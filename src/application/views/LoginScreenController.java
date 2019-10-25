package application.views;

import application.model.DAO.loginDAO;
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
		
		loginDAO.connect(username, password);
	}
}
