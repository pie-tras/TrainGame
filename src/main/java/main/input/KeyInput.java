package main.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Handler;
import main.objects.ID;
import main.objects.GameObjects;

public class KeyInput extends KeyAdapter{

	Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObjects tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.mineCart){
				if(key == KeyEvent.VK_UP) handler.setMove(true);
				if(key == KeyEvent.VK_DOWN) handler.setBrake(true);
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObjects tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.mineCart){
				if(key == KeyEvent.VK_UP) handler.setMove(false);
				if(key == KeyEvent.VK_DOWN) handler.setBrake(false);
			}
		}		
		
		
	}
}
