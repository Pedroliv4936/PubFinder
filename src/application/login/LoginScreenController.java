package application.login;

import java.io.IOException;
import java.sql.SQLException;

import application.JDBC;
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
	
	Stage mainStage;
	
	JDBC jdbc;
	
	@FXML
	public void onLoginButtonClicked() throws IOException {
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
