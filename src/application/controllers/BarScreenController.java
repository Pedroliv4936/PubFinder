package application.controllers;

import java.io.IOException;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import application.Main;
import application.MapManager;
import application.ScreenManager;
import application.models.DrinkForSale;
import application.models.Pub;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BarScreenController{

	@FXML
	Pane drinkInfo1, drinkInfo2, drinkInfo3;

	@FXML
	Pane pubInfoFront, pubInfoLeft, pubInfoRight;

	@FXML
	private StackPane bgStackPane;

	@FXML
	private VBox vbox;

	private GoogleMapView mapView;

	private Pub selectedPub;
	
	private int drinksIndex;
	
	private ObservableList<DrinkForSale> availableDrinks = FXCollections.observableArrayList();

	public BarScreenController(Pub pub) {
		selectedPub = pub;
	}

	@FXML
	private void initialize() {
		System.out.println("Adicionado bebidas de " + selectedPub.toString());
		bgStackPane.getChildren().clear();

		mapView = new GoogleMapView("pt-BR", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");

		mapView = MapManager.getMapManager().getMapView();

		bgStackPane.getChildren().addAll(mapView, vbox);

		vbox.setPickOnBounds(false);
		displaySelectedPubInfo();
		chooseDisplayedDrinks();
	}

	private void displaySelectedPubInfo() {
		pubInfoFront.getChildren().clear();
		pubInfoLeft.getChildren().clear();
		pubInfoRight.getChildren().clear();
		
		FXMLLoader pubFrontLoader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));
		FXMLLoader pubLeftLoader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));
		FXMLLoader pubRightLoader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));
		
		pubFrontLoader.setController(new PubInfoController(PubDAO.getPubsOrdered().get(0)));
		pubLeftLoader.setController(new PubInfoController(PubDAO.getPubsOrdered().get(0)));
		pubRightLoader.setController(new PubInfoController(PubDAO.getPubsOrdered().get(0)));
		
		try {
			pubInfoFront.getChildren().add((Pane) pubFrontLoader.load());
			pubInfoLeft.getChildren().add((Pane) pubLeftLoader.load());
			pubInfoRight.getChildren().add((Pane) pubRightLoader.load());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void chooseDisplayedDrinks() {
		drinkInfo1.getChildren().clear();
		drinkInfo2.getChildren().clear();
		drinkInfo3.getChildren().clear();
		System.out.println("SELECTED PUB IS: " + selectedPub.toString());
		availableDrinks = selectedPub.getDrinks();
		FXMLLoader drinkInfo1Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		FXMLLoader drinkInfo2Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		FXMLLoader drinkInfo3Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		drinkInfo1Loader.setController(new DrinkInfoController(availableDrinks.get(drinksIndex)));
		drinkInfo2Loader.setController(new DrinkInfoController(availableDrinks.get(drinksIndex + 1)));
		drinkInfo3Loader.setController(new DrinkInfoController(availableDrinks.get(drinksIndex + 2)));

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
		if (drinksIndex < availableDrinks.size() - 3) {
			drinksIndex++;
		} else {
			drinksIndex = 0;
		}

		chooseDisplayedDrinks();
	}

	@FXML
	private void changePubDrinksLeft() {
		if (drinksIndex > 3) {
			drinksIndex--;
		} else {
			drinksIndex = 0;
		}

		chooseDisplayedDrinks();
	}
}
