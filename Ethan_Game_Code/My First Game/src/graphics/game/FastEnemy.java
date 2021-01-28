package graphics.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

	private Handler handler;

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		velX = 9;
		velY = 9;
		
		this.handler = handler;
				
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		if(y<= 0 || y>= Game.HEIGHT -32) velY *= -1;
		if(x<= 0 || x>= Game.WIDTH -32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 10, 10, 0.01f, handler));
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillRect(x, y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,10,10);
	}

}
