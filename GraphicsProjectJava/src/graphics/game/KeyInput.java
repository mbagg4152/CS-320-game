package graphics.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean up = false;
    private boolean dp = false;
    private boolean lp = false;
    private boolean rp = false;
    private int playerSpeed = 10;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    up = true;
                    tempObject.setVelY(-playerSpeed);
                }
                if (key == KeyEvent.VK_DOWN) {
                    dp = true;
                    tempObject.setVelY(playerSpeed);
                }
                if (key == KeyEvent.VK_LEFT) {
                    lp = true;
                    tempObject.setVelX(-playerSpeed);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    rp = true;
                    tempObject.setVelX(playerSpeed);
                }
            }

            if (key == KeyEvent.VK_ESCAPE) System.exit(0);
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    up = false;
                    if (dp) tempObject.setVelY(playerSpeed);
                    else tempObject.setVelY(0);
                }
                if (key == KeyEvent.VK_DOWN) {
                    dp = false;
                    if (up) tempObject.setVelY(-playerSpeed);
                    else tempObject.setVelY(0);
                }
                if (key == KeyEvent.VK_LEFT) {
                    lp = false;
                    if (rp) tempObject.setVelX(playerSpeed);
                    else tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    rp = false;
                    if (lp) tempObject.setVelX(-playerSpeed);
                    else tempObject.setVelX(0);
                }
            }
        }

    }

}
