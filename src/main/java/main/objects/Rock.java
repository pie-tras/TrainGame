package main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rock extends GameObjects{

	private int width=64, height=64;
	
	public Rock(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(type==-1){
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		}else if(type==-2){
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
		}else if(type==1){ 
			g.setColor(Color.GRAY);
			g.fillRect(x, y, width, height);
		}else if(type==2){
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
