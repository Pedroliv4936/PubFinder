package application.models;

import javafx.scene.image.Image;

/**
 * É uma Entity que serve como um tipo de bebida para uma DrinkForSale. 
 * @author franc
 *
 */
public class Drink extends Entity{
	private Image icon;
	
	public static final Drink VODKA = new Drink(0, "Vodka", null);
	public static final Drink COPO_CERVEJA = new Drink(1, "Copo de cerveja", null);
	public static final Drink CANECA_CERVEJA = new Drink(2, "Caneca de Cerveja", null);
	public static final Drink SIDRA = new Drink(3, "Sidra", null);
	public static final Drink GIN = new Drink(4, "Gin", null);
	
	public Drink(int id, String name, Image icon) {
		super(id, name);
		this.icon = icon;
	}
	
	/*
	 * Escreve no Console as informacoes deste Drink
	 */
	public void printDrinkInfo() {
		System.out.println("Id: " + id);
		System.out.println("Name" + name);
		System.out.println("Image path" + icon);
	}
	
	
	public String toString() {
		return name;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}
 
}