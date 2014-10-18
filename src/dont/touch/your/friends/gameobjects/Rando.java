/**
 * 
 */
package dont.touch.your.friends.gameobjects;

import java.io.IOException;

import dont.touch.your.friends.engine.Vector2;

/**
 * @author Zack
 *
 */
public class Rando extends Drawable{
	
	private Player myPlayer;
	private Player chasePlayer;
	private static final int SWARM_DISTANCE = 100;

	
	
	public Rando(int ic, Player myPlayer, Player chasePlayer, int startX, int startY) throws IOException{
		super();
		imageChunk = ic;
		this.myPlayer = myPlayer;
		this.chasePlayer = chasePlayer;
		pos.set(startX, startY);
		//this.Randomize();
	}
	
	public void Randomize(){
		for(int i = 0; i < STATE_COUNT; i++){
			state[i] = false;
		}
		state[(int) (System.currentTimeMillis() % 6)] = true;
		
	}
	
	
	public void drawMove(Vector2 chaseVector){
		
		if(myPlayer.getX() - getX() > SWARM_DISTANCE){
			state[LEFT] = false;
			state[RIGHT] = true;
		}
		else if(getX() - myPlayer.getX() > SWARM_DISTANCE){
			state[LEFT] = true;
			state[RIGHT] = false;
		}
		
		if(myPlayer.getY() - getY() > SWARM_DISTANCE){
			state[UP] = false;
			state[DOWN] = true;
		}
		else if(getY() - myPlayer.getY() > SWARM_DISTANCE){
			state[UP] = true;
			state[DOWN] = false;
		}
		
		super.drawMove(chaseVector);
	}
}
