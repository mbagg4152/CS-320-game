package graphics.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.MessageFormat;

import static graphics.game.Const.*;

public class Player extends GameObject {
    BufferedImage playerIcon = null;
    Color playerColor = new Color(103, 0, 38);
    boolean noIcon = false;
    private GameHandler handler;

    public Player(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;
        getIcon();

    }

    private void getIcon() {
        try {
            playerIcon = ImageIO.read(new File(System.getProperty("user.dir") + PLAYER_ICON));
        } catch (Exception e) {

            System.out.println(MessageFormat.format("Could not find player icon", e.toString()));
            noIcon = true;
        }
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 37);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);

        collision();
    }

    public void render(Graphics g) {
        if (noIcon) {
            g.setColor(COLOR_PLAYER);
            g.fillRect(x, y, 32, 32);
            g.drawRect(x, y, 32, 32);
        } else {
            getIcon();
            g.drawImage(playerIcon, x, y, 64, 64, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void collision() {
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);

            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.playerHealth -= 2;
                    if (HUD.playerHealth <= 0) {
                        Game.setPlayerDead(true);
                    }
                }
            }
        }
    }

}
