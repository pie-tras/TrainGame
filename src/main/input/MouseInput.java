package main.input;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import main.Handler;
import main.gfx.Camera;
import main.objects.ID;
import main.objects.GameObjects;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private Camera camera;
	private Game game;
	
	private int x,y;
	
	public MouseInput(Handler handler, Camera camera, Game game){
		this.handler = handler;
		this.camera = camera;
		this.game = game;
	}
	
	public void mouseMoved(MouseEvent e){
		x = (int) (e.getX());
		y = (int) (e.getY());
	}
	
	public void mouseDragged(MouseEvent e){
		x = (int) (e.getX());
		y = (int) (e.getY());
	}
	
	public void mousePressed(MouseEvent e){
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
		
		
		if(game.getPlayer().getType()<0) return;
		
		for(int i=0; i< handler.object.size(); i++){
			GameObjects tempObject = handler.object.get(i);
			
			Rectangle r = new Rectangle(mx-16, my-8, 32, 32);
			
			if(tempObject.getBounds().intersects(r) && tempObject.getId()==ID.rock){
				
				if(tempObject.getType()==-1){
					game.setHp(game.getHp() - 10);
				}else if(tempObject.getType()==-2){
					if(game.getHp()!=game.getStartHp()){
						game.setHp(game.getHp() + 10);
					}else{
						game.getCoinSound().stop ();
						game.getCoinSound().play();
						game.setMoney(game.getMoney()+10);
					}
				}else{
					
					game.getCoinSound().stop ();
					game.getCoinSound().play();
					game.setMoney(game.getMoney()+(tempObject.getType()));
					
				}
				
				tempObject.setType(0);
			}
			
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
