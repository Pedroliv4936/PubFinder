package application.controllers;

import application.ScreenManager;
import application.views.ScreenContainer;
import javafx.fxml.FXML;

public class UserHeaderController {

	@FXML
	private void openDrinksScreen(){
		ScreenManager.setScreen(ScreenContainer.MENU_BEBIDAS);
	}
	
	@FXML
	private void openMainScreen() {
		ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}
	
}
