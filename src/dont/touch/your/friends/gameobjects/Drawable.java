package dont.touch.your.friends.gameobjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import dont.touch.your.friends.engine.Vector2;

public abstract class Drawable {
	protected BufferedImage bi;
	protected Rectangle rect;
	Vector2 pos;
	
	public BufferedImage getBI() {
		return bi;
	}
	
	public void setPosition(float x, float y) {
		pos.set(x, y);
	}
}
