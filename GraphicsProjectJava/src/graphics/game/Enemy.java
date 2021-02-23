package graphics.game;

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
    
    public Enemy(int x, int y, int size, int vx, int vy, ID id, GameHandler handler, String iconPath, Color color) {
        super(x, y, id);
        velX = vx;
        velY = vy;
        this.size = size;
        this.handler = handler;
        this.enemyColor = color;
        this.iconPath = iconPath;
        
        int randDiff = new Random().nextInt((int) Game.HEIGHT / 2);
        if (getBounds().intersects(Game.getPlayerBounds())) {
            setX(-x + randDiff);
            setY(y - randDiff);
        }
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
    
    @Override public Rectangle getBounds() { return new Rectangle(x, y, this.size, this.size); }
}
