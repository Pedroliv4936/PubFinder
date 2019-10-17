package application.login;

import java.io.IOException;
import java.sql.SQLException;

import application.JDBC;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
			Main.createNewWindow("login/MainScreen.fxml", "beerIcon.png");
		}
	}
}
