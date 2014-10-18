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

import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.directinput.DirectInputDevice;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputAxisEventListener;
import de.hardcode.jxinput.event.JXInputEventManager;
import dont.touch.your.friends.controller.ControllerAxisListener;
import dont.touch.your.friends.controller.ControllerButtonListener;
import dont.touch.your.friends.gameobjects.Drawable;
import dont.touch.your.friends.gameobjects.Player;

public class Game extends JFrame implements KeyListener, JXInputAxisEventListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferStrategy bs;
	private boolean quit;
	private List<Drawable> objects = new LinkedList<Drawable>();
	private Player playerOne;
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
		
		initJXInputControllers();
		
		quit = false;
		
		playerOne = new Player("res/test/sample image.png");
		
		objects.add(playerOne);
		
		gameLoop();
	}

	private void initJXInputControllers() {
		System.out.println("Loading Controllers");
		
		System.load(System.getProperty("user.dir") + "\\lib\\JXInput_0.3.4\\jxinput.dll");
		JXInputEventManager.setTriggerIntervall(50);
		System.out.println("Number of controllers detected: " + JXInputManager.getNumberOfDevices());
		for(int i = 0; i < JXInputManager.getNumberOfDevices(); ++i) {
			if(JXInputManager.getJXInputDevice(i).getName().equals("Controller (Xbox 360 For Windows)")) {
				DirectInputDevice xbox = new DirectInputDevice(i);
				for(int j = 0; j < 2; j++) {
					if(xbox.getAxis(j) != null) {
						new ControllerAxisListener(xbox.getAxis(j));
					}
				}
				new ControllerButtonListener(xbox.getButton(0));
			}
		}
	}
	
	private void gameLoop() {
		
		while(!quit) {
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
		//System.out.println(key.getKeyCode());
		switch(key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			quit = true;
			break;
		case KeyEvent.VK_LEFT:
			playerOne.goLeft();
			break;
		case KeyEvent.VK_DOWN:
			playerOne.goDown();
			break;
		case KeyEvent.VK_UP:
			playerOne.goUp();
			break;
		case KeyEvent.VK_RIGHT:
			playerOne.goRight();
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

	@Override
	public void changed(JXInputAxisEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
