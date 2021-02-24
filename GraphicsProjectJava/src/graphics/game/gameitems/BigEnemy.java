package graphics.game.gameitems;

import graphics.game.GameHandler;

import static graphics.game.Const.*;

public class BigEnemy extends Enemy {
    private GameHandler handler;
    
    public BigEnemy(int x, int y, ItemID id, GameHandler handler) {
        super(x, y, SIZE_BIG, 0, 2,4, id, handler, PATH_BIG_ICON, COLOR_BIG);
        this.handler = handler;
    }
}
