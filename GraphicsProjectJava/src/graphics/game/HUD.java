package graphics.game;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HUD {

    public static int playerHealth = 100;
    private int greenValue = 255;
    private int score = 0;
    private int level = 1;
    Date now, start;
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static String thisTime;

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
        g.fillRect(15, 25, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 25, playerHealth * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 25, 200, 32);
        g.setFont(new Font("TimesRoman", Font.BOLD, 14));
        now = new Date();


        try {
            start = sdf.parse(Game.getDate());
        } catch (ParseException e) {
            start = new Date();
            e.printStackTrace();
        }

        long diff = now.getTime() - start.getTime();
        long ms = diff % 1000, sec = (diff / 1000) % 60, min = (diff / (1000 * 60)) % 60, hrs = (diff / (1000 * 60 * 60)) % 24;

        thisTime = hrs + " hr. " + min + " min. " + sec + "." + ms + " sec.";

        g.drawString("Health: " + playerHealth, 15, 15);
        g.drawString("Current time: " + thisTime, 230, 55);
        g.drawString("Past time: " + Game.getPastTime(), 230, 35);

        if (playerHealth <= 15) {
            System.out.println(thisTime);
            Game.setPastTime(thisTime);
        }
    }


    public static String getThisTime() {
        return thisTime;
    }

    public void setThisTime(String time) {
        thisTime = time;
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
