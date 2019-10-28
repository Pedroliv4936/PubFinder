package application.models;

import javafx.scene.image.Image;

public class Drink extends Entity{
	private Image icon;
	
	public static final Drink VODKA = new Drink(0, "Vodka", null);
	public static final Drink COPO_CERVEJA = new Drink(1, "Copo de cerveja", null);
	public static final Drink CANECA_CERVEJA = new Drink(2, "Caneca de Cerveja", null);
	public static final Drink SIDRA = new Drink(3, "Sidra", null);
	public static final Drink GIN = new Drink(4, "Gin", null);
	
	private Drink(int id, String name, Image icon) {
		super(id, name);
		this.icon = icon;
	}
	
	public void showDrinkInfo() {
		System.out.println("Id: " + id);
		System.out.println("Name" + name);
		System.out.println("Image path" + icon);
	}
	

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}
 
}