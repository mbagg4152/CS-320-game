package graphics.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1442501350474703598L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;

    private Random randObj;
    private Handler gameHandler;
    private HUD hud;
    private Spawn spawner;

    private boolean running = false;

    public Game() {
        gameHandler = new Handler();
        hud = new HUD();
        spawner = new Spawn(gameHandler, hud);
        this.addKeyListener(new KeyInput(gameHandler));

        new Window(WIDTH, HEIGHT, "MY FIRST GAME", this);

        randObj = new Random();

        gameHandler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, gameHandler));
        gameHandler.addObject(new BasicEnemy(randObj.nextInt(WIDTH), randObj.nextInt(HEIGHT), ID.BasicEnemy, gameHandler));


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
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) render();

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    private void tick() {
        gameHandler.tick();
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

        gameHandler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();

    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) return var = max;
        else if (var <= min) return var = min;
        else return var;
    }

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        new Game();
    }
}
