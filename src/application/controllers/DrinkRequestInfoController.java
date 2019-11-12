package application.controllers;

import application.models.DrinkForPub;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DrinkRequestInfoController {

	
	@FXML
	private TextField nameField, priceField, barField;
	
	private TextArea obsField;
	
	private DrinkForPub drink;
	
	private String observation;
	
	public DrinkRequestInfoController(DrinkForPub drink, String observation) {
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
