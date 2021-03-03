package graphics.game.gameitems;

import graphics.game.GameHandler;

import static graphics.game.Const.*;

public class SlimeGirl extends Enemy {
    private GameHandler handler;
    
    public SlimeGirl(int x, int y, ItemID id, GameHandler handler) {
        super(x, y, SIZE_SLIME, 1, 1, 6, id, handler, ICON_SLIME, COLOR_SLIME);
        this.handler = handler;
    }
}
