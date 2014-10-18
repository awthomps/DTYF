package dont.touch.your.friends;

import java.io.IOException;



public class Main {
	public static void main(String args[]) {
		try {
			new Game();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
