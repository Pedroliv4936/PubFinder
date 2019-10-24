package application.views;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class HeaderController {
	
	@FXML
	private void openDrinksPage() {
		setScreen("Header.fxml", "BebidasScreen.fxml");
	}

	@FXML
	private void openMainPage() {
		setScreen("Header.fxml", "MainScreenContent.fxml");
	}

	@FXML
	private void openUserPage() {
		setScreen("UserHeader.fxml", "UserScreen.fxml");
	}
	
	private void setScreen(String headerFXML, String contentFXML) {
		setHeader(headerFXML);
		setContent(contentFXML);
	}

	private void setContent(String fxmlLocation) {
		try {
			Pane contentFXML = FXMLLoader.load(getClass().getResource(fxmlLocation));
			Main.getBorderPane().setCenter(contentFXML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void setHeader(String fxmlLocation) {
		try {
			Pane headerFXML = FXMLLoader.load(getClass().getResource(fxmlLocation));
			
			Main.getBorderPane().setTop(headerFXML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
