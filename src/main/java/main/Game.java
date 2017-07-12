package main;

import main.objects.ID;
import main.objects.MineCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// By making this a SpringBootApplication, we get some nice things for "free"
// like configuration and auto-wiring.
@EnableAutoConfiguration
@SpringBootApplication
public class Game implements CommandLineRunner {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GameController controller;
	
	@Autowired
	private GameView view;
	
	public Game(){ }
	
	public static void main(String args[]){
	    SpringApplication.run(Game.class, args);
	}
	
    
    // This is the entry point of the Spring Boot Application - Command Line Runner
    @Override
    public void run(String... arg0) throws Exception {
        MineCart player=  new MineCart(0, 530, ID.mineCart, controller);
        GameModel game= new GameModel();
       
        view.init(game.createLevel(player));
        
        controller.start(player);
        
        // datasource.getConnection().createStatement().execute("create table `test` (`a` int);");
    }

}
