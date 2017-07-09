package main.gfx;

import main.objects.GameObjects;

public class Camera {

	private float x, y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObjects object){
		
		x += ((object.getX() - (x-150)) - 800/2) * 0.05f;
		y += ((object.getY() - (y+50)) - 600/2) * 0.05f;
		
		if(x <= 0) x = 0;
		if(x >= 11800) x = 11800;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
