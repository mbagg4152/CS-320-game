package graphics.game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    
    @Serial private static final long serialVersionUID = 1442501350474703598L;
    public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
    private static JFrame gameFrame;
    private static JButton startBtn;
    private static JLabel gameTitle;
    private static JPanel gamePanel;
    private Thread thread;
    private Random randObj;
    private GameHandler gHandler;
    private HUD hud;
    private Spawn spawner;
    private boolean running = false;
    public static Color bgMain, fgBtn, bgBtn, fgTitle;
    private static boolean playerDead = false;
    private static GameFrame mainGameFrame;
    
    public static void main(String[] args) {
        assignObjectValues();
        startGameMenu();
    }
    
    private static void assignObjectValues() {
        bgMain = new Color(28, 28, 28);
        fgBtn = new Color(0, 50, 104);
        bgBtn = new Color(134, 134, 134);
        fgTitle = new Color(13, 145, 252);
        gameFrame = new JFrame("Run! Dodge! Run! - Main Menu");
        gamePanel = new JPanel(new GridLayout(0, 1, 10, 10));
        gameTitle = new JLabel("Run! Dodge! Run!", JLabel.CENTER);
        startBtn = new JButton("Click here to start the game!");
    }
    
    private static void startGameMenu() {
        // set up the main menu frame
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(500, 500);
        gameFrame.setLayout(new GridLayout(0, 1));
        gameFrame.getContentPane().setBackground(bgMain);
        
        // main menu panel
        gamePanel.setBorder(new EmptyBorder(5, 40, 50, 40));
        gamePanel.setBackground(bgMain);
        
        // main menu title
        gameTitle.setForeground(fgTitle);
        gameTitle.setFont(new Font("", Font.PLAIN, 40));
        
        // create and change settings for the button
        startBtn.setForeground(fgBtn);
        startBtn.setBackground(bgBtn);
        startBtn.setBorder(new LineBorder(bgMain, 60));
        startBtn.setFocusPainted(false);
        startBtn.setBounds(100, 100, 100, 100);
        startBtn.setFont(new Font("", Font.PLAIN, 18));
        
        // add UI elements to panel and then to JFrame
        gamePanel.add(gameTitle);
        gamePanel.add(startBtn);
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        
        startBtn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
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
        
        gHandler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, gHandler));
        gHandler.addObject(new BasicEnemy(randObj.nextInt(WIDTH), randObj.nextInt(HEIGHT), ID.BasicEnemy, gHandler));
        
        this.requestFocusInWindow();
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
    
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            if (getPlayerDead()) {
                gHandler.removeAll();
                mainGameFrame = null;
                hud = null;
                spawner = null;
                randObj = null;
                GameFrame.deleteWindow();
                startGameMenu();
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
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
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
    
    public static boolean getPlayerDead() {
        return playerDead;
    }
    
    public static void setPlayerDead(boolean state) {
        playerDead = state;
    }
}
