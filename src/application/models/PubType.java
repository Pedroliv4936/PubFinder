package application.models;

/**
 * Tipos de pubs existentes na aplicacao
 * @author franco
 *
 */
public class PubType {
	
	private int id;
	private String name;
	
	public PubType(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	public String toString() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
