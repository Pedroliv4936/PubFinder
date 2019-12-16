package application.controllers;

import java.sql.Date;

import application.ScreenContainer;
import application.ScreenManager;
import application.models.Drink;
import application.models.User;
import application.models.UserPrivilege;
import application.models.DAO.LoginDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * Controlador do FXML RegisterScreen que serve para se poder registar um novo utilizador a base de Dados com toda a sua informacao (nome, username, data de nascimento, bebidas favoritas, etc).
 * 
 * @author Franco Zalamena e Pedro Oliveira
 * @see application.models.DAO.LoginDAO
 */
public class RegisterScreenController {
	@FXML
	TextField username, userMail, nome, cellphone;
	@FXML
	PasswordField password1, password2;
	@FXML
	DatePicker birthday;
	@FXML
	CheckBox sidra, cerveja, vodka, gin, canecaCerveja;
	
	ObservableList<CheckBox> checkBoxes = FXCollections.observableArrayList();
	/**
	 * Adiciona os tipos de bebidas as respetivas checkboxes como userdata. Adiciona-se  os tipos de bebida à observableArrayList checkboxes.
	 * @see application.models.Drink
	 */
	@FXML
	private void initialize() {
		sidra.setUserData(Drink.SIDRA);
		cerveja.setUserData(Drink.COPO_CERVEJA);
		vodka.setUserData(Drink.VODKA);
		gin.setUserData(Drink.GIN);
		canecaCerveja.setUserData(Drink.CANECA_CERVEJA);
		checkBoxes.addAll(sidra,cerveja,vodka,gin,canecaCerveja);
	}
	/**
	 * Submete-se o utilizador que se deseja adicionar à base de dados, verificando antes se as passwords escritas são iguais. 
	 */
	@FXML
	private void submit() {
		if(password1.getText().equals(password2.getText())) {
			ObservableList<Drink> selectedDrinks= FXCollections.observableArrayList();
		User user = new User(LoginDAO.getUserList().size(), nome.getText(), username.getText(), password1.getText(), userMail.getText(), Date.valueOf(birthday.getValue()),Integer.valueOf(cellphone.getText()), UserPrivilege.USER);
		for(CheckBox checkBox:checkBoxes) {
			if (checkBox.isSelected()) {
				selectedDrinks.addAll((Drink)checkBox.getUserData());
				System.out.println(checkBox.getUserData().toString() + " Adicionado");
			}	
		}
		System.out.println(user.toString() + " Adicionado");
		LoginDAO.addUser(user);
		ScreenManager.setScreen(ScreenContainer.LOGIN);
		} else {
			System.out.println("Passwords do not match ");
		}
		}
}
