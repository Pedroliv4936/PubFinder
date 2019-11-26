package application.models;

public class DrinkForSale {

	private Drink drink;
	private Pub pub;
	private double rating;
	private double price;
	private boolean pending;

	public DrinkForSale(Drink drink, Pub pub, double rating, double price) {
		this.drink = drink;
		this.pub = pub;
		this.rating = rating;
		this.price = price;
		this.aprove();
	}
	
	public DrinkForSale(Drink drink, Pub pub, double rating, double price, boolean pending) {
		this.drink = drink;
		this.pub = pub;
		this.rating = rating;
		this.price = price;
		this.pending = pending;
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

	public boolean isPending() {
		return pending;
	}

	public void aprove() {
		this.pending = false;
	}
}
