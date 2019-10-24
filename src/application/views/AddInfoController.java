package application.views;



import application.ScreenManager;
import javafx.fxml.FXML;


public class AddInfoController {
	
	@FXML
	private void initialize() {
		ScreenManager.setScreen("Header.fxml", "AddInformationScreen.fxml");

	}
}
