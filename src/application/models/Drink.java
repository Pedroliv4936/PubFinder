package application.models;

import javafx.scene.image.Image;

public class Drink extends Entity{

	private double price;
	private Image icon;
	private Pub pub;
	private String pubName;
	
	public Drink(int id, String name, double rating, double price, Image icon, Pub pub) {
		super(id, name, rating);
		this.price = price;
		this.icon = icon;
		this.pub = pub;
		pubName = pub.getName();
	}
	
	public void showDrinkInfo() {
		System.out.println("Id: " + id);
		System.out.println("Name" + name);
		System.out.println("Rating" + rating);
		System.out.println("Price" + price);
		System.out.println("Image path" + icon);
		System.out.println("Bar onde é vendido: " + pubName);
	}
	
	public Pub getPub() {
		return pub;
	}

	public void setPub(Pub pub) {
		this.pub = pub;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}
 
}