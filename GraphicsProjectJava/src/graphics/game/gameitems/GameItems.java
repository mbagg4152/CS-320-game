package graphics.game.gameitems;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameItems {
    
    protected int x, y;
    protected ItemID id;
    protected int velX, velY;
    public int size = -1;
    
    public GameItems(int x, int y, ItemID id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.velX = 0;
        this.velY = 0;
    }
    
    public abstract Rectangle getBounds();
    
    //Setters
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setId(ItemID id) {
        this.id = id;
    }
    
    public void setVelX(int velX) {
        this.velX = velX;
    }
    
    public void setVelY(int velY) {
        this.velY = velY;
    }
    
    //Getters
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public ItemID getId() {
        return id;
    }
    
    public int getVelX() {
        return velX;
    }
    
    public int getVelY() {
        return velY;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
