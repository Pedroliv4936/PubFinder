package application.models;

public class Coordinates {
	private double x, y;

	public Coordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double distanceFrom(Coordinates other) {
		return Math.sqrt(Math.pow(other.getX() - getX(), 2) + Math.pow(other.getY() + getY(), 2));
	}
}
