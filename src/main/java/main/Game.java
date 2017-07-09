package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import main.audio.Music;
import main.gfx.Assets;
import main.gfx.Camera;
import main.gfx.Font;
import main.input.KeyInput;
import main.input.MouseInput;
import main.objects.ID;
import main.objects.GameObjects;
import main.objects.Rock;
import main.objects.MineCart;

@SpringBootApplication
public class Game extends Canvas implements Runnable, CommandLineRunner {

	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private MouseInput mouseInput;
	private final int WIDTH=800, HEIGHT=600;
	
	//music
	@Autowired
	@Qualifier("banjoSound")
	private Music banjo;
	
	//sounds
	@Autowired
	private Music coinSound;
	
	@Autowired
	private Music trackSound;
	
	//ambient
	@Autowired
	@Qualifier("ambientCaveSound")
	private Music ambientCave;
	
	private GameObjects player;
	
	private boolean dead=false;
	private int hp=20, startHp=hp, money;
	
	public Game(){
	}
	
	private void start(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			render();
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
			}
		}
		stop();		
	}
	
	public void tick(){
		if(hp<=0){
			dead = true;
		}
		
		if(dead){
			player.setType(-1);
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.mineCart){
				camera.tick(handler.object.get(i));
			}
		}
		
		handler.tick();
	}

	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//clear screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//draw here
		g2d.translate(-camera.getX(), -camera.getY());
		
		renderTracks(g);
		handler.render(g);
		
		g2d.translate(camera.getX(), camera.getY());
		
		renderHUD(g);
		
		//end draw	
		g.dispose();
		bs.show();	
	}
	
	public void renderHUD(Graphics g){
		String msg = "Money "+ money;
		
		g.setColor(Color.BLUE);
		g.fillRect(245, HEIGHT-107, (msg.length()*32)+15, 48);
		Font.draw(g, msg, 250, HEIGHT-100, 255, 255, 255, 2);
		
		g.setColor(Color.BLACK);
		g.fillRect(5, HEIGHT-107, startHp*2+4, 32);
		g.setColor(Color.RED);
		g.fillRect(7, HEIGHT-105, hp*2, 28);
		
		g.setColor(Color.GREEN);
		g.drawRect(mouseInput.getX()-16, mouseInput.getY()-8, 32, 32);
	}
	
	public void renderTracks(Graphics g){
		for(int xx = 0; xx < 12800; xx+=512){
			g.drawImage(Assets.tracks, xx, 606, 512, 48, null);
		}
	}
	
	public void createLevel(){
		for(int xx=0; xx<132; xx++){
			for(int yy=0; yy<4; yy++){
				
				Random rand = new Random();
				int s=rand.nextInt(5);
				if(s==0){
					
					int s2=rand.nextInt(100);
					
					GameObjects o = new Rock(xx*96+32, yy*96+232, ID.rock);
					handler.addObject(o);
					
					if(s2==0){
						o.setType(2);
					}else{
						o.setType(1);	
					}
					
				}else if(s==1){
	
					int s2=rand.nextInt(200);
					
					GameObjects o = new Rock(xx*96+32, yy*96+232, ID.rock);
					handler.addObject(o);
					
					if(s2==0){
						o.setType(-2);
					}else{
						o.setType(-1);	
					}
				}
			}
		}
		
		handler.addObject(player);
	}
	
	
	public static void main(String args[]){
	    SpringApplication.run(Game.class, args);
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getStartHp() {
		return startHp;
	}

	public void setStartHp(int startHp) {
		this.startHp = startHp;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Music getCoinSound() {
		return coinSound;
	}

	public void setCoinSound(Music coinSound) {
		this.coinSound = coinSound;
	}

	public Music getTrackSound() {
		return trackSound;
	}

	public void setTrackSound(Music trackSound) {
		this.trackSound = trackSound;
	}
	
	public GameObjects getPlayer() {
		return player;
	}

	public void setPlayer(GameObjects player) {
		this.player = player;
	}

    @Override
    public void run(String... arg0) throws Exception {
        // TODO Auto-generated method stub

        new Window(WIDTH, HEIGHT, "Train Game", this);
        Assets.init();
        camera = new Camera(0, 100);
        handler = new Handler(this, camera);
        start();
        this.addKeyListener(new KeyInput(handler));
        mouseInput = new MouseInput(handler, camera, this);
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);
          
        player = new MineCart(0, 530, ID.mineCart, handler, this);
        
        createLevel();
        
        banjo.setVol(-12f);
        banjo.loop();
        ambientCave.setVol(5f);
        ambientCave.loop();
        trackSound.setVol(4f);
    }

	
	
}
