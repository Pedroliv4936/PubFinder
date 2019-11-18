package application.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import application.ScreenManager;
import application.models.LoginType;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginScreenController {

	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	@FXML Label loginFeedback;
	@FXML CheckBox rememberChoice;
	@FXML CheckBox autoLogin;
	
	@FXML
	public void initialize () {
		Properties p = new Properties();
		try {
			p.load(new FileReader("src/application/models/DAO/loginInfo.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();	
		}
		checkBoxes(p, "0");
		if(rememberChoice.isSelected()) {
			usernameField.setText(p.getProperty("username"));
			passwordField.setText(p.getProperty("password"));
		}
	}

	private void checkBoxes(Properties p, String check) {
		if (check.equals(p.getProperty("option1"))) {
			rememberChoice.setSelected(false);
		}else {
			rememberChoice.setSelected(true);
		}
		if (check.equals(p.getProperty("option2").toString())) {
			autoLogin.setSelected(false);
		}else {
			autoLogin.setSelected(true);
		}
	}
	
	@FXML
	public void login() {
		getSelections();
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		LoginType lt = LoginDAO.connect(username, password);
		
		if(lt == LoginType.USER_LOGIN) {
			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
		}else
			if(lt == LoginType.ADMIN_LOGIN) {
				ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
			}else
				loginFeedback.setTextFill(Color.RED);
			if(lt == LoginType.WRONG_USERNAME) {
				loginFeedback.setText("Wrong username");
			}else
				if(lt == LoginType.WRONG_PASSWORD) {
					loginFeedback.setText("Wrong Password");
				}
			System.out.println("Usuario logado como: " + LoginDAO.getLogedinUser().getUsername());
	}
	
	@FXML
	public void register() {
		ScreenManager.setScreen(ScreenContainer.REGISTER);
	}

	private void getSelections() {
		Properties p = new Properties();
		if(rememberChoice.isSelected()) {
			p.setProperty("option1", "1");
			p.setProperty("username", usernameField.getText());
			p.setProperty("password", passwordField.getText());
		}else {
			p.setProperty("option1", "0");
		}
		try {
			p.store(new FileWriter("src/application/models/DAO/loginInfo.properties"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		if(autoLogin.isSelected()) {
			p.setProperty("option2", "1");
			p.setProperty("username", usernameField.getText());
			p.setProperty("password", passwordField.getText());
		}else {
			p.setProperty("option2", "0");
		}
		try {
			p.store(new FileWriter("src/application/models/DAO/loginInfo.properties"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
