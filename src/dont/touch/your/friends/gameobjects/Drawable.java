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
	
	protected Drawable(){
		pos = new Vector2(0,0);
		rect = new Rectangle();
	}
	
	public BufferedImage getBI() {
		return bi;
	}
	
	public double getX(){
		return pos.getX();
	}
	
	public double getY(){
		return pos.getY();
	}	
	
	public void setPosition(float x, float y) {
		pos.set(x, y);
	}
	
	public void goLeft() {
		this.pos.move(-1,0);
	}
	
	public void goRight() {
		this.pos.move(1,0);
	}
	
	public void goUp() {
		this.pos.move(0,-1);
	}
	
	public void goDown() {
		this.pos.move(0,1);
	}
}
