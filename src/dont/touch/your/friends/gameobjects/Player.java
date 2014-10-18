package dont.touch.your.friends.gameobjects;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Drawable {
	
	// Determines if the player character is tagged
	private boolean isTagged;
	
	public Player(String uri) throws IOException {
		super();
		isTagged = false;
		bi = ImageIO.read(new File(uri));
		
		// This is the position of the player.  Should be different per player
		pos.set(0, 0);
	}
}
