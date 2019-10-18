package application.views.controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController {

	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	
	@FXML
	public void onLoginButtonClicked() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		Main.connectDB(username, password);
		if(Main.getJDBC().isConnected()) {
			Main.getCurrentStage().close();
			Main.createNewWindow("views/MainScreen.fxml", "beerIcon.png");
		}
	}
}
