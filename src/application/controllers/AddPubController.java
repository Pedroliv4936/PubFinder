package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.ScreenManager;
import application.models.Drink;
import application.models.DrinkForPub;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddPubController {
	@FXML
	JFXTextField priceField;
	
	@FXML
	JFXComboBox<String> pubType;
	
	@FXML
	JFXTextArea observacoes;
	
	@FXML
	JFXButton enviar;
	
	@FXML
	TextField barName;
	
	@FXML
	TextField pubXCoord, pubYCoord;

	@FXML
	private void initialize() {
		showPubTypes();
	}
	
	private void showPubTypes() {
		ObservableList<String> pubNames = FXCollections.observableArrayList();
		pubNames.add("Discoteca");
		pubNames.add("Bar");
		pubNames.add("Sala de Jogos");
		pubType.setItems(pubNames);
	}

	@FXML
	private void sendInfo() {
			String name = barName.getText();
			String type = pubType.getValue();
			Double price = Double.parseDouble(priceField.getText());
			Double pubXCoordinate = Double.parseDouble(pubXCoord.getText());
			Double pubYCoordinate = Double.parseDouble(pubYCoord.getText());
			PubDAO.addPendingPub(new Pub(0, name, type, price, 2, "Iade Building", pubXCoordinate, pubYCoordinate, null, null));
			System.out.println(name + " Adicionado à lista de espera");
			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}
}