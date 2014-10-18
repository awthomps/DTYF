package dont.touch.your.friends.image;

import java.util.ArrayList;



public class ImageManager {
	
	public static ImageManager imageManager = new ImageManager();
	
	
	/*
	 * Locations of each image:
	 */
	public static int PLAYER1IMAGE = 0;
	public static int PLAYER2IMAGE = 1;
	
	private ArrayList<ImageChunk> chunks;
	
	public ImageManager() {
		chunks = new ArrayList<ImageChunk>();
	}
	
	public void add(String imageNames, int number) {
		ImageChunk ic = new ImageChunk(imageNames, number);
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
}
