package dont.touch.your.friends.gameobjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import dont.touch.your.friends.engine.Vector2;

public abstract class Drawable {
	public enum Type { Player1, Player2, Player3, Player4, Rando};
	
	protected BufferedImage bi;
	protected Rectangle rect;
	protected Vector2 pos;
	Type type;
	
	public BufferedImage getBI() {
		return bi;
	}
	
	public void setPosition(float x, float y) {
		pos.set(x, y);
	}
}
