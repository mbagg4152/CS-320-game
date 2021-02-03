package graphics.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class BasicEnemy extends GameObject {
    
    private GameHandler handler;
    BufferedImage enemyIcon = null;
    private boolean noIcon = false;
    Color enemyColor = new Color(183, 61, 185);
    
    public BasicEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        velX = 3;
        velY = 3;
        this.handler = handler;
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        
        //handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.1f, handler));
    }
    
    private void getIcon() {
        try {
            enemyIcon = ImageIO.read(new File(System.getProperty("user.dir") + "/res/basicEnemy.png"));
            
        } catch (Exception e) {
            System.out.println("error loading basic enemy image file: " + e.toString());
            noIcon = true;
            
        }
    }
    
    public void render(Graphics g) {
        if (noIcon) {
            g.setColor(enemyColor);
            g.fillRect(x, y, 28, 28);
            g.drawRect(x, y, 28, 28);
        } else {
            getIcon();
            g.drawImage(enemyIcon, x, y, 48, 48, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                    return false;
                }
            });
            
        }
        
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
    
}
