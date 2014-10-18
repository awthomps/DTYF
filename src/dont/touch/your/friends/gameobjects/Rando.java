/**
 * 
 */
package dont.touch.your.friends.gameobjects;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Zack
 *
 */
public class Rando extends Drawable{
	
	private Player myPlayer;
	private Player chasePlayer;

	public Rando(String uri, Player myPlayer, Player chasePlayer, int startX, int startY) throws IOException{
		bi = ImageIO.read(new File(uri));
		this.myPlayer = myPlayer;
		this.chasePlayer = chasePlayer;
		pos.set(startX, startY);
		this.Randomize();
	}
	
	public void Randomize(){
		for(int i = 0; i < STATE_COUNT; i++){
			state[i] = false;
		}
		state[(int) (System.currentTimeMillis() % 6)] = true;
		
	}
}
