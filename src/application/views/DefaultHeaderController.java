package application.views;

import application.ScreenManager;
import javafx.fxml.FXML;

public class DefaultHeaderController {
	
	@FXML
	private void openDrinksPage() {
		ScreenManager.setScreen("views/BebidasScreen.fxml");
	}

	@FXML
	private void openMainPage() {
		ScreenManager.setScreen("views/MainScreenContent.fxml");
	}

	@FXML
	private void openUserPage() {
		ScreenManager.setScreen("views/UserHeader.fxml", "views/UserScreen.fxml");
	}
}
