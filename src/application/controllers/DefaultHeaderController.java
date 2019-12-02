package application.controllers;

import application.ScreenManager;
import application.views.ScreenContainer;
import javafx.fxml.FXML;

public class DefaultHeaderController {
	
	@FXML
	private void openDrinksPage() {
		ScreenManager.setScreen(ScreenContainer.MENU_BEBIDAS);
	}

	@FXML
	private void openMainPage() {
		ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}

	@FXML
	private void openUserPage() {
		ScreenManager.setScreen(ScreenContainer.USER_SCREEN);
	}
}
