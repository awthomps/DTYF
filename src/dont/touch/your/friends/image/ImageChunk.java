package dont.touch.your.friends.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageChunk {
	private ArrayList<BufferedImage> images;
	
	public ImageChunk(String imageNames, int number) {
		images = new ArrayList<BufferedImage>();
		
		for(int i = 0; i < number; ++i) {
			try {
				images.add(ImageIO.read(new File(imageNames + i)));
			} catch (IOException e) {
				System.err.println("BRAH THE FILES ALL WRONG MAN: " + imageNames + i + ".png");
				try {
					images.add(ImageIO.read(new File("res/test/sample image.png")));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.exit(0);
				}
			}
		}
	}
	
	public int getSize() {
		return images.size();
	}
	
	public BufferedImage getImageByIndex(int index) {
		return images.get(index);
	}
	
	public void destroy() {
		images.clear();
	}
	
}
