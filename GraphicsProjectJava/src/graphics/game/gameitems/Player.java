package graphics.game.gameitems;

import graphics.game.*;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.MessageFormat;

import static graphics.game.Const.*;

public class Player extends GameItems {
    BufferedImage playerIcon = null;
    boolean noIcon = false;
    public GameHandler handler;
    
    public Player(int x, int y, ItemID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;
        getIcon();
    }
    
    private void getIcon() {
        try {
            playerIcon = ImageIO.read(new File(PATH_PLAYER_ICON));
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
            g.fillRect(x, y, SIZE_PLAYER, SIZE_PLAYER);
            g.drawRect(x, y, SIZE_PLAYER, SIZE_PLAYER);
        } else {
            getIcon();
            g.drawImage(playerIcon, x, y, SIZE_PLAYER, SIZE_PLAYER, new ImageObserver() {
                @Override public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
        
    }
    
    @Override public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE_PLAYER, SIZE_PLAYER);
    }
    
    public void collision() {
        for (int i = 0; i < handler.gameItems.size(); i++) {
            GameItems tempObject = handler.gameItems.get(i);
            
            if (tempObject instanceof Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.playerHealth -= ((Enemy) tempObject).getDamage();
                    System.out.println("BONK -" + ((Enemy) tempObject).getDamage() + " " + HUD.playerHealth);
                    if (HUD.playerHealth <= 0) {
                        Game.killPlayer(true);
                    }
                }
            }
        }
    }
    
}
