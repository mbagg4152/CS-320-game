package graphics.game;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

import static graphics.game.Const.*;

public class Player extends GameItems {
    BufferedImage playerIcon = null;
    Color playerColor = new Color(103, 0, 38);
    boolean noIcon = false;
    private GameHandler handler;
    Clip hitSound;

    public Player(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;
        getIcon();
        loadHitSound();
    }

    public void loadHitSound() {
        AudioInputStream sound;
        try {
            sound = AudioSystem.getAudioInputStream(new File(OOF_ROBLOX));
            hitSound = AudioSystem.getClip();
            hitSound.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
            System.out.println("Error loading the sound file: " + e.getMessage() + " " + e.getClass());
        }

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
                @Override
                public boolean imageUpdate(Image img, int imgFlags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, BOUND_PLAYER, BOUND_PLAYER);
    }

    public void collision() {
        for (int i = 0; i < handler.gameItems.size(); i++) {
            GameItems tempObject = handler.gameItems.get(i);

            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    System.out.println("BONK");
                    if (Objects.nonNull(hitSound)) {
                        hitSound.start();
                    } else {
                        System.out.println("Sound is null");
                    }

                    HUD.playerHealth -= 2;
                    if (HUD.playerHealth <= 0) {
                        Game.setPlayerDead(true);
                    }
                }
            }
        }
    }

}
