package application.views;

import application.ScreenManager;
import javafx.fxml.FXML;

public class UserHeaderController {

	@FXML
	private void openDrinksScreen(){
		
	}
	
	@FXML
	private void openMainScreen() {
		ScreenManager.setScreen(ScreenManager.MAIN_SCREEN);
	}
	
}
