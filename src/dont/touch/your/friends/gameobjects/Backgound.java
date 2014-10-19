package dont.touch.your.friends.gameobjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Backgound {
	BufferedImage bi;
	
	public Backgound(String uri) {
		try {
			bi = ImageIO.read(new File(uri));
		} catch (IOException e) {
			try {
				bi = ImageIO.read(new File("res/test/sample image.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void drawBackground(Graphics2D g, int width, int height) {
		g.drawImage(bi, 0, 0, width, height, null);
	}
}
