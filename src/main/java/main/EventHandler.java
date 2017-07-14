package main;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import main.objects.GameObjects;
import main.objects.ID;

public class EventHandler {
	//tick and render all objects

    // member variables should generally all be private to promote encapsulation
	private LinkedList<GameObjects> object = new LinkedList<GameObjects>();
	private boolean move = false, brake = false;
	
	private boolean levelComplete= false;
	
		
	public EventHandler(){ }
		
	public void tick(GameObjects tempObject, Rectangle screen){
			
			if(tempObject.getBounds().intersects(screen)){
				
			    // there is no difference between the if() and the else()
				//if(tempObject.getId()==ID.rock && tempObject.getType()!=0){
					tempObject.tick();
				//} 
				//else{
				//	tempObject.tick();
				//}
				
			}else{
				if(tempObject.getId()==ID.mineCart){
					tempObject.setType(-1);    // brad: figure out why the type is getting set to -1 and what that means.
					tempObject.tick();
					// see if we've past the end of the level.
					if (tempObject.getX() > screen.x + screen.width) {
					    levelComplete= true;
					}
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


	public boolean isComplete() {
	    return levelComplete;
	}
		
}
