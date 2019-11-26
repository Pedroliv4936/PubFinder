package application.controllers;

import application.models.DrinkForSale;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DrinkRequestInfoController {

	
	@FXML
	private TextField nameField, priceField, barField;
	
	private TextArea obsField;
	
	private DrinkForSale drink;
	
	private String observation;
	
	public DrinkRequestInfoController(DrinkForSale drink, String observation) {
		this.drink = drink;
		this.observation = observation;
	}
	
	@FXML
	public void initialize() {
		setText();
	}
	
	private void setText() {
		nameField.setText(drink.getDrinkName());
		priceField.setText("€ " + String.valueOf(drink.getPrice()));
		barField.setText(drink.getPub().toString());
	}
	
}
