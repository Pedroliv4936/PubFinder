package application.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import application.ScreenManager;
import application.models.DAO.loginDAO;
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
		if(loginDAO.connect(username, password) == 0) {
			ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, ScreenManager.MAIN_SCREEN_CONTENT, new DefaultHeaderController(), new MainScreenContentController());
		}else
			if(loginDAO.connect(username, password) == 1) {
				ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, ScreenManager.MAIN_SCREEN_CONTENT,new DefaultHeaderController(), new MainScreenContentController());
			}else
				loginFeedback.setTextFill(Color.RED);
			if(loginDAO.connect(username, password) == 2) {
				loginFeedback.setText("Wrong username");
			}else
				if(loginDAO.connect(username, password) == 3) {
					loginFeedback.setText("Wrong Password");
				}

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