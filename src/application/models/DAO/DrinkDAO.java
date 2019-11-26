package application.models.DAO;

import application.models.Drink;
import application.models.DrinkForSale;
import application.models.Pub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DrinkDAO {

	private static ObservableList<DrinkForSale> drinksInPubs = FXCollections.observableArrayList();
	
	private static ObservableList<Drink> drinkList = FXCollections.observableArrayList();

	private static ObservableList<DrinkForSale> pendingDrinkList = FXCollections.observableArrayList();
	

	public static ObservableList<DrinkForSale> getPendingDrinkList() {
		return pendingDrinkList;
	}

	public static ObservableList<DrinkForSale> getDrinksInPubs() {
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
	
	public static void addDrinkFromPub(DrinkForSale drink) {
		drinksInPubs.add(drink);
	}
	
	public static void removeDrinkFromPub(DrinkForSale drink) {
		drinksInPubs.remove(drink);
	}
	
	public static ObservableList<Drink> getDrinkList() {
		return drinkList;
	}

	public static void setDrinkList(ObservableList<Drink> drinkList) {
		DrinkDAO.drinkList = drinkList;
	}

	public static void addPendingDrink(DrinkForSale drink) {
		pendingDrinkList.add(drink);
	}
	
	public static void aproveDrinks(DrinkForSale drink) {
		drinksInPubs.add(drink);
		pendingDrinkList.remove(drink);
		drink.getPub().getDrinks().add(drink);
			System.out.println();
			System.out.println("TAMANHO DA LISTA DO " + drink.getPub().toString());
			System.out.println(drink.getPub().getDrinks().size());
	}
	
	public static void refuseDrinks(ObservableList<DrinkForSale> drinks) {
		pendingDrinkList.removeAll(drinks);
	}
	
	static {
		drinkList.addAll(Drink.CANECA_CERVEJA, Drink.COPO_CERVEJA, Drink.VODKA, Drink.SIDRA, Drink.GIN);
		
		ObservableList<DrinkForSale> drinks = FXCollections.observableArrayList();

		drinks.addAll(new DrinkForSale(Drink.VODKA, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.COPO_CERVEJA, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.CANECA_CERVEJA, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.GIN, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.SIDRA, PubDAO.getPubList().get(0), Math.random() * 5, Math.random() * 10));
		PubDAO.getPubByName("Bar do Pedro").setDrinks(drinks);
		
		drinks =FXCollections.observableArrayList();
		drinks.addAll(new DrinkForSale(Drink.VODKA, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.COPO_CERVEJA, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.CANECA_CERVEJA, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.GIN, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.SIDRA, PubDAO.getPubList().get(1), Math.random() * 5, Math.random() * 10));
		PubDAO.getPubByName("Bar do Franco").setDrinks(drinks);
		
		drinks =FXCollections.observableArrayList();
		drinks.addAll(new DrinkForSale(Drink.VODKA, PubDAO.getPubList().get(2), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.COPO_CERVEJA, PubDAO.getPubList().get(2), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.CANECA_CERVEJA, PubDAO.getPubList().get(2), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.GIN, PubDAO.getPubList().get(2), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.SIDRA, PubDAO.getPubList().get(2), Math.random() * 5, Math.random() * 10));
		PubDAO.getPubList().get(2).setDrinks(drinks);
		
		drinks =FXCollections.observableArrayList();
		drinks.addAll(new DrinkForSale(Drink.VODKA, PubDAO.getPubList().get(3), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.COPO_CERVEJA, PubDAO.getPubList().get(3), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.CANECA_CERVEJA, PubDAO.getPubList().get(3), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.GIN, PubDAO.getPubList().get(3), Math.random() * 5, Math.random() * 10),
				new DrinkForSale(Drink.SIDRA, PubDAO.getPubList().get(3), Math.random() * 5, Math.random() * 10));
		PubDAO.getPubList().get(3).setDrinks(drinks);
		
		for(Pub pub : PubDAO.getPubList()) {
			drinksInPubs.addAll(pub.getDrinks());
		}
		
	}
}
