package application.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import application.ScreenManager;
import application.models.User;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
/**
 * Controlador do FXML LoginScreen que pede ao utilizador que preencha as caixas apresentadas com o seu username e com a respetiva password
 * Dá a possibilidade ao utilizador de deixar que a sua password e username sejam preenchidos automaticamente.
 * 
 * @author pedrooliveira
 *
 */
public class LoginScreenController {

	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;
	@FXML
	Label loginFeedback;
	@FXML
	CheckBox rememberChoice;
	@FXML
	CheckBox autoLogin;
	/**
	 * Caso o utilizador selecionar a checkbox "lembrar credenciais" a informacao que o utilizador preencheu vai ser
	 *  guardada no loginInfo.properties, para essa informacao se preencher automaticamente para a proxima vez que o 
	 *  utilizador deseje fazer login.
	 *  
	 *  @see #checkBoxes(Properties, String)
	 */
	@FXML
	public void initialize() {
		Properties p = new Properties();
		try {
			p.load(new FileReader("src/application/models/DAO/loginInfo.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkBoxes(p, "0");
		if (rememberChoice.isSelected()) {
			usernameField.setText(p.getProperty("username"));
			passwordField.setText(p.getProperty("password"));
		}
	}
	/**
	 * Serve para apresentar as checkboxes que estão selecionadas por default.
	 * @param p Propriedade que contem a informacao necessario para o autologin
	 * @param check	Opcao de login que o utilizador deseja (lembrar credenciais ou autologin)
	 */
	private void checkBoxes(Properties p, String check) {
		if (check.equals(p.getProperty("option1"))) {
			rememberChoice.setSelected(false);
		} else {
			rememberChoice.setSelected(true);
		}
		if (check.equals(p.getProperty("option2").toString())) {
			autoLogin.setSelected(false);
		} else {
			autoLogin.setSelected(true);
		}
	}
	/**
	 * Método que faz o login, em que conecta com a base de dados e apresenta a informcao que esta errada (caso exista),
	 * busca o tipo de utilizador que deseja fazer login e muda a tela para o mainScreen.
	 * 
	 * @see application.models.DAO.LoginDAO#connect(String, String)
	 * @see application.ScreenManager#setScreen(ScreenContainer)
	 */
	@FXML
	public void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();

		User user = LoginDAO.connect(username, password);
		if(user == null) {
			loginFeedback.setTextFill(Color.RED);
			loginFeedback.setText("Wrong Password");
		}else	
		switch (user.getPrivilege()) {
		case USER:
		case ADMIN:
			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
			break;
		default:
			loginFeedback.setTextFill(Color.RED);
			loginFeedback.setText("Wrong Password");
			break;
		}
		System.out.println("Usuario logado como: " + LoginDAO.getLogedinUser().getUsername());
	}
	/**
	 * Muda a tela para a Register Screen
	 * @see application.ScreenManager#setScreen(ScreenContainer)
	 */
	@FXML
	public void register() {
		ScreenManager.setScreen(ScreenContainer.REGISTER);
	}
}
