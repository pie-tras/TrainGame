package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import main.audio.Music;
import main.gfx.Assets;
import main.objects.ID;
import main.objects.MineCart;

@Component
@EnableAutoConfiguration
public class GameController implements Runnable {


    private boolean isRunning = false;
    private Thread thread;
	private MineCart player;
    
	   
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
    
	@Autowired
	private GameView view;
	
    
    
    public GameController() {
        // TODO Auto-generated constructor stub

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
    
    

    public void start(MineCart player){
        this.player= player;

        banjo.setVol(-12f);
        banjo.loop();
        ambientCave.setVol(5f);
        ambientCave.loop();
        trackSound.setVol(4f);
        
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
    


    public MineCart getPlayer() {
        return player;
    }

    public boolean isRunning() {
        return isRunning;
    }

}
