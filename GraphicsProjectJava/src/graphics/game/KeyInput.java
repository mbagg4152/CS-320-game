package graphics.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    
    private GameHandler handler;
    private boolean upEvent = false;
    private boolean downEvent = false;
    private boolean leftEvent = false;
    private boolean rightEvent = false;
    private int playerSpeed = 10;
    
    public KeyInput(GameHandler handler) {
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    upEvent = true;
                    tempObject.setVelY(-playerSpeed);
                }
                if (key == KeyEvent.VK_DOWN) {
                    downEvent = true;
                    tempObject.setVelY(playerSpeed);
                }
                if (key == KeyEvent.VK_LEFT) {
                    leftEvent = true;
                    tempObject.setVelX(-playerSpeed);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    rightEvent = true;
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
                    upEvent = false;
                    if (downEvent) tempObject.setVelY(playerSpeed);
                    else tempObject.setVelY(0);
                }
                if (key == KeyEvent.VK_DOWN) {
                    downEvent = false;
                    if (upEvent) tempObject.setVelY(-playerSpeed);
                    else tempObject.setVelY(0);
                }
                if (key == KeyEvent.VK_LEFT) {
                    leftEvent = false;
                    if (rightEvent) tempObject.setVelX(playerSpeed);
                    else tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    rightEvent = false;
                    if (leftEvent) tempObject.setVelX(-playerSpeed);
                    else tempObject.setVelX(0);
                }
            }
        }
        
    }
    
}
