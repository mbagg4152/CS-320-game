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
    
    public void tick() {
        scoreKeep++;
//        levelKeep++;
        if (scoreKeep >= 10) {
            scoreKeep = 0;
            levelKeep++;
            hud.setLevel(hud.getLevel() + 1);
            
            if (levelKeep%2==0) {
                
                handler.addObject(new BasicEnemy(r.nextInt(WIDTH), 0, ItemID.BasicEnemy, handler));
            }
            if (levelKeep%5==0) {
               
                handler.addObject(new BigEnemy(r.nextInt(WIDTH), 0, ItemID.BasicEnemy, handler));
            }
            if (levelKeep%13==0) {
               
                handler.addObject(new MegaEnemy(r.nextInt(WIDTH), 0, ItemID.MegaEnemy, handler));
            }
        }
        
    }
    
}
