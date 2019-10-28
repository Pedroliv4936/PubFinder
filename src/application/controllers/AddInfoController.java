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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddInfoController {
	@FXML
	JFXTextField preco;
	
	@FXML
	JFXComboBox<String> barField, tipoField;
	
	@FXML
	Label pageName;
	
	@FXML
	JFXTextArea observacoes;
	
	@FXML
	JFXButton enviar;
	
	@FXML 
	Button  addBarButton;
	
	@FXML
	HBox barHbox;
	
	private TextField barName;
	
	private boolean addingBar = false;

	@FXML
	private void initialize() {
		barName = new JFXTextField();
		barName.setPrefWidth(244);
		barName.setPrefHeight(35);
		barName.setOpacity(0.43);
		barName.setPromptText("Nome do bar...");
		barName.setStyle("-fx-prompt-text-fill: white;-fx-text-fill: white");
		showBarList();
		if(barField.isEditable()) {
			showPubTypes();
		}else
			showDrinkTypes();
	}

	@FXML
	private void addNewBar() {
		barHbox.getChildren().clear();
		addingBar = !addingBar;
		if (addingBar) {
			barHbox.getChildren().addAll(barName, addBarButton);
			pageName.setText("Adicionar Bar");
			System.out.println("Adicionando Bar");
			showPubTypes();
		} else {
			barHbox.getChildren().addAll(barField, addBarButton);
			pageName.setText("Adicionar Bebida");
			System.out.println("Adicionando Bebida");
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
		if (addingBar) {
			PubDAO.getPendingPubs().add(new Pub(0, barName.getText(), 2, "Iade Building", null, null));
			System.out.println(barName.getText() + " Adicionado à lista de espera");
			
		}else {
			DrinkDAO.getPendingDrinkList().add(new DrinkForPub(null, null, 0, 0));
		}
	}
}
