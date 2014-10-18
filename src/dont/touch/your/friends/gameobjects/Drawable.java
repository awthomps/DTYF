package dont.touch.your.friends.gameobjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dont.touch.your.friends.engine.Vector2;
import dont.touch.your.friends.image.ImageChunk;
import dont.touch.your.friends.image.ImageManager;

public abstract class Drawable {
	public enum Type { Player1, Player2, Player3, Player4, Rando};
	public static int DRAWUNIT = 50;

	public boolean[] state = new boolean[6];

	private Vector2 prevPos = new Vector2(0,0);
	
	public static final int LEFT = 0;
	public static final int DOWN = 1;
	public static final int UP = 2;
	public static final int RIGHT = 3;
	public static final int CHASE = 4;
	public static final int RUN = 5;
	
	
	private int speed = 5;
	protected int imageChunk;
	protected Rectangle rect;
	protected Vector2 pos;
	protected int currentFrame;
	Type type;

	protected Drawable(){
		pos = new Vector2(0,0);
		rect = new Rectangle();
		currentFrame = 0;
	}

	public BufferedImage getBI() {
		return ImageManager.imageManager.getChunk(imageChunk).getImageByIndex(currentFrame);
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

	public void drawMove(){
		Vector2 move = new Vector2(0,0);
		
		if(state[LEFT])
			move = move.add(new Vector2(-1,0));
		if(state[RIGHT])
			move = move.add(new Vector2(1,0));
		if(state[DOWN])
			move = move.add(new Vector2(0,1));
		if(state[UP])
			move = move.add(new Vector2(0,-1));
		
		if(move.magnitude() > 0) {
			prevPos.set(pos);
		}
		
		move.normalize();
		move = move.scale(speed);
		
		pos = pos.add(move);
		
	}
}
