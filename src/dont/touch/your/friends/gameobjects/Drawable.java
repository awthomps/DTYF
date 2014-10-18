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
	
	public void goLeft(int magnitude) {
		this.pos.move(-magnitude,0);
	}
	
	public void goRight(int magnitude) {
		this.pos.move(magnitude,0);
	}
	
	public void goUp(int magnitude) {
		this.pos.move(0,-magnitude);
	}
	
	public void goDown(int magnitude) {
		this.pos.move(0,magnitude);
	}
	
	public void setRect(int x, int y) {
		rect = new Rectangle(x, y);
	}
	
	public void setRect(Rectangle r) {
		rect = r;
	}
	
	public boolean collidesWith(Drawable d) {
		if((pos.getX() + rect.width < d.pos.getX()) && (pos.getY() + rect.height < d.pos.getY())) return false;
		else if((pos.getX() + rect.width < d.pos.getX()) && (pos.getY() > d.pos.getY() + d.rect.height)) return false;
		else if((pos.getX() > d.pos.getX() + d.rect.width) && (pos.getY() > d.pos.getY() + d.rect.height)) return false;
		else if((pos.getX() > d.pos.getX() + d.rect.width) && (pos.getY() + rect.height < d.pos.getY())) return false;
		else return true;
	}

	public void handleCollision(Drawable d2) {
		System.out.println("Hey look, " + this + " collides with " + d2);
	}
}
