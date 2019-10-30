package application.controllers;

import java.sql.Date;

import application.ScreenManager;
import application.models.Drink;
import application.models.FavoriteDrinkList;
import application.models.User;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterScreenController {
	@FXML
	TextField username, userMail;
	@FXML
	PasswordField password1, password2;
	@FXML
	DatePicker birthday;
	@FXML
	CheckBox sidra, cerveja, vodka, gin, canecaCerveja;
	ObservableList<CheckBox> checkBoxes = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		sidra.setUserData(Drink.SIDRA);
		cerveja.setUserData(Drink.COPO_CERVEJA);
		vodka.setUserData(Drink.VODKA);
		gin.setUserData(Drink.GIN);
		canecaCerveja.setUserData(Drink.CANECA_CERVEJA);
		checkBoxes.addAll(sidra,cerveja,vodka,gin,canecaCerveja);
	}
	
	@FXML
	private void submit() {
		if(password1.getText().equals(password2.getText())) {
		User user = new User(LoginDAO.getUserList().size(), username.getText(), username.getText(), password1.getText(), userMail.getText(), Date.valueOf(birthday.getValue()), false);
		ObservableList<Drink> selectedDrinks= FXCollections.observableArrayList();
		for(CheckBox checkBox:checkBoxes) {
			if (checkBox.isSelected()) {
				selectedDrinks.addAll((Drink)checkBox.getUserData());
				System.out.println(checkBox.getUserData().toString() + " Adicionado");
			}	
		}
		System.out.println(user.toString() + " Adicionado");
		FavoriteDrinkList favoriteDrinks = new FavoriteDrinkList(user.toString(), null);
		user.setFavoriteDrinkList(favoriteDrinks);
		LoginDAO.addUser(user, favoriteDrinks);
		ScreenManager.setScreen(ScreenContainer.LOGIN);
		} else {
			System.out.println("Passwords do not match");
		}
		}
}
