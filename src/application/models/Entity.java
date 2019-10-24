package application.models;

public abstract class Entity {
	protected int id;
	protected String name;
	protected double rating;
	
	public Entity(int id, String name, double rating) {
		this.id=id;
		this.name=name;
		this.rating= rating;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
	
	}
	
	public void rate(double rating) {
		this.rating=rating;
	}
	
	public double getRating() {
		return rating;
	}
}
