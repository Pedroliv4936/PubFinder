package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.models.Drink;
import application.models.DrinkForPub;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class AddDrinkController {
	
	@FXML
	JFXComboBox<Drink> drinkType;
	
	@FXML
	JFXComboBox<Pub> pubOptions;
	
	@FXML
	JFXButton enviar;
	
	@FXML
	JFXTextField priceField;
	
	@FXML
	private void initialize() {
		showDrinkTypes();
		showBarList();
	}
	
	private void showBarList() {
		ObservableList<Pub> pubNames = FXCollections.observableArrayList();
		for (Pub pub : PubDAO.getPubList()) {
			pubNames.add(pub);
		}
		pubOptions.setItems(pubNames);
	}
	
	private void showDrinkTypes() {
		ObservableList<Drink> drinkNames = FXCollections.observableArrayList();
		for (Drink drink : DrinkDAO.getDrinkList()) {
			drinkNames.add(drink);
			System.out.println(drink.toString() + "Foi Adicionado");
		}
		drinkType.setItems(drinkNames);
	}
	
	@FXML
	private void sendInfo() {
		Drink drinkChosen = null;
		for(Drink drink : DrinkDAO.getDrinkList()) {
			if(drink.toString().equals(drinkType.getValue())) {
				drinkChosen = drink;
			}
		}
		DrinkForPub newDrink = new DrinkForPub(Drink.CANECA_CERVEJA, pubOptions.getValue() , 4, Double.parseDouble(priceField.getText()));
		newDrink.showDrinkInformation();
		DrinkDAO.addPendingDrink(newDrink);
	}
}
