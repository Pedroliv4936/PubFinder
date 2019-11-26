package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;

import application.ScreenManager;
import application.models.Pub;
import application.models.DAO.LoginDAO;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainScreenContentController implements MapComponentInitializedListener {

	@FXML
	TextField searchField;

	@FXML
	VBox vbox, buttonsVbox;

	@FXML
	StackPane backStackPane;

	@FXML
	JFXButton checkNewRequests;

	@FXML
	JFXButton addInformation;

	private GoogleMapView mapView;

	private GoogleMap map;

	private StringProperty address = new SimpleStringProperty();

	private GeocodingService geocodingService;

	@FXML
	public void initialize() {
		address.bind(searchField.textProperty());

		backStackPane.getChildren().clear();

		mapView = new GoogleMapView("pt-BR", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");

		backStackPane.getChildren().addAll(mapView, vbox);

		if (!LoginDAO.getLogedinUser().isAdmin())
			buttonsVbox.getChildren().remove(checkNewRequests);

		backStackPane.setPickOnBounds(false);
		vbox.setPickOnBounds(false);
		mapView.addMapInitializedListener(this);
	}

	@FXML
	private void checkNewRequests() {
		ScreenManager.setScreen(ScreenContainer.CHECK_NEW_REQUESTS);
	}

	@FXML
	private void addInformation() {
		ScreenManager.setScreen(ScreenContainer.ADD_INFO);
	}

	@FXML
	public void centerMap(ActionEvent event) {
		geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {

			LatLong latLong = null;

			if (status == GeocoderStatus.ZERO_RESULTS) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
				alert.show();
				return;
			} else if (results.length > 1) {
				Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
				alert.show();
				latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
						results[0].getGeometry().getLocation().getLongitude());
			} else {
				latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
						results[0].getGeometry().getLocation().getLongitude());
			}

			map.setCenter(latLong);

		});
	}

	@Override
	public void mapInitialized() {
		geocodingService = new GeocodingService();
		MarkerOptions markerOptions = new MarkerOptions();
		ObservableList<Marker> pubMarkers = FXCollections.observableArrayList();
		for (Pub pub : PubDAO.getPubList()) {
			LatLong latLong = pub.getCoordinates();
			markerOptions.position(latLong);
			Marker newPubMarker = new Marker(markerOptions);
			newPubMarker.setTitle(pub.toString() + " Marker");
			pubMarkers.add(newPubMarker);
			System.out.println();
			System.out.println("Pub with coordinates : " + pub.getCoordinates().getLongitude() + " and "
					+ pub.getCoordinates().getLatitude() + " added to map");
		}
		// Set the initial properties of the map.

		MapOptions mapOptions = new MapOptions();

		mapOptions.center(new LatLong(38.707438, -9.152532)).mapType(MapTypeIdEnum.ROADMAP).overviewMapControl(false)
				.panControl(false).rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(false)
				.zoom(15).mapTypeControl(false);

		map = mapView.createMap(mapOptions);

		map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
			LatLong latLong = event.getLatLong();
			System.out.println("Latitude: " + latLong.getLatitude());
			System.out.println("Longitude: " + latLong.getLongitude());
		});

		// Add markers to the map
		map.addMarkers(pubMarkers);
	}

	@FXML
	public void openPub() {
	}
}
