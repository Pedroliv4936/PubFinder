package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.ScreenManager;
import application.models.Drink;
import application.models.DrinkForPub;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
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
		String priceRegex = "[0-9]+";
		if (!priceField.getText().matches(priceRegex)) {
			priceField.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			priceField.setText("Preco invalido");
			System.out.println("Invalid price");
		} else {
			DrinkForPub newDrink = new DrinkForPub(drinkType.getValue(), pubOptions.getValue(), 4,
					Double.parseDouble(priceField.getText()));
			System.out.println();
			System.out.println();
			System.out.println("BEBIDA ENVIADA PARA APROVAÇÃO COM ATRIBUTOS:");
			newDrink.showDrinkInfo();
			DrinkDAO.addPendingDrink(newDrink);
			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
		}
	}
}
