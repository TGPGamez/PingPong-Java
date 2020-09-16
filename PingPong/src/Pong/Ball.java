package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	double xVel, yVel, x, y;
	
	
	public Ball() {
		 x = Tennis.WIDTH / 2;
		 y = Tennis.HEIGHT / 2;
		 xVel = getRandomSpeed();
		 yVel = getRandomDirection();
	}
	
	public double getRandomSpeed() {
		double toReturn = (Math.random() * 3 + 4);
		if(toReturn > 7)
			return 5;
		else
			return toReturn;
	}
	
	public int getRandomDirection() {
		int rand = (int)(Math.random() * 2);
		if(rand == 1)
			return 1;
		else
			return -1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x-10, (int)y-10, 20, 20);
	}
	
	public void checkPaddleCollision(Paddle p1, Paddle p2, Ball b1) {
		if(x >= 49 && x < 51) {
			if(y >= p1.getY() && y <= p1.getY() + 80)
				xVel = -xVel;
		}
		if(x >= (Tennis.WIDTH - 52) && x < (Tennis.WIDTH - 49)) {
			if(y >= p2.getY() && y <= p2.getY() + 80) {
				xVel = -xVel;
			}
		}
	}
	
	public void move() {
		x += xVel;
		y += yVel;
		
		if(y < 10)
			yVel = -yVel;
		if(y > Tennis.HEIGHT - 10)
			yVel = -yVel;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}

}
