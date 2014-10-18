package dont.touch.your.friends.engine;

public class Vector2 {
	private double v[];
	
	public Vector2(double x, double y) {
		v = new double[2];
		set(x,y);
	}
	
	public double getX() {
		return v[0];
	}
	public double getY() {
		return v[1];
	}
	
	public void move(double x, double y){
		v[0] = v[0] + x;
		v[1] = v[1] + y;
	}

	public void set(double x, double y) {
		v[0] = x;
		v[1] = y;
	}
}
