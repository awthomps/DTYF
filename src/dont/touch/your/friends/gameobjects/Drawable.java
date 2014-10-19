package dont.touch.your.friends.gameobjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dont.touch.your.friends.engine.Vector2;
import dont.touch.your.friends.image.ImageManager;

public abstract class Drawable {
	public enum Type { Player1, Player2, Player3, Player4, Rando};
	public static int DRAWUNIT = 50;

	public boolean[] state = new boolean[6];

	protected Vector2 prevPos = new Vector2(0,0);
	
	protected static final int STATE_COUNT = 5;
	
	public static final int LEFT = 0;
	public static final int DOWN = 1;
	public static final int UP = 2;
	public static final int RIGHT = 3;
	public static final int CHASE = 4;
	public static final int RUN = 5;
	
	
	protected int speed = 5;
	protected int imageChunk;
	protected Rectangle rect;
	protected Vector2 pos;
	protected int currentFrame;

	Type type;

	private boolean toDelete;

	protected Drawable(){
		pos = new Vector2(0,0);
		rect = new Rectangle();
		currentFrame = 0;
		toDelete = false;
	}
	
	private void whichOneDirection() {
		Vector2 dir = pos.sub(prevPos);
		
		if(dir.getX() > 0) {
			if(dir.getY() > 0) {
				//1
				currentFrame = 1;
			}
			else if(dir.getY() < 0) {
				//3
				currentFrame = 3;
			}
			else if(dir.getY() == 0) {
				//2
				currentFrame = 2;
			}
		}
		else if(dir.getX() < 0) {
			if(dir.getY() > 0) {
				//7
				currentFrame = 7;
			}
			else if(dir.getY() < 0) {
				//5
				currentFrame = 5;
			}
			else if(dir.getY() == 0) {
				//6
				currentFrame = 6;
			}
		}
		else if(dir.getY() < 0) {
			//4
			currentFrame = 4;
		}
		else if(dir.getY() > 0) {
			//0
			currentFrame = 0;
		}
	}
	
	public Rectangle getBounds() { return rect; }
	
	public void initCollision() {
		rect = ImageManager.imageManager.getChunk(imageChunk).getBounds();
	}

	public BufferedImage getBI() {
		whichOneDirection();
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

	public boolean testCollision(Drawable d) {
		Rectangle r1 = new Rectangle(rect);
		r1.setLocation((int) pos.getX(),(int) pos.getY());
		
		Rectangle r2 = new Rectangle(d.rect);
		r2.setLocation((int) d.pos.getX(),(int) d.pos.getY());
		
		if(r1.intersects(r2)) return true;
		else return false;
	}

	public void handleCollision(Drawable d2) {
		/**
		 * TODO: handle what to do for each object. This can be overwritten in child classes
		 */
		
		if(this instanceof Player && d2 instanceof Player)
			System.out.println("Hey look, " + this + " collides with " + d2);
	}
	
	public boolean markedForDeletion() {
		return toDelete;
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
