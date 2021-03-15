package graphics.game;

import graphics.game.gameitems.BasicEnemy;
import graphics.game.gameitems.ItemID;
import graphics.game.gameitems.Player;
import graphics.game.gameitems.Spawn;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static graphics.game.Const.*;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = 1442501350474703598L;
    private GameHandler gHandler;
    private HUD hud;
    private Random randObj;
    private Spawn spawner;
    private Thread thread;
    private static JButton startBtn;
    private static JFrame gameFrame;
    private static JLabel gameTitle;
    private static JPanel gamePanel;
    private static boolean running = false, playerDead = false;
    public static Color bgMain;
    public static final int WIDTH = 1600, HEIGHT = 900;

    private static GameFrame mainGameFrame;
    private static Player character;
    public static Clip hitSound;
    public static BufferedImage titleIcon, btnIcon;
    public static Image bgImage;

    public static Date startDate;
    public static String startStr;
    SimpleDateFormat sdf
            = new SimpleDateFormat(
            "dd-MM-yyyy HH:mm:ss");

    public static void main(String[] args) {
        assignObjectValues();
        startGameMenu();
    }

    private static void assignObjectValues() {
        bgMain = new Color(16, 26, 40);
        gameFrame = new JFrame("Start Menu");
        gamePanel = new JPanel(new GridLayout(0, 1, 10, 10));
        gameTitle = new JLabel("", JLabel.CENTER);
        startBtn = new JButton();
        try {
            bgImage = ImageIO.read(new File(IMG_BG));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            titleIcon = ImageIO.read(new File(IMG_TITLE));
            btnIcon = ImageIO.read(new File(IMG_START));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void startGameMenu() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // get screen size
        // set up the main menu frame
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(500, 500);
        gameFrame.setLocation(dim.width / 2 - 500 / 2, dim.height / 2 - 500 / 2); // center panel
        gameFrame.setLayout(new GridLayout(0, 1));
        gameFrame.getContentPane().setBackground(bgMain);

        // main menu panel
        gamePanel.setBorder(new EmptyBorder(5, 40, 50, 40));
        gamePanel.setBackground(bgMain);

        // main menu title
        gameTitle.setIcon(new ImageIcon(titleIcon));

        // create and change settings for the button
        startBtn.setBorderPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setIcon(new ImageIcon(btnIcon));
        startBtn.setFocusable(false);

        // add UI elements to panel and then to JFrame
        gamePanel.add(gameTitle);
        gamePanel.add(startBtn);
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game();
                gameFrame.dispose();
            }
        });
    }

    public Game() {
        gHandler = new GameHandler();
        hud = new HUD();
        spawner = new Spawn(gHandler, hud);
        this.addKeyListener(new KeyInput(gHandler));
        mainGameFrame = new GameFrame(WIDTH, HEIGHT, "Run! Dodge! Run!", this);
        randObj = new Random();
        character = new Player((WIDTH / 2) - 32, (HEIGHT / 2) - 32, ItemID.Player, gHandler);
        gHandler.addObject(character);
        gHandler.addObject(new BasicEnemy(randObj.nextInt(WIDTH), randObj
                .nextInt(HEIGHT), ItemID.BasicEnemy, gHandler));

        setDate(new Date());
        this.requestFocusInWindow();
    }


    public void setDate(Date date) {
        startStr = sdf.format(date);
    }

    public static String getDate() {
        return startStr;
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetGameVals() {
        gHandler = new GameHandler();
        hud = new HUD();
        spawner = new Spawn(gHandler, hud);
        this.addKeyListener(new KeyInput(gHandler));
        randObj = new Random();
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks, delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            if (isPlayerDead()) {
                gHandler.removeAll();
                GameFrame.deleteWindow();
                resetGameVals();
                assignObjectValues();
                startGameMenu();
                killPlayer(false);
            }
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) render();
            if (System.currentTimeMillis() - timer > 1000) timer += 1000;
        }
        stop();
    }

    private void tick() {
        gHandler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(COLOR_FELLA);


        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(bgImage, 0, 0, WIDTH, HEIGHT, null);
        gHandler.render(g);
        hud.render(g);
        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) return max;
        else if (var <= min) return min;
        else return var;
    }

    public static boolean isPlayerDead() {
        return playerDead;
    }

    public static void killPlayer(boolean state) {
        playerDead = state;
    }

    public static Rectangle getPlayerBounds() {
        return character.getBounds();
    }

}
