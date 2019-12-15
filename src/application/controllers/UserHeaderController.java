package application.controllers;

import application.ScreenManager;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * Controlador do FXML UserHeader que serve para apresentar o nome do User logado e para se poder mudar de ecra(menu bebidas,logout ou para o mainScreen).
 * @author pedrooliveira
 *
 */
public class UserHeaderController {

	@FXML
	Label user_name;
	/**
	 * Define o nome do user logado como o texto da label.
	 */
	@FXML
	private void initialize() {
		user_name.setText(LoginDAO.getLogedinUser().getName());
	}
	/**
	 * Mudar o ecrã para o menu Bebidas.
	 */
	@FXML
	private void openDrinksScreen(){
		ScreenManager.setScreen(ScreenContainer.MENU_BEBIDAS);
	}
	/**
	 * mudar o ecra para o mainScreen.
	 */
	@FXML
	private void openMainScreen() {
		ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}
	/**
	 * Mudar para o ecrã Login de forma a se poder fazer login de outro utilizador(fazendo logout do utilizador logado).
	 */
	@FXML
	private void logOut() {
		ScreenManager.setScreen(ScreenContainer.LOGIN);
		LoginDAO.setLogedinUser(null);
	}
}
