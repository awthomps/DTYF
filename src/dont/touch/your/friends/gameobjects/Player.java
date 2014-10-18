package dont.touch.your.friends.gameobjects;

import dont.touch.your.friends.engine.Vector2;

public class Player extends Drawable {
	
	// Determines if the player character is tagged
	private boolean isTagged;
	
	public Player(int ic){
		this(ic, 0, 0);
	}
	
	public Player(int ic, int startX, int startY) {
		super();
		isTagged = false;
		imageChunk = ic;
		
		// This is the position of the player.  Should be different per player
		pos.set(startX, startY);
	}
	public Vector2 getVector(){
		return pos;
	}
}
