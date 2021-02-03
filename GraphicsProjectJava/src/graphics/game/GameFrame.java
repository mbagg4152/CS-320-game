package graphics.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.Serial;

import javax.swing.JFrame;

public class GameFrame extends Canvas {
    
    @Serial private static final long serialVersionUID = -1788956338001533907L;
    static JFrame frame;
    
    public GameFrame(int width, int height, String title, Game game) {
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
        
    }
    
    public static void deleteWindow() {
        frame.dispose();
    }
}
