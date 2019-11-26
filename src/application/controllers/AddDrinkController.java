package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.ScreenManager;
import application.models.Drink;
import application.models.DrinkForSale;
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
			System.out.println(drink.toString() + " foi adicionado a lista de beebidas disponiveis");
		}
		drinkType.setItems(drinkNames);
	}

	@FXML
	private void sendInfo() {
		if (allFieldsFilled()) {
			DrinkForSale newDrink = new DrinkForSale(drinkType.getValue(), pubOptions.getValue(), 4,
					Double.parseDouble(priceField.getText()));
			System.out.println();
			System.out.println();
			System.out.println("BEBIDA ENVIADA PARA APROVAÇÃO COM ATRIBUTOS:");
			newDrink.showDrinkInfo();
			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
		}
	}

	private boolean allFieldsFilled() {
		boolean allFieldsFilled = true;
		String priceRegex = "^[0-9]+(,\\d{3})*([,.]\\d+)?$";
		if (pubOptions.getValue() == null) {
			pubOptions.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			System.out.println("Pub Not Selected");
			allFieldsFilled = false;
		} else {
			pubOptions.getStylesheets().clear();
		}
		if (drinkType.getValue() == null) {
			drinkType.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			System.out.println("Drink Not Selected");
			allFieldsFilled = false;
		} else {
			drinkType.getStylesheets().clear();
		}
		if (!priceField.getText().matches(priceRegex)) {
			priceField.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			priceField.clear();
			priceField.setPromptText("Preco invalido");
			System.out.println("Invalid price");
			allFieldsFilled = false;
		} else {
			priceField.getStylesheets().clear();
		}
		System.out.println("All fields filled");
		return allFieldsFilled;
	}
}