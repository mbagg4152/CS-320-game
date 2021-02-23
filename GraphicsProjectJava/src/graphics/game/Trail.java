package graphics.game;

import graphics.game.gameitems.GameItems;
import graphics.game.gameitems.ItemID;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameItems {
    
    private float alpha = 1;
    private int width;
    private int height;
    private GameHandler handler;
    private Color color;
    private float life;
    
    public Trail(int x, int y, ItemID id, Color color, int width, int height, float life, GameHandler handler) {
        super(x, y, id);
        this.color = color;
        this.handler = handler;
        this.width = width;
        this.height = height;
        this.life = life;
    }
    
    public Color getColor() {
        return color;
    }
    
    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }
    
    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void tick() {
        // TODO Auto-generated method stub
        if (alpha > life)
            alpha -= life - 0.001f;
        else
            handler.removeObject(this);
    }
    
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        
        g.setColor(getColor());
        g.fillRect(x, y, width, height);
        
        g2d.setComposite(makeTransparent(1));
        
    }
    
}
