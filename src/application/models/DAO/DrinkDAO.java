package application.models.DAO;

import application.models.Drink;
import application.models.DrinkForPub;
import application.models.Pub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DrinkDAO {

	private static ObservableList<DrinkForPub> drinksInPubs = FXCollections.observableArrayList();
	
	private static ObservableList<Drink> drinkList = FXCollections.observableArrayList();

	private static ObservableList<DrinkForPub> pendingDrinkList = FXCollections.observableArrayList();
	

	public static ObservableList<DrinkForPub> getPendingDrinkList() {
		return pendingDrinkList;
	}

	public static ObservableList<DrinkForPub> getDrinksInPubs() {
		return drinksInPubs;
	}
	
	public static Drink getDrinkTypeByName(String drinkName) {
		for(Drink drink : drinkList) {
			if(drinkName.equals(drink.toString())) {
				return drink;
			}
		}
		return null;
	}
	
	public static Drink getDrinkTypeById(int id) {
		for(Drink drink : drinkList) {
			if(id == drink.getId()) {
				return drink;
			}
		}
		return null;
	}
	
	public static void addDrinkFromPub(DrinkForPub drink) {
		drinksInPubs.add(drink);
	}
	
	public static void removeDrinkFromPub(DrinkForPub drink) {
		drinksInPubs.remove(drink);
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
		drinksInPubs.add(drink);
		pendingDrinkList.remove(drink);
	}
	
	public static void aproveDrinks(ObservableList<DrinkForPub> drinks) {
		drinksInPubs.addAll(drinks);
		pendingDrinkList.removeAll(drinks);
	}
	
	static {
		drinkList.addAll(Drink.CANECA_CERVEJA, Drink.COPO_CERVEJA, Drink.VODKA, Drink.SIDRA, Drink.GIN);
		
		ObservableList<DrinkForPub> pedroDrinks = FXCollections.observableArrayList();
		ObservableList<DrinkForPub> francoDrinks = FXCollections.observableArrayList();
		
		pedroDrinks.addAll(new DrinkForPub(Drink.VODKA, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.COPO_CERVEJA, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.CANECA_CERVEJA, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.GIN, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.SIDRA, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10));

		francoDrinks.addAll(new DrinkForPub(Drink.VODKA, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.COPO_CERVEJA, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.CANECA_CERVEJA, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.GIN, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.SIDRA, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10));
		drinksInPubs.addAll(pedroDrinks);
		drinksInPubs.addAll(francoDrinks);
	}
}
