package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import main.gfx.Camera;
import main.objects.GameObjects;
import main.objects.ID;

public class Handler {
	//tick and render all objects

		public LinkedList<GameObjects> object = new LinkedList<GameObjects>();
		
		private boolean move = false, brake = false;
	
		private Game game;
		private Camera camera;
		
		public Handler(Game game, Camera camera){
			this.game=game;
			this.camera=camera;
		}
		
		public void tick(){
			for(int i = 0; i < object.size(); i++){
				GameObjects tempObject = object.get(i);
				
				Rectangle screen = new Rectangle((int)camera.getX(), (int)camera.getY(), game.getWIDTH(), game.getHEIGHT());
				
				if(tempObject.getBounds().intersects(screen)){
					
					if(tempObject.getId()==ID.rock && tempObject.getType()!=0){
						tempObject.tick();
					}else{
						tempObject.tick();
					}
					
				}else{
					if(tempObject.getId()==ID.mineCart){
						object.remove(tempObject);
						game.getTrackSound().reduceVol(10.0f);
					}
				}
			}
		}
		
		public void render(Graphics g){
			for(int i = 0; i < object.size(); i++){
				GameObjects tempObject = object.get(i);

				Rectangle screen = new Rectangle((int)camera.getX(), (int)camera.getY(), game.getWIDTH(), game.getHEIGHT());
				
				if(tempObject.getBounds().intersects(screen)){
					
					if(tempObject.getId()==ID.rock && tempObject.getType()!=0){
						tempObject.render(g);
					}else{
						tempObject.render(g);
					}
				}
			}
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
