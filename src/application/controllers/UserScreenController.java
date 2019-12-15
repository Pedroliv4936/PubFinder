package application.controllers;

import application.models.Drink;
import application.models.DAO.DrinkDAO;
import application.models.DAO.LoginDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
/**
 * Controlador do Fxml UserScreen que apresenta a informacao do utilizador logado. (Password continua encriptada com MD5)
 * 
 * @author Franco Zalamena & Pedro Oliveira
 *
 */
public class UserScreenController {

	@FXML
	private GridPane bebidasFavoritasUser;
	
	@FXML
	private TextField logedinUsername, logedinEmail;
	
	@FXML
	private PasswordField logedinPassword;
	/**
	 * Define o texto TextFields e da passwordField com a respetiva informacao do utilizador e apresenta as bebidas favoritas do utilizador.
	 */
	@FXML
	private void initialize() {
		logedinUsername.setText(LoginDAO.getLogedinUser().getUsername());
		logedinPassword.setText(LoginDAO.getLogedinUser().getPassword());
		logedinEmail.setText(LoginDAO.getLogedinUser().getEmail());
		setFavoriteDrinks();
	}
	/**
	 * Apresenta as bebidas da aplicacao na GridPane e as que se encontram selecionadas aparecem no menu bebidas.
	 * @see application.models.DAO.DrinkDAO#getFavDrinks(application.models.User)
	 * @see application.models.DAO.LoginDAO#getLogedinUser()
	 */
	private void setFavoriteDrinks() {
		ObservableList<Drink> userFavoriteDrinks = DrinkDAO.getFavDrinks(LoginDAO.getLogedinUser());
		int row = 0, column=0;
		System.out.println("Usuario Bebidas: " + LoginDAO.getLogedinUser().toString());
		for(Drink drink : DrinkDAO.getDrinkTypes()) {
			CheckBox newCheckBox = new CheckBox(drink.toString());
			newCheckBox.setStyle("-fx-text-fill: WHITE");
			
			newCheckBox.setOnAction(e -> {
				
				if(newCheckBox.isSelected() && !userFavoriteDrinks.contains(drink)) {
				DrinkDAO.addFavDrink(LoginDAO.getLogedinUser(), drink);
				}
				
			});
			
			System.out.println(drink.toString());
			bebidasFavoritasUser.add(newCheckBox, column, row);
			for(Drink userDrink : userFavoriteDrinks) {
				if(userDrink.getId() == drink.getId()) {
					System.out.println(userDrink.getId() + " SELECIONADO");
					newCheckBox.setSelected(true);
				}
			}

			if(row<2) {
				row++;
			}else {
				column++;
				row=0;
			}
		}
	}
}
