package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.ScreenManager;
import application.models.Pub;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddPubController {

	@FXML
	JFXComboBox<String> pubType;

	@FXML
	JFXTextArea observacoes;

	@FXML
	JFXButton enviar;

	@FXML
	TextField pubXCoord, pubYCoord, barName, priceField, addressField;

	@FXML
	ChoiceBox<Integer> openHour, openMin, closeHour, closeMin;
	
	boolean fieldsFilled;

	@FXML
	private void initialize() {
		showPubTypes();
		showHour();
		showMin();
	}

	private void showPubTypes() {
		ObservableList<String> pubNames = FXCollections.observableArrayList();
		pubNames.add("Discoteca");
		pubNames.add("Bar");
		pubNames.add("Sala de Jogos");
		pubType.setItems(pubNames);
	}

	private void showHour() {

		ObservableList<Integer> hours = FXCollections.observableArrayList();
		for (int h = 0; h < 24; h++) {
			hours.add(h);
		}
		openHour.setItems(hours);
		closeHour.setItems(hours);
		openHour.setValue(0);
		closeHour.setValue(0);
	}

	private void showMin() {
		ObservableList<Integer> min = FXCollections.observableArrayList();
		for (int m = 0; m < 60; m++) {
			min.add(m);
		}
		openMin.setItems(min);
		closeMin.setItems(min);
	}

	@FXML
	private void sendInfo() {
		if(!fieldsFilled) {
			fieldsFilled();
		} else {
			String name = barName.getText();
			String type = pubType.getValue();
			Double price = Double.parseDouble(priceField.getText());
			String address = addressField.getText();
			Double pubXCoordinate = Double.parseDouble(pubXCoord.getText());
			Double pubYCoordinate = Double.parseDouble(pubYCoord.getText());
			String openTime = (openHour.getValue() + ":" + openMin.getValue());
			String closeTime = (closeHour.getValue() + ":" + closeMin.getValue());
			int id = PubDAO.getPubList().size();
			PubDAO.addPendingPub(new Pub(id, name, type, price, 4, address, pubXCoordinate, pubYCoordinate, openTime,
					closeTime, null));
			System.out.println(name + " Adicionado a lista de espera");

			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
		}
	}

	private boolean fieldsFilled() {
		String nameRegex = "^[a-zA-Z]{1}.{0,19}";
		String priceRegex = "^[0-9]+(,\\\\d{3})*([,.]\\\\d+)?$";
		String addressRegex = "\\w+(\\s\\w+[,]?)+";
		fieldsFilled = true;

		if (!barName.getText().matches(nameRegex)) {
			barName.setPromptText("Nome Invalido");
			barName.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			barName.getStylesheets().clear();
		}
		if (pubType.getValue() == null) {
			pubType.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			pubType.getStylesheets().clear();
		}
		if (!priceField.getText().matches(priceRegex) || priceField.getText().isEmpty()) {
			priceField.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			priceField.getStylesheets().clear();
		}
		if (!addressField.getText().matches(addressRegex)) {
			addressField.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			addressField.getStylesheets().clear();
		}
		if (!pubXCoord.getText().matches(priceRegex)) {
			pubXCoord.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			pubXCoord.getStylesheets().clear();
		}
		if (!pubYCoord.getText().matches(priceRegex)) {
			pubYCoord.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			pubYCoord.getStylesheets().clear();
		}
		if(openHour.getValue() == null || openMin.getValue() == null || closeHour.getValue() == null || closeMin.getValue() == null) {
			openHour.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			openMin.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			closeHour.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			closeMin.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		}else {
			openHour.getStylesheets().clear();
			openMin.getStylesheets().clear();
			closeHour.getStylesheets().clear();
			closeMin.getStylesheets().clear();
		}
		return fieldsFilled;
	}
}