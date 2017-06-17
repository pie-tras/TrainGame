package main.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Font {
	private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789    ";
	private static SpriteSheet font = new SpriteSheet(ImageLoader.loadImage("/textures/OldFont.png"));
	
    private Font() {}
    
    public static int getStringWidth(String text){
    	return text.length() * 16;
    }
    
    public static void draw(Graphics g, String msg, int x, int y, int R, int G, int B, double scale){
    
    	msg = msg.toUpperCase();
    	int length = msg.length();
    	for(int i = 0; i<length; i++){
    		int c = letters.indexOf(msg.charAt(i));
    		if(c < 0) continue;
    		BufferedImage img = font.crop(c*16, 0, 16, 16);
    		BufferedImage img2 = font.crop(c*16, 0, 16, 16);
    		

       	 	int width = img.getWidth();
            int height = img.getHeight();
            int width2 = img2.getWidth();
            int height2 = img2.getHeight();
            WritableRaster raster = img.getRaster();
            WritableRaster raster2 = img2.getRaster();
            

    		for (int xx = 0; xx < width2; xx++) {
                for (int yy = 0; yy < height2; yy++) {
                    int[] pixels2 = raster2.getPixel(xx, yy, (int[]) null);
                    pixels2[0] = 0;//rand.nextInt(255);
                    pixels2[1] = 0;//rand.nextInt(255);
                    pixels2[2] = 0;//rand.nextInt(255);
                    raster2.setPixel(xx, yy, pixels2);
                }
            }
            
      
    		
    		g.drawImage(img2, x+2, y+2, (int)(16*scale), (int)(16*scale), null);
    		   
            
            
            
            for (int xx = 0; xx < width; xx++) {
                for (int yy = 0; yy < height; yy++) {
                    int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                    pixels[0] = R;//rand.nextInt(255);
                    pixels[1] = G;//rand.nextInt(255);
                    pixels[2] = B;//rand.nextInt(255);
                    raster.setPixel(xx, yy, pixels);
                }
            }
            
    		g.drawImage(img, x, y, (int)(16*scale), (int)(16*scale), null);
    		
    		x+=16*scale;
    	}
    }

	
}
