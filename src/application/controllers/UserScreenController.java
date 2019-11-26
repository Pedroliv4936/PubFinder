package application.controllers;

import application.models.Drink;
import application.models.DAO.DrinkDAO;
import application.models.DAO.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class UserScreenController {

	@FXML
	private GridPane bebidasFavoritasUser;
	
	@FXML
	private TextField logedinUsername, logedinEmail;
	
	@FXML
	private PasswordField logedinPassword;
	
	@FXML
	private void initialize() {
		logedinUsername.setText(LoginDAO.getLogedinUser().getUsername());
		logedinPassword.setText(LoginDAO.getLogedinUser().getPassword());
		logedinEmail.setText(LoginDAO.getLogedinUser().getEmail());
		setFavoriteDrinks();
	}

	private void setFavoriteDrinks() {
		int row = 0, column=0;
		System.out.println("Usuario Bebidas: " + LoginDAO.getLogedinUser().toString());
		for(Drink drink : DrinkDAO.getDrinkTypes()) {
			CheckBox newCheckBox = new CheckBox(drink.toString());
			newCheckBox.setStyle("-fx-text-fill: WHITE");
			
			newCheckBox.setOnAction(e -> {
				
				if(newCheckBox.isSelected() && !LoginDAO.getLogedinUser().getFavoriteDrinks().contains(drink)) {
				LoginDAO.getLogedinUser().addFavoriteDrink(drink);
				}
				
			});
			
			System.out.println(drink.toString());
			bebidasFavoritasUser.add(newCheckBox, column, row);
			if(LoginDAO.getLogedinUser().getFavoriteDrinks().contains(drink)) {
				newCheckBox.setSelected(true);
			}
			if(row<2) {
				row++;
			}else {
				column++;
				row=0;
			}
		}
	}
}
