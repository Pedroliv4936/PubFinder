package application.controllers;

import application.models.DrinkForSale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controlador do FXML AvailableDrinks que apresenta a informação das bebidas vendidas em cada pub.
 * 
 * @see application.controllers.PubInfoController
 * 
 * 
 * @author pedrooliveira and Franco Zalamena
 */
public class DrinkInfoController {

	@FXML
	private Label drinkName, drinkPrice, drinkRating;
	
	@FXML
	private ImageView drinkImage;
	
	private String name, price, rating;
	
	private Image image;
	
	public DrinkInfoController(DrinkForSale drink) {
		this.name = drink.getDrinkName();
		this.image = drink.getDrinkType().getIcon();
		this.price = String.format("%.2f" ,drink.getPrice());
		this.rating = String.format("%.2f", drink.getRating());
	}
	/**
	 * Apresenta a informaçao da bebida
	 */
	@FXML
	private void initialize() {
		drinkName.setText(name);
		drinkImage.setImage(image);
		drinkPrice.setText(price);
		drinkRating.setText(rating);
	}
	/**
	 * Coloca a informacao de cada bebida nas respetivas variaveis desta classe.
	 * 
	 * @param drink Bebida à venda do pub.
	 * 
	 */
	public void setShownDrink(DrinkForSale drink) {
		this.name = drink.getDrinkName();
		this.image = drink.getDrinkType().getIcon();
		this.price = String.format("%.2f" ,drink.getPrice());
		this.rating = String.format("%.2f", drink.getRating());
	}
}
