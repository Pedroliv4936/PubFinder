package application.views;

import com.jfoenix.controls.JFXTextField;

import application.ScreenManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class MainScreenContentController {

	@FXML
	JFXTextField searchField;
	@FXML
	VBox vbox;
	
	@FXML
	private void addInformation() {
	ScreenManager.setScreen("DefaultHeader.fxml", "AddInformationScreen");
	}
	
}
