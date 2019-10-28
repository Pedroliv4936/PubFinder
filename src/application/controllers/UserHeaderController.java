package application.controllers;

import application.ScreenManager;
import javafx.fxml.FXML;

public class UserHeaderController {

	@FXML
	private void openDrinksScreen(){
		ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, ScreenManager.DRINK_SCREEN_CONTENT,new DefaultHeaderController(), new MenuBebidasController());
	}
	
	@FXML
	private void openMainScreen() {
		ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, ScreenManager.MAIN_SCREEN_CONTENT, new DefaultHeaderController(), new MainScreenContentController());
	}
	
}
