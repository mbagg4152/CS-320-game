package graphics.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameItems {
    private GameHandler handler;
    BufferedImage enemyIcon = null;
    private boolean noIcon = false;
    private int size;
    
    public Enemy(int x, int y, ID id, int size) {
        super(x, y, id);
        velX = 5;
        velY = 5;
        this.size = size;
        this.handler = handler;
        int randDiff = new Random().nextInt((int) Game.HEIGHT / 2);
        if (getBounds().intersects(Game.getPlayerBounds())) {
            setX(-x + randDiff);
            setY(y - randDiff);
        }
    }
    
    @Override public Rectangle getBounds() {
        return null;
    }
    
    @Override public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= Game.HEIGHT - 1) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 1) velX *= -1;
        for (int i = 0; i < handler.gameItems.size(); i++) {
            GameItems tempObject = handler.gameItems.get(i);
            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    velY *= -1;
                    velX *= -1;
                    setY(y + 16);
                    setX(x + 16);
                }
            }
        }
    }
    
    @Override public void render(Graphics g) {
    
    }
    
    public void setNoIcon(Graphics g, Color color, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
        g.drawRect(x, y, size, size);
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
