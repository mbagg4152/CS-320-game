package graphics.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (GameObject tempObject : object) tempObject.tick();

    }

    public void render(Graphics g) {
        for (GameObject tempObject : object) tempObject.render(g);

    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

}
