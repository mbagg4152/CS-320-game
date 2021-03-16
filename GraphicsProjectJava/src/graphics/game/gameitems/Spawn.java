package graphics.game.gameitems;

import graphics.game.GameHandler;
import graphics.game.HUD;

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
    
    public int[] getSpawn() {
        int spawnX = r.nextInt(WIDTH), spawnY = r.nextInt(HEIGHT), flip = r.nextInt(2), flip2 = r.nextInt(2);
        if (spawnX > spawnY) {
            if (flip == 0) spawnY = 1;
            else spawnY = HEIGHT - 1;
        } else if (spawnY > spawnX) {
            if (flip == 0) spawnX = 1;
            else spawnX = WIDTH - 1;
        } else {
            if (flip == 0) {
                if (flip2 == 0) spawnX = 1;
                else spawnX = WIDTH - 1;
            } else {
                if (flip2 == 0) spawnY = 1;
                else spawnY = HEIGHT - 1;
            }
        }
        return new int[]{spawnX, spawnY};
    }
    
    public void tick() {
        scoreKeep++;
        if (scoreKeep >= 8) {
            scoreKeep = 0;
            levelKeep++;
            hud.setLevel(hud.getLevel() + 1);
            int[] points; // make different calls to getSpawn() to hopefully have different random vals
            if (levelKeep % 3 == 0) {
                points = getSpawn();
                handler.addObject(new BasicEnemy(points[0], points[1], ItemID.BasicEnemy, handler));
            }
            if (levelKeep % 13 == 0) {
                points = getSpawn();
                handler.addObject(new FlameGuy(points[0], points[1], ItemID.BasicEnemy, handler));
            }
            if (levelKeep % 29 == 0) {
                points = getSpawn();
                handler.addObject(new SlimeGirl(points[0], points[1], ItemID.SlimeGirl, handler));
            }
            if (levelKeep % 59 == 0) {
                points = getSpawn();
                handler.addObject(new BigFella(points[0], points[1], ItemID.BigFella, handler));
            }
        }
        
    }
    
}
