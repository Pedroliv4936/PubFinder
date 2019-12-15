package application.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import application.ScreenContainer;
import application.ScreenManager;
import application.models.User;
import application.models.DAO.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
/**
 * Controlador do FXML LoginScreen que pede ao utilizador que preencha as caixas apresentadas com o seu username e com a respetiva password
 * Dá a possibilidade ao utilizador de deixar que a sua password e username sejam preenchidos automaticamente.
 * 
 * @author pedrooliveira
 *
 */
public class LoginScreenController {

	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;
	@FXML
	Label loginFeedback;
	@FXML
	CheckBox rememberChoice;
	@FXML
	CheckBox autoLogin;


	@FXML
	public void initialize() {
		Properties p = new Properties();
		try {
			p.load(new FileReader("src/application/models/DAO/loginInfo.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkBoxes(p, "0");
		if (rememberChoice.isSelected()) {
			usernameField.setText(p.getProperty("username"));
			passwordField.setText(p.getProperty("password"));
		}
	}

	private void checkBoxes(Properties p, String check) {
		if (check.equals(p.getProperty("option1"))) {
			rememberChoice.setSelected(false);
		} else {
			rememberChoice.setSelected(true);
			usernameField.setText(p.getProperty("username"));
			passwordField.setText(p.getProperty("password"));
		}
		if (check.equals(p.getProperty("option2").toString())) {
			autoLogin.setSelected(false);
		} else {
			autoLogin.setSelected(true);
		}
	}

	@FXML
	public void login() {
		saveCredentials();
		String username = usernameField.getText();
		String password = passwordField.getText();

		User user = LoginDAO.connect(username, password);
		if(user == null) {
			loginFeedback.setTextFill(Color.RED);
			loginFeedback.setText("Wrong Password");
		}else	
		switch (user.getPrivilege()) {
		case USER:
		case ADMIN:
			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
			break;
		default:
			loginFeedback.setTextFill(Color.RED);
			loginFeedback.setText("Wrong Password");
			break;
		}
		System.out.println("Usuario logado como: " + LoginDAO.getLogedinUser().getUsername());
	}
	
	private void saveCredentials() {
		Properties p = new Properties();
		try {
			p.load(new FileReader("src/application/models/DAO/loginInfo.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(rememberChoice.isSelected()) {
			p.setProperty("username", usernameField.getText());
			System.out.println(usernameField.getText());
			p.setProperty("password", passwordField.getText());
			p.setProperty("option1", "1");
			p.setProperty("option2", "0");
			try {
				p.store(new FileOutputStream("src/application/models/DAO/loginInfo.properties"), " ");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void register() {
		ScreenManager.setScreen(ScreenContainer.REGISTER);
	}
}
