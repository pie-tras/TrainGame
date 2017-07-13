package main;

public class PlayerModel {

    public static final int START_HEALTH= 20;

    private int health;
    private int money;
    
    public PlayerModel(int health, int money) {
        this.health= health;
        this.money= money;
    }
    

    public int getHealth() {
        return health;
    }
    
    public int getMoney() {
        return money;
    }
    
    public boolean isDead() {
        return health <= 0;
    }


    public void subtractHealth() {
        this.health-= 10;
    }
    
    public void addHealth() {
        this.health+= 10;
    }


    public void addMoney() {
        this.money++;
    }
    
}
