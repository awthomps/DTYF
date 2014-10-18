package dont.touch.your.friends.engine;

public class Vector2 {
	private double v[];

	public Vector2() {
		v = new double[2];
		set(0,0);
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

	public double magnitude(){
		return Math.sqrt(v[0] * v[0] + v[1] * v[1]);
	}

	public void normalize(){
		double magnitude = magnitude();

		if(magnitude != 0){
			v[0] = v[0] / magnitude;
			v[1] = v[1] / magnitude;
		}
	}
	public Vector2 add(Vector2 b) {
		Vector2 result = new Vector2();
		System.err.println("first vector from add is " + v[0] + " " + v[1]);
		System.err.println("second vector from add is " + b.v[0] + " " + b.v[1]);
		for(int i = 0; i < 2; ++i) {
			result.v[i] = v[i] + b.v[i];
		}

		System.err.println("result vector from add is " + result.v[0] + " " + result.v[1]);
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
<<<<<<< HEAD
	
	public String toString() {
		return "Vector2( " + v[0] + ", " + v[1] + ")";
	}
	public void print() {
		System.out.println(toString());
=======

	public void set(Vector2 vector){
		this.set(vector.v[0], vector.v[1]);
>>>>>>> origin/master
	}
}
