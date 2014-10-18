package dont.touch.your.friends.gameobjects;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Drawable {
	
	public Player(String uri) throws IOException {
		bi = ImageIO.read(new File(uri));
		System.out.println(bi);
	}
}
