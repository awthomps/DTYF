package dont.touch.your.friends;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import java.util.List;

import dont.touch.your.friends.engine.World;
import dont.touch.your.friends.gameobjects.Drawable;
import dont.touch.your.friends.gameobjects.Player;
import dont.touch.your.friends.image.ImageManager;

public class Game extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferStrategy bs;
	private boolean quit;
	private World world;
	private Player playerOne;
	private Player playerTwo;

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
		
		loadGraphics();
		
		initWorld();
		
		gameLoop();
	}

	private void loadGraphics() {
		ImageManager.imageManager.add("", 1);
		ImageManager.imageManager.add("", 1);
		
	}

	private void initWorld() throws IOException {
		world = new World();
		
		playerOne = new Player(ImageManager.PLAYER1IMAGE);
		playerTwo = new Player(ImageManager.PLAYER2IMAGE, this.getWidth() - 50, 0);
		
		world.add(playerOne);
		world.add(playerTwo);
	}
	
	private void gameLoop() {
		
		while(!quit) {
			world.drawMove();

			drawStuff();
			
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
			world.render(g);
			g.dispose();
			bs.show();
		}
		catch( Exception e) {
			System.out.println(e.getCause());
		}
	}

	
	
	
	public void keyPressed(KeyEvent key) {
		System.out.println(key);

		//System.out.println(key.getKeyCode());
		switch(key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			quit = true;
			break;
		case KeyEvent.VK_W:
			playerOne.state[Drawable.UP] = true;
			break;
		case KeyEvent.VK_A:
			playerOne.state[Drawable.LEFT] = true;
			break;
		case KeyEvent.VK_S:
			playerOne.state[Drawable.DOWN] = true;
			break;
		case KeyEvent.VK_D:
			playerOne.state[Drawable.RIGHT] = true;
			break;
		case KeyEvent.VK_UP:
			playerTwo.state[Drawable.UP] = true;
			break;
		case KeyEvent.VK_DOWN:
			playerTwo.state[Drawable.DOWN] = true;
			break;
		case KeyEvent.VK_LEFT:
			playerTwo.state[Drawable.LEFT] = true;
			break;
		case KeyEvent.VK_RIGHT:
			playerTwo.state[Drawable.RIGHT] = true;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		//System.out.println(key.getKeyCode());
		switch(key.getKeyCode()) {
		case KeyEvent.VK_W:
			playerOne.state[Drawable.UP] = false;
			break;
		case KeyEvent.VK_A:
			playerOne.state[Drawable.LEFT] = false;
			break;
		case KeyEvent.VK_S:
			playerOne.state[Drawable.DOWN] = false;
			break;
		case KeyEvent.VK_D:
			playerOne.state[Drawable.RIGHT] = false;
			break;
		case KeyEvent.VK_UP:
			playerTwo.state[Drawable.UP] = false;
			break;
		case KeyEvent.VK_DOWN:
			playerTwo.state[Drawable.DOWN] = false;
			break;
		case KeyEvent.VK_LEFT:
			playerTwo.state[Drawable.LEFT] = false;
			break;
		case KeyEvent.VK_RIGHT:
			playerTwo.state[Drawable.RIGHT] = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent key) {
		switch(key.getKeyCode()) {
		
		}
	}
	
	
}
