package graphics.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class BasicEnemy extends GameObject {
    
    private GameHandler handler;
    BufferedImage enemyIcon = null;
    
    public BasicEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        velX = 5;
        velY = 5;
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
    
    private void getBasicEnemyIcon() {
        try {
            enemyIcon = ImageIO.read(new File(System.getProperty("user.dir") + "/res/basicEnemy.png"));
        } catch (Exception e) {
            System.out.println("error loading basic enemy image file: " + e.toString());
        }
    }
    
    public void render(Graphics g) {
        
        //g.fillRect(x, y, 32, 32);
        getBasicEnemyIcon();
        g.drawImage(enemyIcon, x, y, 48, 48, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                return false;
            }
        });
        
    }
//    @Override
//    public void render(Graphics g) {
//        g.setColor(Color.red);
//        g.fillRect(x, y, 16, 16);
//    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
    
}
