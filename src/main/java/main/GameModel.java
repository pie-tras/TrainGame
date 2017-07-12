package main;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import main.objects.GameObjects;
import main.objects.ID;
import main.objects.MineCart;
import main.objects.Rock;

public class GameModel {

    public GameModel() { }
    
    public Level createLevel(MineCart player){
        
        Level level= new Level();
        
        EventHandler handler= level.getHandler();
        
        for(int xx=0; xx<132; xx++){
            for(int yy=0; yy<4; yy++){
                
                Random rand = new Random();
                int s=rand.nextInt(5);
                if(s==0){
                    
                    int s2=rand.nextInt(100);
                    
                    GameObjects o = new Rock(xx*96+32, yy*96+232, ID.rock);
                    handler.addObject(o);
                    
                    if(s2==0){
                        o.setType(2);
                    }else{
                        o.setType(1);   
                    }
                    
                }else if(s==1){
    
                    int s2=rand.nextInt(200);
                    
                    GameObjects o = new Rock(xx*96+32, yy*96+232, ID.rock);
                    handler.addObject(o);
                    
                    if(s2==0){
                        o.setType(-2);
                    }else{
                        o.setType(-1);  
                    }
                }
            }
        }
        
        handler.addObject(player);
        return level;
    }
    
}
