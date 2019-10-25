package application.views;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import application.ScreenManager;
import application.model.DAO.loginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController {

	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	
	@FXML CheckBox rememberChoice;
	@FXML CheckBox autoLogin;
	
	@FXML
	public void initialize () {
		Properties p = new Properties();
		try {
			p.load(new FileReader("choicheBoxes.properties"));
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
			ScreenManager.setScreen(ScreenManager.MAIN_SCREEN);
		}else
			if(loginDAO.connect(username, password) == 1) {
				ScreenManager.setScreen(ScreenManager.MAIN_SCREEN);
			}else
			if(loginDAO.connect(username, password) == 2) {
				
			}else
				if(loginDAO.connect(username, password) == 3) {
					
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
			p.store(new FileWriter("choicheBoxes.properties"), "");
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
			p.store(new FileWriter("choicheBoxes.properties"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
