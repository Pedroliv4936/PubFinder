package application.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controlador do FXML RegisterScreen que serve para se poder registar um novo
 * utilizador a base de Dados com toda a sua informacao (nome, username, data de
 * nascimento, bebidas favoritas, etc).
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
	 * Adiciona os tipos de bebidas as respetivas checkboxes como userdata.
	 * Adiciona-se os tipos de bebida à observableArrayList checkboxes.
	 * 
	 * @see application.models.Drink
	 */
	@FXML
	private void initialize() {
		sidra.setUserData(Drink.SIDRA);
		cerveja.setUserData(Drink.COPO_CERVEJA);
		vodka.setUserData(Drink.VODKA);
		gin.setUserData(Drink.GIN);
		canecaCerveja.setUserData(Drink.CANECA_CERVEJA);
		checkBoxes.addAll(sidra, cerveja, vodka, gin, canecaCerveja);
	}

	/**
	 * Submete-se o utilizador que se deseja adicionar à base de dados, verificando
	 * antes se as passwords escritas são iguais.
	 */
	@FXML
	private void submit() {
		if (fieldsFilled()) {
			ObservableList<Drink> selectedDrinks = FXCollections.observableArrayList();
			User user = new User(LoginDAO.getUserList().size(), nome.getText(), username.getText(), password1.getText(),
					userMail.getText(), Date.valueOf(birthday.getValue()), Integer.valueOf(cellphone.getText()),
					UserPrivilege.USER);
			for (CheckBox checkBox : checkBoxes) {
				if (checkBox.isSelected()) {
					selectedDrinks.addAll((Drink) checkBox.getUserData());
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
	
	private boolean fieldsFilled() {
		boolean filled = false;
		try{
		if(username.getText().isEmpty()) {
			filled = false;
			username.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
		}else {
			username.getStylesheets().clear();
		}
		if(password1.getText().isEmpty()) {
			filled = false;
			password1.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
		}else {
			password1.getStylesheets().clear();
		}
		if(!password2.getText().equals(password1.getText())) {
			filled = false;
			password2.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
		}else {
			password2.getStylesheets().clear();
		}
		if(nome.getText().isEmpty()) {
			filled = false;
			nome.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
		}else {
			nome.getStylesheets().clear();
		}
		if(cellphone.getText().isEmpty()) {
			filled = false;
			cellphone.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
		}else {
			cellphone.getStylesheets().clear();
		}
		if(!userMail.getText().isEmpty()) {
			if(userMail.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z](?:[a-zA-Z]{0,61}[a-zA-Z])?)*$")) {
				filled = false;
				userMail.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			}else {
				userMail.getStylesheets().clear();
			}
		}else {
			filled = false;
			userMail.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
		}
		if(birthday.getValue().isAfter(LocalDate.now())) {
			filled = false;
			birthday.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
		}else {
			birthday.getStylesheets().clear();
		}
		}catch(Exception e) {
			System.out.println("Campos nao preenchidos");
		}
		
		return filled;
	}
}
