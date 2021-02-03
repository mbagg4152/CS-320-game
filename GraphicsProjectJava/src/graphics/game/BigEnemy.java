package graphics.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import static graphics.game.Const.*;

public class BigEnemy extends GameItems {

    private GameHandler handler;
    BufferedImage enemyIcon = null;
    private boolean noIcon = false;


    public BigEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        velX = 2;
        velY = 2;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
    }

    private void getIcon() {
        try {
            enemyIcon = ImageIO.read(new File(PATH_BIG_ICON));

        } catch (Exception e) {
            System.out.println("error loading fast enemy image file: " + e.toString());
            noIcon = true;
        }
    }

    public void render(Graphics g) {
        if (noIcon) {
            g.setColor(COLOR_BIG);
            g.fillRect(x, y, SIZE_BIG, SIZE_BIG);
            g.drawRect(x, y, SIZE_BIG, SIZE_BIG);
        } else {
            getIcon();
            g.drawImage(enemyIcon, x, y, SIZE_BIG, SIZE_BIG, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                    return false;
                }
            });

        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, BOUND_BIG, BOUND_BIG);
    }

}
