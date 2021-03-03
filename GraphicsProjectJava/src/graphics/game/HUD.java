package graphics.game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    
    public static int playerHealth = 100;
    private int greenValue = 255;
    private int score = 0;
    private int level = 1;
    
    HUD() {
        playerHealth = 100;
        score = 0;
        level = 1;
    }
    
    public void tick() {
        playerHealth = Game.clamp(playerHealth, 0, 100);
        greenValue = Game.clamp(greenValue, 0, 255);
        greenValue = playerHealth * 2;
        score++;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, playerHealth * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        
//        g.drawString("Score: " + score, 15, 64);
//        g.drawString("Level: " + level, 15, 80);
    }
    
    public void score(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return level;
    }
}
