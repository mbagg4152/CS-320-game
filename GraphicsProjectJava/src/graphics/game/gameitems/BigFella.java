package graphics.game.gameitems;

import graphics.game.GameHandler;

import static graphics.game.Const.*;

public class BigFella extends Enemy {
    private GameHandler handler;
    
    public BigFella(int x, int y, ItemID id, GameHandler handler) {
        super(x, y, SIZE_FELLA, 1, 1,7, id, handler, ICON_FELLA, COLOR_BASIC);
        this.handler = handler;
    }
}
