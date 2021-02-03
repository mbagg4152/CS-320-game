package graphics.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameHandler {

    LinkedList<GameItems> gameItems = new LinkedList<>();
    LinkedList<Graphics> rendered = new LinkedList<>();

    public void tick() {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameItems.size(); i++) {
            if (Game.getPlayerDead()) {
                break;
            }
            GameItems tempObject = gameItems.get(i);
            tempObject.tick();
        }

    }

    public void render(Graphics g) {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameItems.size(); i++) {
            if (Game.getPlayerDead()) {
                break;
            }
            GameItems tempObject = gameItems.get(i);
            if (!(rendered.contains(g))) {
                rendered.add(g);
            }

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
