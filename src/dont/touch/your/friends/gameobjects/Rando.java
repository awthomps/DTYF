/**
 * 
 */
package dont.touch.your.friends.gameobjects;

/**
 * @author Zack
 *
 */
public class Rando extends Drawable{
	public static final int PATH_AMOUNT = 6;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int CHASE = 4;
	public static final int RUN = 5;
	
	private int path;
	private int type;

	// rando goes left
	private void left(){
		path = 0;
	}

	private void right(){
		path = 1;
	}

	private void up(){
		path = 2;
	}

	private void down(){
		path = 3;
	}

	private void chase(){
		path = 4;
	}

	private void run(){
		path = 5;
	}

	public void setPath(int path){
		switch(path){
		case 0:
			left();
			break;
		case 1:
			right();
			break;
		case 2:
			up();
			break;
		case 3:
			down();
			break;
		case 4:
			chase();
			break;
		case 5:
			run();
			break;
		}
	}

	public int getPath(){
		return path;
	}

	public int getType(){
		return type;
	}
	
	public Rando(int path, int type){
		this.path = path;
		this.type = type;
	}
}
