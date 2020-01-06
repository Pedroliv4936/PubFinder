package application.models;

/**
 * É a bebida que está a venda. Está associada com um Pub e um Drink.
 * 
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public class DrinkForSale {

	private int id;
	private Drink drink;
	private Pub pub;
	private double rating;
	private double price;
	private boolean pending;


	public DrinkForSale(int id, Drink drink, Pub pub, double rating, double price, boolean pending) {
		this.id = id;
		this.drink = drink;
		this.pub = pub;
		if (rating >= 0) {
			if (rating <= 5) {
				this.rating = rating;
			} else {
				this.rating = 5;
			}
		} else {
			this.rating = 0;
		}
		this.price = price;
		this.pending = pending;
	}

	/**
	 * Escreve toda informacao da bebida no Console
	 */
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

	/**
	 * Serve para descobrir o nome do tipo da bebida
	 * @return nome do Drink associado a esta bebida
	 */
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

	/**
	 * Busca a base de dados o rating da bebida
	 */
	public void refreshRating() {
		this.rating = DrinkDAO.getRating(this);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
