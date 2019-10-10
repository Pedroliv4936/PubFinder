package application.login;

import java.sql.SQLException;

import application.JDBC;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController {

	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	
	JDBC jdbc;
	
	@FXML
	public void onLoginButtonClicked() {
		jdbc = new JDBC();
		
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		try {
			jdbc.dbConnect(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
