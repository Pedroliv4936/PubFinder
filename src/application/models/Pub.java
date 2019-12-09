package application.models;

import application.models.DAO.DrinkDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Pub extends Entity {

	private String address;
	private ObservableList<DrinkForSale> drinks;
	private ObservableList<Image> images;
	private double rating;
	private double price;
	private PubType type;
	private Coordinates coordinates;
	private String openTime, closeTime;
	private boolean pending;
	private ObservableList<Pub> nearPubs = FXCollections.observableArrayList();

	public static final String DISCOTECA = "Discoteca";
	public static final String BAR = "Bar";
	public static final String SALAO_DE_JOGOS = "Salao de jogos";

	public Pub(int id, String name, PubType type, double price, double rating, String address, Coordinates coordinates, boolean pending) {
		super(id, name);
		this.price = price;
		this.type = type;
		this.rating = rating;
		this.address = address;
		this.coordinates = coordinates;
		this.pending = pending;
		drinks = FXCollections.observableArrayList();
	}	

	public String getPubInfo() {
		return name + " (" + type + ")" + " preco: " + price;
	}

	public double getPrice() {
		return price;
	}
	
	public double distance(Pub pub) {
		return coordinates.distanceFrom(pub.getCoordinates());
	}

	public double distance(Coordinates loc) {
		return coordinates.distanceFrom(loc);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public PubType getType() {
		return type;
	}

	public void setType(PubType type) {
		this.type = type;
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

	public ObservableList<DrinkForSale> getDrinks() {
		return DrinkDAO.getDrinks(this);
	}

	public void setDrinks(ObservableList<DrinkForSale> drinks) {
		this.drinks = drinks;
	}

	public ObservableList<Image> getImages() {
		return images;
	}

	public void setImages(ObservableList<Image> images) {
		this.images = images;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}


	public ObservableList<Pub> getNearPubs() {
		return nearPubs;
	}

	public void setNearPubs(ObservableList<Pub> nearPubs) {
		this.nearPubs = nearPubs;
	}
	
	public boolean isPending() {
		return pending;
	}
	
	public void aprove() {
		this.pending = false;
	}
}
