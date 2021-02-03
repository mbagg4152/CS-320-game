package graphics.game;

import java.util.Random;

import static graphics.game.Game.*;

public class Spawn {

    private GameHandler handler;
    private HUD hud;
    private Random r = new Random();

    private int scoreKeep = 0;
    private int levelKeep = 1;

    public Spawn(GameHandler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;
//        levelKeep++;
        if (scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() > 1) {
                levelKeep++;
                handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
            }
            if (levelKeep == 5) {
                levelKeep = 0;
                handler.addObject(new BigEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
            }
        }

    }

}
