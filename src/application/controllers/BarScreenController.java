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

public class BarScreenController implements MapComponentInitializedListener {

	@FXML
	Pane drinkInfo1, drinkInfo2, drinkInfo3;

	@FXML
	Pane pubInfoFront, pubInfoLeft, pubInfoRight;

	@FXML
	private StackPane bgStackPane;

	@FXML
	private VBox vbox;

	private GoogleMapView mapView;

	private GoogleMap map;

	private Pub selectedPub;
	
	private int index;
	
	private ObservableList<DrinkForSale> availableDrinks = FXCollections.observableArrayList();

	public BarScreenController(Pub pub) {
		selectedPub = pub;
	}

	@FXML
	private void initialize() {
		System.out.println("Adicionado bebidas de " + selectedPub.toString());
		bgStackPane.getChildren().clear();

		mapView = new GoogleMapView("pt-BR", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");

		mapView.addMapInializedListener(this);

		bgStackPane.getChildren().addAll(mapView, vbox);

		vbox.setPickOnBounds(false);
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
		
		int index = PubDAO.getActivePubs().indexOf(selectedPub);
		PubInfoController pubInfoFrontController = new PubInfoController(selectedPub);
		PubInfoController pubInfoLeftController;
		if(PubDAO.getActivePubs().indexOf(selectedPub)<=0 && PubDAO.getActivePubs().size() > 1) {
			pubInfoLeftController = new PubInfoController(PubDAO.getActivePubs().get(PubDAO.getActivePubs().size() - 1));
		}
		pubInfoLeftController = new PubInfoController(PubDAO.getActivePubs().get(index - 1));
		PubInfoController pubInfoRightController;
		if(PubDAO.getActivePubs().size() > 1) {
			pubInfoRightController = new PubInfoController(PubDAO.getActivePubs().get(index + 1));
		}

		pubInfo1Loader.setController(pubInfoFrontController);
		pubInfo2Loader.setController(pubInfoLeftController);

		
		System.out.println("FXMLS carregados" + " enviando " + selectedPub.toString());
		try {
			pubInfoFront.getChildren().add((Pane) pubInfo1Loader.load());
			pubInfoLeft.getChildren().add((Pane) pubInfo2Loader.load());
			pubInfoRight.getChildren().add((Pane) pubInfo3Loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}

		ScreenContainer screen2 = new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml", new DefaultHeaderController(), new BarScreenController(pubInfoLeftController.getPub()));
		pubInfoLeft.setOnMouseClicked(e -> {
			
			ScreenManager.setScreen(screen2);
		});
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
		if (index < availableDrinks.size() - 3) {
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

	@Override
	public void mapInitialized() {
		MarkerOptions markerOptions = new MarkerOptions();
		ObservableList<Marker> pubMarkers = FXCollections.observableArrayList();

		LatLong latLong = new LatLong(selectedPub.getCoordinates().getX(), selectedPub.getCoordinates().getY());
		markerOptions.position(latLong);
		Marker newPubMarker = new Marker(markerOptions);
		newPubMarker.setTitle(selectedPub.toString() + " Marker");
		pubMarkers.add(newPubMarker);
		System.out.println();
		System.out.println("Pub with coordinates : " + selectedPub.getCoordinates().getX() + " and " + selectedPub.getCoordinates().getY() + " added to map");

		// Set the initial properties of the map.

		MapOptions mapOptions = new MapOptions();

		mapOptions.center(new LatLong(selectedPub.getCoordinates().getX(), selectedPub.getCoordinates().getY()))
		.mapType(MapTypeIdEnum.ROADMAP)
		.overviewMapControl(false)
		.panControl(false)
		.rotateControl(false)
		.scaleControl(false)
		.streetViewControl(false)
		.zoomControl(false)
		.zoom(15)
		.mapTypeControl(false);

		map = mapView.createMap(mapOptions);
		// Add markers to the map
		map.addMarkers(pubMarkers);
	}
}
