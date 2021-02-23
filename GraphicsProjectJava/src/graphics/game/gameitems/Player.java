package graphics.game.gameitems;

import graphics.game.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.MessageFormat;

import static graphics.game.Const.*;

public class Player extends GameItems {
    BufferedImage playerIcon = null;
    Color playerColor = new Color(103, 0, 38);
    boolean noIcon = false;
    public GameHandler handler;
    Clip hitSound;
    AudioInputStream sound;

    public Player(int x, int y, ItemID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;
        getIcon();
//        loadHitSound();
    }

//    public void loadHitSound() {
//
//        if (Objects.isNull(sound)) {
//            try {
//                sound = AudioSystem.getAudioInputStream(new File(OOF_ROBLOX));
//                hitSound = AudioSystem.getClip();
//                hitSound.open(sound);
//            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
//                System.out.println("Error loading the sound file: " + e.getMessage() + " " + e.getClass());
//            }
//        }
//
//    }

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
        return new Rectangle(x, y, SIZE_PLAYER, SIZE_PLAYER);
    }

    public void collision() {
        for (int i = 0; i < handler.gameItems.size(); i++) {
            GameItems tempObject = handler.gameItems.get(i);

            if (tempObject.getId() == ItemID.BasicEnemy || tempObject.getId() == ItemID.BigEnemy) {

                if (getBounds().intersects(tempObject.getBounds())) {
                    System.out.println("BONK " + HUD.playerHealth);
                    new HitSound();

                    if (tempObject.getId() == ItemID.BasicEnemy) {
                        HUD.playerHealth -= 1;
                    } else {
                        HUD.playerHealth -= 4;
                    }

                    if (HUD.playerHealth <= 0) {
                        Game.setPlayerDead(true);
                    }
                }
            }
        }
    }

//    public void loadHitSound() {
//
//        AudioInputStream sound;
//        try {
//            sound = AudioSystem.getAudioInputStream(new File(OOF_ROBLOX));
//            hitSound = AudioSystem.getClip();
//            hitSound.open(sound);
////            hitSound.start();
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
//            System.out.println("Error loading the sound file: " + Arrays.toString(e.getStackTrace()));
//        }
//
//    }

}
