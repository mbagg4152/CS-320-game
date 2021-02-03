package graphics.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameHandler {
    
    LinkedList<GameObject> object = new LinkedList<>();
    
    public void tick() {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }
    
    public void render(Graphics g) {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object) {
        this.object.add(object);
    }
    
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}
