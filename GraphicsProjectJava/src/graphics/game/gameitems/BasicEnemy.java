package graphics.game.gameitems;

import graphics.game.GameHandler;

import static graphics.game.Const.*;

public class BasicEnemy extends Enemy {
    private GameHandler handler;
    
    public BasicEnemy(int x, int y, ItemID id, GameHandler handler) {
        super(x, y, SIZE_BASIC, 5, 5, id, handler, PATH_BASIC_ICON, COLOR_BASIC);
        this.handler = handler;
    }
}
