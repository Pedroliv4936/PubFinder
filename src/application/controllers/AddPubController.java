package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import application.ScreenManager;
import application.models.Pub;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	TextField pubXCoord, pubYCoord, pubOpens,pubCloses;

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
			Int openHour = (pubOpens.getText(0, 1));
			PubDAO.addPendingPub(new Pub(0, name, type, price, 2, "Iade Building", pubXCoordinate, pubYCoordinate, null, null));
			System.out.println(name + " Adicionado � lista de espera");
			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}
}