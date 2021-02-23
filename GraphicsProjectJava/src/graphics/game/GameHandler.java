package graphics.game;

import graphics.game.gameitems.GameItems;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameHandler {
    
    public LinkedList<GameItems> gameItems = new LinkedList<>();
    public LinkedList<Graphics> rendered = new LinkedList<>();
    
    public void tick() {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameItems.size(); i++) {
            if (Game.isPlayerDead()) break;
            GameItems tempObject = gameItems.get(i);
//            System.out.println(tempObject.getX() + " " + tempObject.getY());
            
            tempObject.tick();
        }
        
    }
    
    public void render(Graphics g) {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameItems.size(); i++) {
            if (Game.isPlayerDead()) break;
            GameItems tempObject = gameItems.get(i);
            if (!(rendered.contains(g))) rendered.add(g);
//            if ((tempObject.getY() <= 0 || tempObject.getY() >= Game.HEIGHT - 1)) {
//                removeObject(tempObject);
//                System.out.println("removed");
//            }
//            if (tempObject.getX() <= 0 || tempObject.getX() >= Game.HEIGHT - 1){
//                removeObject(tempObject);
//                System.out.println("removed");
//            }
            
            tempObject.render(g);
        }
    }
    
    public void addObject(GameItems object) {
        this.gameItems.add(object);
    }
    
    public void removeObject(GameItems object) {
        this.gameItems.remove(object);
    }
    
    public void removeAll() {
        gameItems = new LinkedList<>();
        for (Graphics item : rendered) {
            item.dispose();
        }
        rendered = new LinkedList<>();
    }
    
}
