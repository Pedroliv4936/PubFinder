package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;

import application.MapManager;
import application.ScreenManager;
import application.models.UserPrivilege;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainScreenContentController{

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

	private GoogleMapView mapView ;

	private GoogleMap map;

	private StringProperty address = new SimpleStringProperty();

	private GeocodingService geocodingService;

	@FXML
	public void initialize() {
		address.bind(searchField.textProperty());

		backStackPane.getChildren().clear();

		mapView = MapManager.getMapView();
		mapView.minHeight(Control.USE_COMPUTED_SIZE);
		mapView.minWidth(Control.USE_COMPUTED_SIZE);
		mapView.prefHeight(Control.USE_COMPUTED_SIZE);
		mapView.prefWidth(Control.USE_COMPUTED_SIZE);
		

		backStackPane.getChildren().addAll(mapView, vbox);

		if (LoginDAO.getLogedinUser().getPrivilege() != UserPrivilege.ADMIN)
			buttonsVbox.getChildren().remove(checkNewRequests);

		backStackPane.setPickOnBounds(false);
		vbox.setPickOnBounds(false);
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

	@FXML
	public void openPub() {
	}
}
