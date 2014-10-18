/**
 * 
 */
package dont.touch.your.friends.gameobjects;

import java.io.IOException;
import java.util.Random;

import dont.touch.your.friends.engine.Vector2;

/**
 * @author Zack
 *
 */
public class Rando extends Drawable{

	private Player myPlayer;
	private Player chasePlayer;
	private static final int minRandomTimer = 80;
	private static final int maxRandomTimer = 150;
	private int timeSinceRefresh = 0;
	private static final int SWARM_DISTANCE = 150;



	public Rando(int ic, Player myPlayer, Player chasePlayer, int startX, int startY) throws IOException{
		super();
		imageChunk = ic;
		this.myPlayer = myPlayer;
		this.chasePlayer = chasePlayer;
		pos.set(startX, startY);
		//this.Randomize();
	}

	public void Randomize(){
		Random randGen = new Random();
		for(int i = 0; i < STATE_COUNT; i++){
			state[i] = false;
		}
		state[randGen.nextInt(6)] = true;

	}


	public void drawMove(){

		boolean outOfBounds = false;
		Random randGen = new Random();
		
		Vector2 move = new Vector2(0,0);

		/* Check if we are out of bounds */
		if(myPlayer.getX() - getX() > SWARM_DISTANCE){
			state[LEFT] = false;
			state[RIGHT] = true;
			outOfBounds = true;
		}
		else if(getX() - myPlayer.getX() > SWARM_DISTANCE){
			state[LEFT] = true;
			state[RIGHT] = false;
			outOfBounds = true;
		}

		if(myPlayer.getY() - getY() > SWARM_DISTANCE){
			state[UP] = false;
			state[DOWN] = true;
			outOfBounds = true;
		}
		else if(getY() - myPlayer.getY() > SWARM_DISTANCE){
			state[UP] = true;
			state[DOWN] = false;
			outOfBounds = true;
		}

		if(outOfBounds){
			state[CHASE] = false;
			state[RUN] = false;
			timeSinceRefresh = 0;
		}
		
		/* If we aren't out of bounds, check to see if we want to randomize direction */
		if(!outOfBounds){
			timeSinceRefresh++;
			int rand = randGen.nextInt(maxRandomTimer - minRandomTimer) + minRandomTimer;
			if(timeSinceRefresh >= maxRandomTimer){
				Randomize();
			}
			else if(timeSinceRefresh >= rand){
				Randomize();
			}
			
		}
		
		
		/* Chase and run the PC on your team*/
		if(state[CHASE]){
			move = myPlayer.getVector().sub(pos);
			for(int i = 0; i < STATE_COUNT; i++){
				if(i != CHASE){
					state[i] = false;
				}
			}
		}
		if(state[RUN]){
			move = pos.sub(myPlayer.getVector());{
				for(int i = 0; i < STATE_COUNT; i++){
					if(i != RUN){
						state[i] = false;
					}
				}	
			}
		}

		
		/* Do the state logic */
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
