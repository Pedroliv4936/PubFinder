package application.controllers;

import com.jfoenix.controls.JFXSlider;

import application.models.DrinkForSale;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Controlador do FXML AvailableDrinks que apresenta a informaÃ§Ã£o das bebidas vendidas em cada pub.
 * 
 * @see application.controllers.PubInfoController
 * 
 * 
 * @author Franco Zalamena e Pedro Oliveira
 */
public class DrinkInfoController {

	@FXML
	private Label drinkName, drinkPrice, drinkRating;
	
	@FXML
	private ImageView drinkImage;
	
	private String name, price, rating;
	
	private Image image;
	
	private DrinkForSale drink;
	
	@FXML
	private JFXSlider rateSlider;
	
	@FXML
	private Button rateButton;
	
	@FXML
	private VBox vbox;
	
	/**
	 * Coloca a informacao de cada bebida nas respetivas variaveis desta classe.
	 * 
	 * @param drink Bebida Ã  venda do pub.
	 * 
	 */
	public DrinkInfoController(DrinkForSale drink) {
		this.drink = drink;
		this.name = drink.getDrinkName();
		drink.refreshRating();
		this.image = drink.getDrinkType().getIcon();
		this.price = String.format("%.2f" ,drink.getPrice());
		this.rating = String.format("%.2f", drink.getRating());
	}
	/**
	 * Insere a informacao da bebida nas respetivas labels
	 */
	@FXML
	private void initialize() {
		drinkName.setText(name);
		drinkImage.setImage(image);
		drinkPrice.setText("€ " + price);
		drinkRating.setText("☆ " + rating);
	}
	
	@FXML
	private void rate() {
		this.drink.rate(rateSlider.getValue());
		vbox.getChildren().removeAll(rateButton, rateSlider);
		Label thxMsg = new Label("Obrigado!");
		thxMsg.setTextFill(Color.BLACK);
		vbox.getChildren().add(thxMsg);
	}
}
