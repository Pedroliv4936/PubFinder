package application.models;

import javafx.collections.ObservableList;

public class FavoriteDrinkList {
	
	private String name;
	private ObservableList<Drink> drinks;

	public FavoriteDrinkList(String name, ObservableList<Drink> drinks) {
		this.name = name + " Favorite Drinks";
		this.drinks = drinks;
	}

	public ObservableList<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(ObservableList<Drink> drinks) {
		this.drinks = drinks;
	}

	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FavoriteDrinkList(String name) {
		this.name = name;
	}

	
}
