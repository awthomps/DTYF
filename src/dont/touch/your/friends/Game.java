package dont.touch.your.friends;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

import dont.touch.your.friends.engine.World;
import dont.touch.your.friends.gameobjects.Backgound;
import dont.touch.your.friends.gameobjects.Drawable;
import dont.touch.your.friends.gameobjects.Player;
import dont.touch.your.friends.image.ImageManager;
import dont.touch.your.friends.gameobjects.Rando;


public class Game extends JFrame implements KeyListener{
	/**
	 * 
	 */
	
	private static long frameTime = 17;
	private static final long serialVersionUID = 1L;
	private static final int NPC_COUNT = 30;
	private BufferStrategy bs;
	private boolean quit;
	private World world;
	private Backgound background;
	private Player playerOne;
	private Player playerTwo;
	
	private Rando[] oneNPC = new Rando[NPC_COUNT];
	private Rando[] twoNPC = new Rando[NPC_COUNT];
	

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
		ImageManager.imageManager.add("res/characters/hair/Hair1_", 8, new Rectangle(Drawable.DRAWUNIT, (int) (Drawable.DRAWUNIT*1.5)));
		ImageManager.imageManager.add("res/characters/hair/Hair2_", 8, new Rectangle(Drawable.DRAWUNIT, (int) (Drawable.DRAWUNIT*1.5)));
		ImageManager.imageManager.add("res/characters/hair/Hair3_", 8, new Rectangle(Drawable.DRAWUNIT, (int) (Drawable.DRAWUNIT*1.5)));
	}

	private void initWorld() throws IOException {
		background = new Backgound("backgrounds/Parking_Lot_Backdrop.png");
		world = new World();
		
		playerOne = new Player(ImageManager.PLAYER1IMAGE);
		playerTwo = new Player(ImageManager.PLAYER2IMAGE, this.getWidth() - 50, 0);
		
		
		world.add(playerOne);
		world.add(playerTwo);
		
		for(int i = 0; i < NPC_COUNT; i++){
			oneNPC[i] = new Rando(ImageManager.RANDO, playerOne, playerTwo, this.getWidth()/2, this.getHeight()/2);
			world.add(oneNPC[i]);
		}
		
		for(int i = 0; i < NPC_COUNT; i++){
			twoNPC[i] = new Rando(ImageManager.RANDO, playerTwo, playerOne, this.getWidth()/2, this.getHeight()/2);
			world.add(twoNPC[i]);
		}
		
		world.initCollision();
	}
	
	private void gameLoop() {
		long startTime = 0;
		while(!quit) {
			startTime = System.currentTimeMillis();
			world.drawMove();
			world.checkCollisions();
			drawStuff();
			
			try {
				
				
				long sleepTime = frameTime - (System.currentTimeMillis() - startTime);
				if(sleepTime < 0) sleepTime = 0;
				Thread.sleep(sleepTime);
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
			background.drawBackground(g, this.getWidth(), this.getHeight());
			world.render(g);
			g.dispose();
			bs.show();
		}
		catch( Exception e) {
			System.out.println(e.getCause());
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
