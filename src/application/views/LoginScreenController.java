package application.views;

import java.sql.SQLException;

import application.JDBC;
import application.Main;
import application.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController {

	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	
	@FXML
	public void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		try {
			JDBC.dbConnect(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(JDBC.isConnected()) {
			ScreenManager.setContent(ScreenManager.MAIN_SCREEN);
		}
	}
}
