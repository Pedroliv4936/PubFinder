package application.views;

import com.jfoenix.controls.JFXButton;

import application.ScreenManager;
import javafx.fxml.FXML;

public class GlobalController {

	@FXML
	JFXButton bebidasButton, logoButton, userButton;

	@FXML
	private void initialize() {
		ScreenManager.setScreen("Header.fxml", "MainScreenContent.fxml");

	}
}
