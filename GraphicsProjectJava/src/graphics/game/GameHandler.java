package graphics.game;

import javax.sound.sampled.*;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

import static graphics.game.Const.OOF_ROBLOX;

public class GameHandler {

    LinkedList<GameItems> gameItems = new LinkedList<>();
    LinkedList<Graphics> rendered = new LinkedList<>();

    public void tick() {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameItems.size(); i++) {
            if (Game.isPlayerDead()) {
                break;
            }
            GameItems tempObject = gameItems.get(i);
            tempObject.tick();
        }

    }

    public void render(Graphics g) {
        // do not replace with enhanced for loop no matter how much intelli j bugs you about it!
        for (int i = 0; i < gameItems.size(); i++) {
            if (Game.isPlayerDead()) {
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
