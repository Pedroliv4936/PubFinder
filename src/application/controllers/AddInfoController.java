package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.models.DrinkForPub;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AddInfoController {
	@FXML
	JFXTextField preco;
	@FXML
	JFXComboBox<String> barField, tipoField;
	@FXML
	Label pageName;
	JFXTextArea observacoes;
	JFXButton enviar;

	@FXML
	private void initialize() {
		showBarList();
		if(barField.isEditable()) {
			showPubTypes();
		}else
			showDrinkTypes();
	}

	@FXML
	private void addNewBar() {
		barField.setEditable(!barField.isEditable());
		if (barField.isEditable()) {
			pageName.setText("Adicionar Bar");
			barField.setPromptText(" ");
			showBarList();
			showPubTypes();
		} else {
			pageName.setText("Adicionar Bebida");
			showDrinkTypes();
		}
	}

	private void showBarList() {
		ObservableList<String> pubNames = FXCollections.observableArrayList();
		for (Pub pub : PubDAO.getPubList()) {
			pubNames.add(pub.getName());
		}
		barField.setItems(pubNames);
	}
	
	private void showPubTypes() {
		ObservableList<String> pubNames = FXCollections.observableArrayList();
		pubNames.add("Discoteca");
		pubNames.add("Bar");
		pubNames.add("Sala de Jogos");
		tipoField.setItems(pubNames);
	}

	private void showDrinkTypes() {
		ObservableList<String> drinkNames = FXCollections.observableArrayList();
		for (DrinkForPub drink : DrinkDAO.getDrinkList()) {
			if(!drinkNames.contains(drink.getDrink().getName())) {
			drinkNames.add(drink.getDrink().getName());
			}
		}
		tipoField.setItems(drinkNames);
	}

	@FXML
	private void sendInfo() {
		if (barField.isEditable()) {
			PubDAO.getPendingPubs().add(new Pub(0, barField.getPromptText(), 2, "Iade Building", null, null));
		}else {
			DrinkDAO.getPendingDrinkList().add(new DrinkForPub(null, null, 0, 0));
		}
	}
}
