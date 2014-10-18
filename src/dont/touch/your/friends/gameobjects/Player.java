package dont.touch.your.friends.gameobjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Drawable {
	private BufferedImage image;
	
	public Player(String uri) throws IOException {
		image = ImageIO.read(new File(uri));
	}
	
	public BufferedImage getBufferedImage() {
		return image;
	}

	@Override
	public BufferedImage getBI() {
		// TODO Auto-generated method stub
		return null;
	}
}
