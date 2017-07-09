package main.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Game;
import main.Handler;
import main.gfx.Animation;
import main.gfx.Assets;

public class MineCart extends GameObjects{

	Animation move = new Animation(110 ,Assets.mineCart);
	
	private Game game;
	
	public MineCart(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.game=game;
		game.getTrackSound().loop();
	}

	@Override
	public void tick() {
		move.tick();
		x+=velX;
		
		if(type<0){
			if(velX>0.1){
				velX-=.1f;
				game.getTrackSound().reduceVol(1f);
			}else{
				game.getTrackSound().stop();
			}
		}else{
			if(velX<8){
				velX+=.1f;
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		if(type==-1){
			g.drawImage(Assets.mineCartRuin, x, y, 140, 96, null);
		}else{
			g.drawImage(move.getCurrentFrame(), x, y, 140, 96, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+20, y+140, 308, 80);
	}

}
