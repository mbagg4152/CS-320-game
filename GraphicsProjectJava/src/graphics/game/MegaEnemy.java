package graphics.game;

import static graphics.game.Const.*;

public class MegaEnemy extends Enemy {
    private GameHandler handler;
    
    public MegaEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, SIZE_MEGA, 1, 1, id, handler, PATH_MEGA_ICON, COLOR_MEGA);
        this.handler = handler;
    }
}
