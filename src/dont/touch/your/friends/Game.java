package dont.touch.your.friends;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import java.util.List;

import dont.touch.your.friends.gameobjects.Drawable;
import dont.touch.your.friends.gameobjects.Player;
import dont.touch.your.friends.gameobjects.Rando;

public class Game extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NPC_COUNT = 4;
	private BufferStrategy bs;
	private boolean quit;
	private List<Drawable> objects = new LinkedList<Drawable>();
	private Player playerOne;
	private Player playerTwo;
	
	private Rando[] oneNPC = new Rando[NPC_COUNT];
	

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
		playerTwo = new Player("res/test/sample image.png", this.getWidth() - 50, this.getHeight() - 50);
		for(int i = 0; i < NPC_COUNT; i++){
			oneNPC[i] = new Rando("res/test/sample image.png", playerOne, playerTwo, this.getWidth()/2, this.getHeight()/2);
			objects.add(oneNPC[i]);
		}
		
		
		objects.add(playerOne);
		objects.add(playerTwo);
		
		gameLoop();
	}
	
	private void gameLoop() {
		
		while(!quit) {
			drawStuff();
			
			playerOne.drawMove(null);
			playerTwo.drawMove(null);
			for(int i = 0; i < NPC_COUNT; i++){
				oneNPC[i].drawMove(playerTwo.getVector());
			}
			
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
