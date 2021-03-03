package graphics.game.gameitems;

import graphics.game.GameHandler;

import static graphics.game.Const.*;

public class FlameGuy extends Enemy {
    private GameHandler handler;
    
    public FlameGuy(int x, int y, ItemID id, GameHandler handler) {
        super(x, y, SIZE_FLAME, 2, 2,4, id, handler, ICON_FIRE, COLOR_FLAME);
        this.handler = handler;
    }
}
