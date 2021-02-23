package graphics.game;

import static graphics.game.Const.*;

public class BigEnemy extends Enemy {
    private GameHandler handler;
    
    public BigEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, SIZE_BIG, 2, 2, id, handler, PATH_BIG_ICON, COLOR_BIG);
        this.handler = handler;
    }
}
