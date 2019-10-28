package application.controllers;

import application.ScreenManager;
import javafx.fxml.FXML;

public class DefaultHeaderController {
	
	@FXML
	private void openDrinksPage() {
		ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, "views/BebidasScreen.fxml", new DefaultHeaderController(), new MenuBebidasController());
	}

	@FXML
	private void openMainPage() {
		ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, "views/MainScreenContent.fxml", new DefaultHeaderController(), new MainScreenContentController());
	}

	@FXML
	private void openUserPage() {
		ScreenManager.setScreen("views/UserHeader.fxml", "views/UserScreen.fxml", new UserHeaderController(), new UserScreenController());
	}
}
