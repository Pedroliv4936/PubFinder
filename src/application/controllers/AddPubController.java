package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.models.Drink;
import application.models.DrinkForPub;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
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
			PubDAO.addPendingPub(new Pub(0, barName.getText(), pubType.getValue(), Double.parseDouble(priceField.getText()), 2, "Iade Building", null, null));
			System.out.println(barName.getText() + " Adicionado � lista de espera");
	}
}