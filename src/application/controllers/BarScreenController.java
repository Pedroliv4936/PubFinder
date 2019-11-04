package application.controllers;

import java.io.IOException;

import application.Main;
import application.models.DrinkForPub;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class BarScreenController {

	@FXML
	Pane drinkInfo1, drinkInfo2, drinkInfo3;

	@FXML
	Pane pubInfoFront, pubInfoLeft, pubInfoRight;
	
	private Pub selectedPub;
	
	private ObservableList<DrinkForPub> availableDrinks = FXCollections.observableArrayList();
	private ObservableList<Pub> pubs = FXCollections.observableArrayList();

	private int index;
	
	public BarScreenController(Pub pub) {
		availableDrinks.addAll(DrinkDAO.getDrinksInPubs());
		pubs.addAll(PubDAO.getPubList());
		selectedPub = pub;
	}
	
	
	@FXML
	private void initialize() {
		System.out.println("Adicionado bebidas de " + selectedPub.toString());

		displaySelectedPubInfo();
		chooseDisplayedDrinks();
	}
	
	
	private void displaySelectedPubInfo() {
		
		pubInfoFront.getChildren().clear();
		pubInfoLeft.getChildren().clear();
		pubInfoRight.getChildren().clear();

		FXMLLoader pubInfo1Loader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));
		FXMLLoader pubInfo2Loader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));
		FXMLLoader pubInfo3Loader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));

		
		pubInfo1Loader.setController(new PubInfoController(selectedPub));
		pubInfo2Loader.setController(new PubInfoController(pubs.get(1)));
		pubInfo3Loader.setController(new PubInfoController(pubs.get(1)));
		System.out.println("FXMLS carregados" + " enviando " + selectedPub.toString());
		try {
			pubInfoFront.getChildren().add((Pane) pubInfo1Loader.load());
			pubInfoLeft.getChildren().add((Pane) pubInfo2Loader.load());
			pubInfoRight.getChildren().add((Pane) pubInfo3Loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void chooseDisplayedDrinks() {
		drinkInfo1.getChildren().clear();
		drinkInfo2.getChildren().clear();
		drinkInfo3.getChildren().clear();
		availableDrinks = selectedPub.getDrinks();
		FXMLLoader drinkInfo1Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		FXMLLoader drinkInfo2Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		FXMLLoader drinkInfo3Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		drinkInfo1Loader.setController(new DrinkInfoController(selectedPub.getDrinks().get(index)));
		drinkInfo2Loader.setController(new DrinkInfoController(selectedPub.getDrinks().get(index + 1)));
		drinkInfo3Loader.setController(new DrinkInfoController(selectedPub.getDrinks().get(index + 2)));

		try {
			drinkInfo1.getChildren().add((Pane) drinkInfo1Loader.load());
			drinkInfo2.getChildren().add((Pane) drinkInfo2Loader.load());
			drinkInfo3.getChildren().add((Pane) drinkInfo3Loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void changePubDrinksRight() {
		if (index < availableDrinks.size()-3) {
			index++;
		} else {
			index = 0;
		}
		
		chooseDisplayedDrinks();
	}
	
	@FXML
	private void changePubDrinksLeft() {
		if (index > 3) {
			index--;
		} else {
			index = 0;
		}
		
		chooseDisplayedDrinks();
	}
}
