package application.views;

import application.ScreenManager;
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
}
