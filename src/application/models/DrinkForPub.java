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
	
	public void showDrinkInfo() {
		System.out.println("Drink type " + drink.toString());
		System.out.println("Pub selling " + pub.toString());
		System.out.println("Rating " + getRating());
		System.out.println("Price " + getPrice());
	}

	public String toString() {
		return drink.toString() + " €" + price + "(" + pub.toString() + ")";
	}
	
	public Drink getDrinkType() {
		return drink;
	}
	
	public String getDrinkName() {
		return drink.toString();
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
