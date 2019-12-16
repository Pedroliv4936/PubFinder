package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.ScreenContainer;
import application.ScreenManager;
import application.models.Drink;
import application.models.DrinkForSale;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * controlador do fxml destinado a preencher um formulario de adicao de bebidas.
 * 
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public class AddDrinkController {

	@FXML
	JFXComboBox<Drink> drinkType;

	@FXML
	JFXComboBox<Pub> pubOptions;

	@FXML
	JFXButton enviar;

	@FXML
	JFXTextField priceField, ratingField;

	@FXML
	private void initialize() {
		showDrinkTypes();
		showBarList();
	}
	
	/**
	 * Define lista de pubs como bares disponiveis, para estes serem apresentados depois na ComboBox.
	 */
	private void showBarList() {
		ObservableList<Pub> pubNames = FXCollections.observableArrayList();
		for (Pub pub : PubDAO.getActivePubs()) {
			pubNames.add(pub);
		}
		pubOptions.setItems(pubNames);
	}
	/**
	 * Define lista de tipos de Bebidas para ser apresentada na ComboBox.
	 */
	private void showDrinkTypes() {
		ObservableList<Drink> drinkNames = FXCollections.observableArrayList();
		for (Drink drink : DrinkDAO.getDrinkTypes()) {
			drinkNames.add(drink);
			System.out.println(drink.toString() + " foi adicionado a lista de bebidas disponiveis");
		}
		drinkType.setItems(drinkNames);
	}
	/**
	 * Adiciona a bebida preenchida à lista de bebidas da aplicação e muda o ecrã para o main screen.
	 */
	@FXML
	private void sendInfo() {
		if (allFieldsFilled()) {
			DrinkForSale newDrink = new DrinkForSale(0, drinkType.getValue(), pubOptions.getValue(), 4,
					Double.parseDouble(priceField.getText()), true);
			System.out.println();
			System.out.println();
			System.out.println("BEBIDA ENVIADA PARA APROVA��O COM ATRIBUTOS:");
			newDrink.showDrinkInfo();
			DrinkDAO.addDrinkForSale(newDrink);
			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
		}
	}
	/**
	 * Certifica que todas as opcoes estao preenchidas, caso alguma opcao nao esteja preenchida apresenta qual a caixa que falta preencher.
	 * 
	 * @return se todos os campos estao preenchidos
	 */
	private boolean allFieldsFilled() {
		boolean allFieldsFilled = true;
		String priceRegex = "^[0-9]+(,\\d{3})*([,.]\\d+)?$";
		String ratingRegex = "^[0-5]+(,\\d{3})*([,.][0-9])?$";
		if (pubOptions.getValue() == null) {
			pubOptions.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			System.out.println("Pub Not Selected");
			allFieldsFilled = false;
		} else {
			pubOptions.getStylesheets().clear();
		}
		if (drinkType.getValue() == null) {
			drinkType.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			System.out.println("Drink Not Selected");
			allFieldsFilled = false;
		} else {
			drinkType.getStylesheets().clear();
		}if (!ratingField.getText().matches(ratingRegex)) {
			priceField.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			priceField.clear();
			priceField.setPromptText("Avaia�ao invalida");
			System.out.println("Invalid rating");
			allFieldsFilled = false;
		} else {
			priceField.getStylesheets().clear();
		}
		if (!priceField.getText().matches(priceRegex)) {
			priceField.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			priceField.clear();
			priceField.setPromptText("Preco invalido");
			System.out.println("Invalid price");
			allFieldsFilled = false;
		} else {
			priceField.getStylesheets().clear();
		}
		System.out.println("All fields filled");
		return allFieldsFilled;
	}
}