package graphics.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class FastEnemy extends GameObject {
    
    private GameHandler handler;
    BufferedImage enemyIcon = null;
    private boolean noIcon = false;
    Color enemyColor = new Color(0, 85, 218);
    
    public FastEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        velX = 6;
        velY = 6;
        this.handler = handler;
    }
    
    @Override public void tick() {
        x += velX;
        y += velY;
        
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

//        handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 10, 10, 0.01f, handler));
    }
    
    private void getIcon() {
        try {
            enemyIcon = ImageIO.read(new File(System.getProperty("user.dir") + "/res/enemyIdea1.png"));
            
        } catch (Exception e) {
            System.out.println("error loading fast enemy image file: " + e.toString());
            noIcon = true;
        }
    }
    
    public void render(Graphics g) {
        if (noIcon) {
            g.setColor(enemyColor);
            g.fillRect(x, y, 24, 24);
            g.drawRect(x, y, 24, 24);
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
        return new Rectangle(x, y, 10, 10);
    }
    
}
