package application.controllers;

import application.models.Drink;
import application.models.DAO.DrinkDAO;
import application.models.DAO.LoginDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
		ObservableList<Drink> userFavoriteDrinks = DrinkDAO.getFavDrinks(LoginDAO.getLogedinUser());
		int row = 0, column=0;
		System.out.println("Usuario Bebidas: " + LoginDAO.getLogedinUser().getName() + " com " + userFavoriteDrinks.size());
		for(Drink drink : DrinkDAO.getDrinkTypes()) {
			CheckBox newCheckBox = new CheckBox(drink.toString());
			newCheckBox.setStyle("-fx-text-fill: WHITE");
			
			if(userFavoriteDrinks.contains(drink)) {
				newCheckBox.setSelected(true);
				System.out.println(drink.toString() + " é uma bebida favorita de: " + LoginDAO.getLogedinUser().getName());
			}
			newCheckBox.setOnAction(e -> {
				
				if(newCheckBox.isSelected() && !userFavoriteDrinks.contains(drink)) {
				DrinkDAO.addFavDrink(LoginDAO.getLogedinUser(), drink);
				}
				
			});
			
			System.out.println(drink.toString());
			bebidasFavoritasUser.add(newCheckBox, column, row);
			if(userFavoriteDrinks.contains(drink)) {
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
