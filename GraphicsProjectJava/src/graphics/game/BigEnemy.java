package graphics.game;

import static graphics.game.Const.*;

public class BigEnemy extends Enemy {
    private GameHandler handler;
    
    public BigEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id, SIZE_BIG, handler, 2, 2, PATH_BIG_ICON, COLOR_BIG);
        this.handler = handler;
    }
}
