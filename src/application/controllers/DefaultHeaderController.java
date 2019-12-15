package application.controllers;

import application.ScreenContainer;
import application.ScreenManager;
import javafx.fxml.FXML;

public class DefaultHeaderController {
	/**
	 * Muda a tela para o Menu Bebidas.
	 */
	@FXML
	private void openDrinksPage() {
		ScreenManager.setScreen(ScreenContainer.MENU_BEBIDAS);
	}
	/**
	 * Muda a tela para o MainScreen
	 */
	@FXML
	private void openMainPage() {
		ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}
	/**
	 * Muda a tela para o User Screen.
	 */
	@FXML
	private void openUserPage() {
		ScreenManager.setScreen(ScreenContainer.USER_SCREEN);
	}
}
