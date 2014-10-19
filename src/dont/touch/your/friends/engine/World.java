package dont.touch.your.friends.engine;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import dont.touch.your.friends.gameobjects.Drawable;
import dont.touch.your.friends.gameobjects.Player;

public class World {
	private LinkedList<Drawable> objects;

	public World() {
		objects = new LinkedList<Drawable>();
	}

	public void add(Drawable d) {
		objects.add(d);
	}

	public void checkCollisions() {
		//Do a sweep to check collisions:
		for(Drawable d1 : objects) {
			for(Drawable d2 : objects) {
				//make sure they aren't the same exact object:
				if(d1 != d2 && d1.testCollision(d2)) {
					d1.handleCollision(d2);
				}
			}
		}
		
		LinkedList<Drawable> toDelete = new LinkedList<Drawable>();
		//do another sweep for individual objects to handle their own collisions:
		for(Drawable d : objects) {
			if(d.markedForDeletion()) toDelete.add(d);
		}
		
		//do a sweep to see if anything should be deleted finally:
		//TODO: also this is untested! :3
		if(toDelete.size() > 0) objects.removeAll(toDelete);
	}
	
	//Must be declared after all images are loaded
	public void initCollision() {
		for(Drawable d : objects) {
			d.initCollision();
		}
	}

	public void render(Graphics2D g) {
		
		for(Drawable obj : objects) {
			Rectangle rec = obj.getBounds();
			g.drawImage(obj.getBI(), (int) obj.getX(), (int) obj.getY(), (int) rec.getWidth(), (int) rec.getHeight(),null);
		}
	}

	/**
	 * for(int i = 0; i < NPC_COUNT; i++){
				oneNPC[i].drawMove(playerTwo.getVector());
			}
	 */

	public void drawMove() {
		for(Drawable d : objects) {
			d.drawMove();
		}
	}
}
