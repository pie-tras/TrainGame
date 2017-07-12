package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import main.gfx.Camera;
import main.objects.GameObjects;
import main.objects.ID;

@EnableAutoConfiguration
@Component
public class EventHandler {
	//tick and render all objects

    // member variables should generally all be private to promote encapsulation
	private LinkedList<GameObjects> object = new LinkedList<GameObjects>();
	private boolean move = false, brake = false;
	
		
	public EventHandler(){
	}
		
	public void tick(GameObjects tempObject, Rectangle screen){
			
			if(tempObject.getBounds().intersects(screen)){
				
				if(tempObject.getId()==ID.rock && tempObject.getType()!=0){
					tempObject.tick();
				}else{
					tempObject.tick();
				}
				
			}else{
				if(tempObject.getId()==ID.mineCart){
					tempObject.setType(-1);
					tempObject.tick();
				}
			}
	}
	
	/**
	 * Returns a read-only copy of the internal list of objects being tracked by this handler
	 * @return
	 */
	public List<GameObjects> getObjects() {
        return Collections.unmodifiableList(object);
    }
		

	
	public void addObject(GameObjects tempObject){
		object.add(tempObject);
	}
	
	public void removeObject(GameObjects tempObject){
		object.remove(tempObject);
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
		
	public boolean isBrake() {
		return brake;
	}

	public void setBrake(boolean brake) {
		this.brake = brake;
	}


		
}
