package application.views;

import java.io.IOException;

import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class HeaderController {

	@FXML
	JFXButton bebidasButton, logoButton, userButton;
	@FXML
	View contentPane;
	@FXML
	View headerPane;

	@FXML
	private void initialize() {
		try {
			Pane contentFXML = FXMLLoader.load(getClass().getResource("MainScreenContent.fxml"));
			Pane headerFXML = FXMLLoader.load(getClass().getResource("Header.fxml"));
			contentPane.setCenter(contentFXML);
			headerPane.setTop(headerFXML);

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

	private void changeContent(String fxmlLocation) {
		try {
			Pane contentFXML = FXMLLoader.load(getClass().getResource(fxmlLocation));
			contentPane.setCenter(contentFXML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void changeHeader(String fxmlLocation) {
		try {
			Pane headerFXML = FXMLLoader.load(getClass().getResource(fxmlLocation));
			headerPane.setTop(headerFXML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}