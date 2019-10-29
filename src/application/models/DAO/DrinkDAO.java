package application.models.DAO;

import application.models.Drink;
import application.models.DrinkForPub;
import application.models.Pub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DrinkDAO {

	private static ObservableList<DrinkForPub> drinksInPubsList = FXCollections.observableArrayList();
	
	private static ObservableList<Drink> drinkList = FXCollections.observableArrayList();

	private static ObservableList<DrinkForPub> pendingDrinkList = FXCollections.observableArrayList();
	

	public static ObservableList<DrinkForPub> getPendingDrinkList() {
		return pendingDrinkList;
	}

	public static ObservableList<DrinkForPub> getDrinksInPubs() {
		return drinksInPubsList;
	}
	
	public static void addDrinkFromPub(DrinkForPub drink) {
		drinksInPubsList.add(drink);
	}
	
	public static void removeDrinkFromPub(DrinkForPub drink) {
		drinksInPubsList.remove(drink);
	}
	
	public static ObservableList<Drink> getDrinkList() {
		return drinkList;
	}

	public static void setDrinkList(ObservableList<Drink> drinkList) {
		DrinkDAO.drinkList = drinkList;
	}

	public static void addPendingDrink(DrinkForPub drink) {
		pendingDrinkList.add(drink);
	}
	
	public static void aproveDrink(DrinkForPub drink) {
		drinksInPubsList.add(drink);
		pendingDrinkList.remove(drink);
	}
	
	public static void aproveDrinks(ObservableList<DrinkForPub> drinks) {
		drinksInPubsList.addAll(drinks);
		pendingDrinkList.removeAll(drinks);
	}
	
	static {
		drinkList.addAll(Drink.CANECA_CERVEJA, Drink.COPO_CERVEJA, Drink.VODKA, Drink.SIDRA, Drink.GIN);
		
		for(Pub pub:PubDAO.getPubList()) {
			drinksInPubsList.addAll(pub.getDrinks());
		}
	}
}
