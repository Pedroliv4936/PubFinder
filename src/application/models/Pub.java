package application.models;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Pub extends Entity{

	private String address;
	private ObservableList<DrinkForPub> drinks;
	private ObservableList<Image> images;
	private double rating;
	private double price;
	private String type;
	
	public static final String DISCOTECA = "Discoteca";
	public static final String BAR = "Bar";
	public static final String SALAO_DE_JOGOS = "Salao de jogos";
	
	public Pub(int id, String name, String type, double price, double rating, String address,
					ObservableList<DrinkForPub> drinks, ObservableList<Image> images) {
		super(id, name);
		this.price = price;
		this.type = type;
		this.rating = rating;
		this.address = address;
		this.drinks = drinks;
		this.images = images;
	}
	
	public void showPubInfo() {
			System.out.println("Id: " + id);
			System.out.println("Name: " + name);
			System.out.println("Rating: " + rating);
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ObservableList<DrinkForPub> getDrinks() {
		return drinks;
	}

	public void setDrinks(ObservableList<DrinkForPub> drinks) {
		this.drinks = drinks;
	}

	public ObservableList<Image> getImages() {
		return images;
	}

	public void setImages(ObservableList<Image> images) {
		this.images = images;
	}
	
}
