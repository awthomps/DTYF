package dont.touch.your.friends;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.List;

import dont.touch.your.friends.gameobjects.Drawable;
import dont.touch.your.friends.gameobjects.Player;

public class Game extends JFrame implements KeyListener{
	private BufferStrategy bs;
	private boolean quit;
	private List<Drawable> objects = new LinkedList<Drawable>();
	public Game() throws IOException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setUndecorated(true);
		this.setSize(800,600);
		this.setVisible(true);
		this.createBufferStrategy(2);
		this.addKeyListener(this);
		this.setTitle("DTYF");
		//this.setBackground(Color.BLACK);
		bs = this.getBufferStrategy();
		quit = false;
		
		objects.add(new Player("res/test/sample image.png"));
		
		gameLoop();
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
		System.exit(0);
	}
	
	private void drawStuff() {
		Graphics2D g;
		try {
			g = (Graphics2D) bs.getDrawGraphics();
			render(g);
			
			g.drawRect(3, 3, 100, 100);
			g.dispose();
			bs.show();
		}
		catch( Exception e) {
			System.out.println(e.getCause());
		}
	}

	private void render(Graphics2D g) {
		for(Drawable obj : objects) {
			g.drawImage(obj.getBI(), 0, 0,null);
		}
	}
	
	
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0);
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			quit = true;
			System.out.println("Now Exiting...");
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}
