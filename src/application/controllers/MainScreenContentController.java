package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
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
	VBox vbox, buttonsVbox, mapVB;

	@FXML
	StackPane backStackPane;

	@FXML
	JFXButton checkNewRequests;

	@FXML
	JFXButton addInformation;

	private GoogleMapView mapView ;



	@FXML
	public void initialize() {
		mapView = MapManager.getMapManager().getMapView();
		mapVB.getChildren().add(mapView);
		if (LoginDAO.getLogedinUser().getPrivilege() != UserPrivilege.ADMIN)
			buttonsVbox.getChildren().remove(checkNewRequests);
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
	private void centerMap() {
		MapManager.getMapManager().centerMap(searchField.getText());
	}

	@FXML
	public void openPub() {
	}
}

