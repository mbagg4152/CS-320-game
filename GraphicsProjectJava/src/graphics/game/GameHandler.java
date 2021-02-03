package graphics.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameHandler {
    
    LinkedList<GameObject> gameObjects = new LinkedList<>();
    LinkedList<Graphics> rendered = new LinkedList<>();
    
    public void tick() {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.tick();
        }
        
    }
    
    public void render(Graphics g) {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            if (!(rendered.contains(g))) {
                rendered.add(g);
            }
            
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object) {
        this.gameObjects.add(object);
    }
    
    public void removeObject(GameObject object) {
        this.gameObjects.remove(object);
    }
    
    public void removeAll() {
        gameObjects.clear();
        for (Graphics item : rendered) {item.dispose();}
    }
}
