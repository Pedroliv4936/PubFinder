package application.models;

public class DrinkForPub {

	private Drink drink;
	private Pub pub;
	private double rating;
	private double price;

	public DrinkForPub(Drink drink, Pub pub, double rating, double price) {
		this.drink = drink;
		this.pub = pub;
		this.rating = rating;
		this.price = price;
	}

	public Drink getDrinkType() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public Pub getPub() {
		return pub;
	}

	public void setPub(Pub pub) {
		this.pub = pub;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
