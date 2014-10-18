package dont.touch.your.friends.engine;

import java.util.LinkedList;

import dont.touch.your.friends.gameobjects.Drawable;

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
				if(!d1.equals(d2)) {
					
					
					
				}
			}
		}
	}
}
