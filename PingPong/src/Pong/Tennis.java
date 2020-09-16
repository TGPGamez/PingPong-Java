package Pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {
	
	public static final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	HumanPaddle p1;
	HumanPaddle p2;
	Ball b1;
	boolean gameStarted;
	boolean gameEnded;
	Graphics gfx;
	Image img;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);
		
		gameStarted = false;
		gameEnded = false;
		this.addKeyListener(this);
		p1 = new HumanPaddle(1);
		p2 = new HumanPaddle(2);
		b1 = new Ball();
		
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		
		thread = new Thread(this);
		thread.start();
	}
	
	private void restartGame() {		
		gameStarted = false;
		gameEnded = false;
		p1 = new HumanPaddle(1);
		p2 = new HumanPaddle(2);
		b1 = new Ball();		
	}
	
	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		if(b1.getX() < -10 || b1.getX() > (WIDTH + 10)) {
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", WIDTH / 2, HEIGHT / 2);
			gfx.drawString("Press ENTER to restart", WIDTH / 2 , HEIGHT / 2 + 30);
			gameEnded = true;
		}
		else {
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
		}
		if(!gameStarted) {
			gfx.setColor(Color.white);
			gfx.drawString("Ping pong", WIDTH / 2, HEIGHT / 2);
			gfx.drawString("Press ENTER to begin..", WIDTH / 2 , HEIGHT / 2 + 30);
		}
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	
	public void run() {
		for(;;) {
			if(gameStarted) {
				p1.move();
				p2.move();
				b1.move();
				b1.checkPaddleCollision(p1,  p2, b1);
			}
			
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && gameStarted == false) {
			gameStarted = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER && gameEnded == true) {
			restartGame();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			p1.setUpAccel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			p1.setDownAccel(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p2.setUpAccel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setDownAccel(true);
		}

		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			p1.setUpAccel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			p1.setDownAccel(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p2.setUpAccel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setDownAccel(false);
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
