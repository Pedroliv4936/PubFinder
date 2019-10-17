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
	
	JDBC jdbc;
	
	@FXML
	public void onLoginButtonClicked() {
		connectDB();
		if(jdbc.isConnected()) {
			Main.getCurrentStage().close();
			try {
				Main.createNewWindow("login/MainScreen.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void connectDB() {
		jdbc = new JDBC();
		
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		try {
			jdbc.dbConnect(username, password);
			
			System.out.println("Connected as " + username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jdbc.disconnectDb();
	}
}
