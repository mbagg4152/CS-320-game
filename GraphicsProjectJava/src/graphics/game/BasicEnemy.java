package graphics.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import static graphics.game.Const.*;

public class BasicEnemy extends GameItems {

    private GameHandler handler;
    BufferedImage enemyIcon = null;
    private boolean noIcon = false;

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
        if (y <= 0 || y >= Game.HEIGHT - 1) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 1) velX *= -1;
        for (int i = 0; i < handler.gameItems.size(); i++) {
            GameItems tempObject = handler.gameItems.get(i);
            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    velY *= -1;
                    velX *= -1;
                }
            }
        }
    }

    private void getIcon() {
        try {
            enemyIcon = ImageIO.read(new File(PATH_BASIC_ICON));

        } catch (Exception e) {
            System.out.println("error loading basic enemy image file: " + e.toString());
            noIcon = true;

        }
    }

    public void render(Graphics g) {
        if (noIcon) {
            g.setColor(COLOR_BASIC);
            g.fillRect(x, y, SIZE_BASIC, SIZE_BASIC);
            g.drawRect(x, y, SIZE_BASIC, SIZE_BASIC);
        } else {
            getIcon();
            g.drawImage(enemyIcon, x, y, SIZE_BASIC, SIZE_BASIC, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                    return false;
                }
            });

        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE_BASIC, SIZE_BASIC);
    }

}
