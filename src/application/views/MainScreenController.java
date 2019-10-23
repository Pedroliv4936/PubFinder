package application.views;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.fxml.FXML;

public class MainScreenController {

	@FXML
	JFXTextField searchField;
	@FXML
	JFXButton bebidasButton,logoButton,userButton;
	
	@FXML
	private void initialize() {
	}

	@FXML
	private void openDrinksPage() {
		Main.changeScene("views/BebidasScreen.fxml");
	}
	
	@FXML
	private void openMainPage() {
		Main.changeScene("views/MainScreen.fxml");
	}
	
	@FXML
	private void openUserPage() {
		Main.changeScene("views/UserScreen.fxml");
	}
	
	@FXML
	private void openInformationPage() {
		Main.changeScene("views/AddInformationScreen.fxml");
	}
}
