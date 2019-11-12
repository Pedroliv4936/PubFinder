package application.controllers;

import application.models.DrinkForPub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DrinkInfoController {

	@FXML
	private Label drinkName, drinkPrice, drinkRating;
	
	@FXML
	private ImageView drinkImage;
	
	private String name, price, rating;
	
	private Image image;

	public DrinkInfoController(DrinkForPub drink) {
		this.name = drink.getDrinkName();
		this.image = drink.getDrinkType().getIcon();
		this.price = String.format("%.2f" ,drink.getPrice());
		this.rating = String.format("%.2f", drink.getRating());
	}
	
	@FXML
	private void initialize() {
		drinkName.setText(name);
		drinkImage.setImage(image);
		drinkPrice.setText(price);
		drinkRating.setText(rating);
	}
	
	public void setShownDrink(DrinkForPub drink) {
		this.name = drink.getDrinkName();
		this.image = drink.getDrinkType().getIcon();
		this.price = String.format("%.2f" ,drink.getPrice());
		this.rating = String.format("%.2f", drink.getRating());
	}
}
