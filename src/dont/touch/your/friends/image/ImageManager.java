package dont.touch.your.friends.image;

import java.awt.Rectangle;
import java.util.ArrayList;



public class ImageManager {
	
	public static ImageManager imageManager = new ImageManager();
	
	
	/*
	 * Locations of each image:
	 */
	public static int PLAYER1IMAGE = 0;
	public static int PLAYER2IMAGE = 1;
	public static int RANDO = 2;
	
	private ArrayList<ImageChunk> chunks;
	
	public ImageManager() {
		chunks = new ArrayList<ImageChunk>();
	}
	
	public void add(String imageNames, int number, Rectangle bounds) {
		ImageChunk ic = new ImageChunk(imageNames, number, bounds);
		chunks.add(ic);
	}
	
	public void blowChunks() {
		for(ImageChunk ic : chunks) {
			ic.destroy();
		}
	}
	
	public ImageChunk getChunk(int index) {
		return chunks.get(index);
	}
	
	public int getSize() {
		return imageManager.getSize();
	}
}
