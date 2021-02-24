package graphics.game;

import graphics.game.gameitems.Enemy;
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
            gameItems.get(i).tick();
            
        }
        
    }
    
    public void render(Graphics g) {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameItems.size(); i++) {
            if (Game.isPlayerDead()) break;
            GameItems tmpGI = gameItems.get(i);
            if (!(rendered.contains(g))) rendered.add(g);
            if (tmpGI instanceof Enemy) {
                if (((Enemy) tmpGI).isMarkedForDeath()) {
                    removeObject(tmpGI);
                    rendered.remove(g);
                }
            }
            
            tmpGI.render(g);
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
        for (Graphics item : rendered) item.dispose();
        rendered = new LinkedList<>();
    }
    
}
