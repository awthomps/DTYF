package dont.touch.your.friends;


import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		JFrame frame = new JFrame("DTYF");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane();
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1280,720);
		Graphics2D  g = (Graphics2D) frame.getGraphics();
		BufferStrategy myStrategy = frame.getBufferStrategy();
		
		int i = 0;
		while(i != 100000000) {
			Graphics2D g2 = (Graphics2D) myStrategy.getDrawGraphics();			
			frame.update(g2);
			++i;
		}
	}
}
