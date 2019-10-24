package application.views;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import application.ScreenManager;

public class HeaderController {
	
	@FXML
	private void openDrinksPage() {
		ScreenManager.setScreen("views/Header.fxml", "views/BebidasScreen.fxml");
	}

	@FXML
	private void openMainPage() {
		ScreenManager.setScreen("views/Header.fxml", "views/MainScreenContent.fxml");
	}

	@FXML
	private void openUserPage() {
		ScreenManager.setScreen("views/UserHeader.fxml", "views/UserScreen.fxml");
	}
}
