package application.models;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Pub extends Entity{

	private String address;
	private ObservableList<Drink> drinks;
	private ObservableList<Image> images;
	private Pub pub;
	
	public Pub(int id, String name, double rating, String address,
					ObservableList<Drink> drinks, ObservableList<Image> images, Pub pub) {
		super(id, name, rating);
		this.address = address;
		this.drinks = drinks;
		this.images = images;
		this.pub = pub;
	}
	
	public void showPubIndo() {
			System.out.println("Id: " + id);
			System.out.println("Name" + name);
			System.out.println("Rating" + rating);
			System.out.println("Bar onde é vendido: " + pub);
	}

	public Pub getPub() {
		return pub;
	}
	
	public void setPub(Pub pub) {
		this.pub = pub;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ObservableList<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(ObservableList<Drink> drinks) {
		this.drinks = drinks;
	}

	public ObservableList<Image> getImages() {
		return images;
	}

	public void setImages(ObservableList<Image> images) {
		this.images = images;
	}
	
}
