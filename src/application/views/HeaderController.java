package application.views;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class HeaderController {

	@FXML
	JFXButton bebidasButton,logoButton,userButton;
	@FXML
	Pane contentPane;
	@FXML
	private void initialize() {
		try {
			Pane newLoadedFxml = FXMLLoader.load(getClass().getResource("MainScreenContent.fxml"));
			contentPane.getChildren().add(newLoadedFxml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void openDrinksPage() {
		changeContent("BebidasScreen.fxml");
	}
	
	@FXML
	private void openMainPage() {
		changeContent("MainScreenContent.fxml");
	}
	
	@FXML
	private void openUserPage() {
		changeContent("UserScreen.fxml");
	}
	
	public void changeContent(String fxmlLocation) {
		try {
			Pane newLoadedFxml = FXMLLoader.load(getClass().getResource(fxmlLocation));
			contentPane.getChildren().clear();
			contentPane.getChildren().add(newLoadedFxml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
