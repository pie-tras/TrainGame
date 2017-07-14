package main;


/**
 * Represents a level in the game
 * @author morsecode
 *
 */
public class Level implements Comparable<Level> {
    
    private int value;
    
    private EventHandler handler;
    
    public Level() {
        handler= new EventHandler();
        this.value= 1;  // the first level
    }
    
    private Level(int value) {
        handler= new EventHandler();
        this.value= Math.abs(value);    // don't allow levels < 0
    }

    public int getValue() {
        return value;
    }
    
    /**
     * Returns <=-1 if this < otherLevel
     * Returns 0 if this == otherLevel
     * Returns >=1  if this > otherLevel 
     */
    @Override
    public int compareTo(Level otherLevel) {
        if (otherLevel == null) {
            return 1;   // something > null
        }
        
        // simple way to return < 0, 0, or > 0 with some math
        return value - otherLevel.value;
    }
    
    public Level getNextLevel() {
        return new Level(value + 1);
    }
    
    public EventHandler getHandler() {
        return handler;
    }
}
