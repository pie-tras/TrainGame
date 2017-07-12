package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

// By making this a SpringBootApplication, we get some nice things for "free"
// like configuration and auto-wiring.
@EnableAutoConfiguration
@SpringBootApplication
public class Game implements Runnable, CommandLineRunner {

	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;
	private Thread thread;
	
	@Autowired
	private EventHandler handler;
	
	@Autowired
	private GameView view;
	
	// Autowired variables are set by some Spring magic at application boot.
	// The names and types of these variables are important to make the Autowiring 
	// magic work.
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
	
	private MineCart player;
	
	@Autowired
	private DataSource datasource;
	
	public Game(){
	}
	
	private void start(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		// i'm being really lazy:
		new Thread(view).start();
	}
	
	private void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void tick() {
	    
        view.tick(player);
        
	}
	
	public void run(){
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
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
			}
		}
		stop();		
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
	

	// This is the entry point of the Spring Boot Application - Command Line Runner
    @Override
    public void run(String... arg0) throws Exception {
        this.player = new MineCart(0, 530, ID.mineCart, this);
        Assets.init();
        view.init();
          
        createLevel();
        
        banjo.setVol(-12f);
        banjo.loop();
        ambientCave.setVol(5f);
        ambientCave.loop();
        trackSound.setVol(4f);
        
        start();
        
        // datasource.getConnection().createStatement().execute("create table `test` (`a` int);");
    }

    public MineCart getPlayer() {
        return player;
    }
	
    public EventHandler getEventHandler() {
        return handler;
    }

    public boolean isRunning() {
        return isRunning;
    }
	
}
