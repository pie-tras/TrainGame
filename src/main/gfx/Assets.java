package main.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static int cartWidth=35, cartHeight=24;
	
	public static BufferedImage tracks, mineCartRuin;
	
	public static BufferedImage[] mineCart;
	
	public static void init(){
		SpriteSheet t1 = new SpriteSheet(ImageLoader.loadImage("/textures/MineCart.png"));
		
		tracks=ImageLoader.loadImage("/textures/Tracks.png");
		
		mineCartRuin=ImageLoader.loadImage("/textures/MineCartRuin.png");
		
		mineCart = new BufferedImage[4];
		
		mineCart[0]=t1.crop(0, 0, cartWidth, cartHeight);
		mineCart[1]=t1.crop(cartWidth, 0, cartWidth, cartHeight);
		mineCart[2]=t1.crop(cartWidth*2, 0, cartWidth, cartHeight);
		mineCart[3]=t1.crop(cartWidth*3, 0, cartWidth, cartHeight);
		
	}
}
