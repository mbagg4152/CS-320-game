package graphics.game.gameitems;

import graphics.game.Game;
import graphics.game.GameHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Random;

public class Enemy extends GameItems {
    private GameHandler handler;
    BufferedImage enemyIcon = null;
    private boolean noIcon = false;
    private int size;
    private Color enemyColor;
    private String iconPath;
    int counter = 0;
    int passedInSize;
    boolean markedForDeath = false;
    int damage;
    
    public Enemy(int x, int y, int size, int vx, int vy, int damage, ItemID id, GameHandler handler, String iconPath, Color color) {
        super(x, y, id);
        velX = vx;
        velY = vy;
        this.size = size;
        this.passedInSize = size;
        this.handler = handler;
        this.enemyColor = color;
        this.iconPath = iconPath;
        this.damage = damage;
        
        int randDiff = new Random().nextInt((int) Game.HEIGHT / 2);
        if (getBounds().intersects(Game.getPlayerBounds())) {
            setX(-x + randDiff);
            setY(y - randDiff);
        }
    }
    
    @Override public void tick() {
        
        x += velX;
        y += velY;
        if ((y <= 0 || y >= Game.HEIGHT - 1) || (x <= 0 || x >= Game.WIDTH - 1)) {
            this.markedForDeath = true;
        }
        
        for (int i = 0; i < handler.gameItems.size(); i++) {
            GameItems tempObject = handler.gameItems.get(i);
            if (tempObject.getId() == ItemID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    setY(y - 48);
                    setX(x - 48);
                }
            }
        }
    }
    
    @Override public void render(Graphics g) {
        if (noIcon) setNoIcon(g);
        else {
            getIcon();
            g.drawImage(this.enemyIcon, x, y, this.size, this.size, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                    return false;
                }
            });
            
        }
    }
    
    public void getIcon() {
        try { this.enemyIcon = ImageIO.read(new File(this.iconPath)); } catch (Exception e) {
            System.out.println("error loading enemy image file: " + e.toString());
            noIcon = true;
        }
    }
    
    public void setNoIcon(Graphics g) {
        g.setColor(this.enemyColor);
        g.fillRect(x, y, this.size, this.size);
        g.drawRect(x, y, this.size, this.size);
    }
    
    public void setX(int x) { this.x = x; }
    
    public void setY(int y) { this.y = y; }
    
    public int getSize() {return this.size;}
    
    public int getDamage() {return this.damage;}
    
    public boolean isMarkedForDeath() {return this.markedForDeath;}
    
    @Override public Rectangle getBounds() { return new Rectangle(x, y, this.size, this.size); }
}
