package graphics.game;

import static graphics.game.Const.*;

public class BasicEnemy extends Enemy {
    private GameHandler handler;
    
    public BasicEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id, SIZE_BASIC, handler, 5, 5, PATH_BASIC_ICON, COLOR_BASIC);
        this.handler = handler;
    }
}
