package graphics.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class FastEnemy extends GameObject {
    
    private GameHandler handler;
    BufferedImage enemyIcon = null;
    
    public FastEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        velX = 9;
        velY = 9;
        this.handler = handler;
    }
    
    @Override public void tick() {
        x += velX;
        y += velY;
        
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 10, 10, 0.01f, handler));
    }
    
    private void getFastEnemyIcon() {
        try {
            enemyIcon = ImageIO.read(new File(System.getProperty("user.dir") + "/res/fastEnemy.png"));
        } catch (Exception e) {
            System.out.println("error loading fast enemy image file: " + e.toString());
        }
    }
    
    public void render(Graphics g) {
        
        //g.fillRect(x, y, 32, 32);
        getFastEnemyIcon();
        g.drawImage(enemyIcon, x, y, 16, 16, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                return false;
            }
        });
        
    }

//	@Override
//	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect(x, y, 16, 16);
//	}
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
    
}
