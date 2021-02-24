package graphics.game.gameitems;

import graphics.game.GameHandler;

import static graphics.game.Const.*;

public class MegaEnemy extends Enemy {
    private GameHandler handler;
    
    public MegaEnemy(int x, int y, ItemID id, GameHandler handler) {
        super(x, y, SIZE_MEGA, 1, 1, 6, id, handler, PATH_MEGA_ICON, COLOR_MEGA);
        this.handler = handler;
    }
}
