package dont.touch.your.friends.engine;

import java.awt.Graphics2D;
import java.util.LinkedList;

import dont.touch.your.friends.gameobjects.Drawable;
import dont.touch.your.friends.gameobjects.Player;
import dont.touch.your.friends.gameobjects.Rando;

public class World {
	private LinkedList<Drawable> objects;
	
	public World() {
		objects = new LinkedList<Drawable>();
	}
	
	public void add(Drawable d) {
		objects.add(d);
	}
	
	public void checkCollisions() {
		LinkedList<Drawable> toDelete = new LinkedList<Drawable>();
		
		for(Drawable d1 : objects) {
			for(Drawable d2 : objects) {
				//make sure they aren't the same exact object:
				if(d1 != d2) {
					if(d1.collidesWith(d2)) {
						d1.handleCollision(d2);
					}
					
				}
			}
		}
	}
	
	public void render(Graphics2D g) {
		for(Drawable obj : objects) {
			g.drawImage(obj.getBI(), (int) obj.getX(), (int) obj.getY(), Drawable.DRAWUNIT, Drawable.DRAWUNIT,null);
		}
	}
	
	public void drawMove() {
		for(Drawable d : objects) {
			d.drawMove();
		}
	}
}
