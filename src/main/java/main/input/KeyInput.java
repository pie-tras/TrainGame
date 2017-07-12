package main.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.EventHandler;
import main.objects.ID;
import main.objects.GameObjects;

public class KeyInput extends KeyAdapter{

	EventHandler handler;
	
	public KeyInput(EventHandler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		//for(int i = 0; i < handler.object.size(); i++){
		//	GameObjects tempObject = handler.object.get(i);
			
		for (GameObjects tempObject : handler.getObjects()) {
			if(tempObject.getId() == ID.mineCart){
				if(key == KeyEvent.VK_UP) handler.setMove(true);
				if(key == KeyEvent.VK_DOWN) handler.setBrake(true);
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		//for(int i = 0; i < handler.object.size(); i++){
		//	GameObjects tempObject = handler.object.get(i);
		
		for (GameObjects tempObject : handler.getObjects()) {
			
			if(tempObject.getId() == ID.mineCart){
				if(key == KeyEvent.VK_UP) handler.setMove(false);
				if(key == KeyEvent.VK_DOWN) handler.setBrake(false);
			}
		}		
		
		
	}
}
