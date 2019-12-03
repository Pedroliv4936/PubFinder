package application.controllers;

import application.ScreenManager;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserHeaderController {

	@FXML
	Label user_name;
	
	@FXML
	private void initialize() {
		user_name.setText(LoginDAO.getLogedinUser().getName());
	}
	
	@FXML
	private void openDrinksScreen(){
		ScreenManager.setScreen(ScreenContainer.MENU_BEBIDAS);
	}
	
	@FXML
	private void openMainScreen() {
		ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}
	
	@FXML
	private void logOut() {
		ScreenManager.setScreen(ScreenContainer.LOGIN);
		LoginDAO.setLogedinUser(null);
	}
}
