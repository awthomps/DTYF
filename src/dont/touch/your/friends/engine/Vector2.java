package dont.touch.your.friends.engine;

public class Vector2 {
	private double v[];
	
	public Vector2() {
		this(0.0,0.0);
	}
	
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
	
	public Vector2 add(Vector2 b) {
		Vector2 result = new Vector2();
		for(int i = 0; i < 2; ++i) {
			result.v[i] = v[i] + b.v[i];
		}
		return result;
	}
	
	public Vector2 sub(Vector2 b) {
		Vector2 result = new Vector2();
		for(int i = 0; i < 2; ++i) {
			result.v[i] = v[i] - b.v[i];
		}
		return result;
	}
	
	public Vector2 scale(double s) {
		Vector2 result = new Vector2();
		for(int i = 0; i < 2; ++i) {
			result.v[i] = v[i]*s;
		}
		return result;
	}
	public void move(Vector2 b) {
		for(int i = 0; i < 2; ++i) {
			v[i] = v[i] + b.v[i];
		}
	}
	public void move(double x, double y){
		move(new Vector2(x,y));
	}

	public void set(double x, double y) {
		v[0] = x;
		v[1] = y;
	}
}
