package dont.touch.your.friends;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import java.util.List;

import dont.touch.your.friends.gameobjects.Drawable;
import dont.touch.your.friends.gameobjects.Player;

public class Game extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferStrategy bs;
	private boolean quit;
	private List<Drawable> objects = new LinkedList<Drawable>();
	private Player playerOne;
	private boolean[] playerOneState = new boolean[256];
	private int playerOneSpeed = 5;
	public Game() throws IOException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(1280,720);
		this.setVisible(true);
		this.createBufferStrategy(2);
		this.addKeyListener(this);
		this.setTitle("DTYF");
		//this.setBackground(Color.BLACK);
		bs = this.getBufferStrategy();
		
		quit = false;
		
		playerOne = new Player("res/test/sample image.png");
		
		objects.add(playerOne);
		
		gameLoop();
	}
	
	private void gameLoop() {
		
		while(!quit) {
			drawStuff();
			
			if(playerOneState[KeyEvent.VK_LEFT])
				playerOne.goLeft(playerOneSpeed);
			if(playerOneState[KeyEvent.VK_RIGHT])
				playerOne.goRight(playerOneSpeed);
			if(playerOneState[KeyEvent.VK_DOWN])
				playerOne.goDown(playerOneSpeed);
			if(playerOneState[KeyEvent.VK_UP])
				playerOne.goUp(playerOneSpeed);
			
			
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setVisible(false);
		this.dispose();
	}
	
	private void drawStuff() {
		Graphics2D g;
		try {
			g = (Graphics2D) bs.getDrawGraphics();
			render(g);
			g.dispose();
			bs.show();
		}
		catch( Exception e) {
			System.out.println(e.getCause());
		}
	}

	private void render(Graphics2D g) {
		for(Drawable obj : objects) {
			g.drawImage(obj.getBI(), (int) obj.getX(), (int) obj.getY(), Drawable.DRAWUNIT, Drawable.DRAWUNIT,null);
		}
	}
	
	
	public void keyPressed(KeyEvent key) {
		System.out.println(key);

		//System.out.println(key.getKeyCode());
		switch(key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			quit = true;
			break;
		default:
			playerOneState[key.getKeyCode()] = true;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent key) {
		switch(key.getKeyCode()) {
		
		}
	}
	
	
}
