package application.models;

/**
 * Classe generica para entidades que possuam nome e ID.
 * @author Franco Zalamena & Pedro Oliveira
 *
 */
public abstract class Entity {
	protected int id;
	protected String name;
	
	public Entity(int id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return name;
	}
	
	public void setName(String name) {
	this.name = name;
	}
}
