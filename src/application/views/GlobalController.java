package application.views;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class GlobalController {

	@FXML
	JFXButton bebidasButton, logoButton, userButton;

	@FXML
	private void initialize() {
		setScreen("Header.fxml", "MainScreenContent.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader();
		HeaderController headerController = (HeaderController)fxmlLoader.getController();
		MainScreenController mainScreenController = (MainScreenController)fxmlLoader.getController();
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
