package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;

import application.MapManager;
import application.ScreenManager;
import application.models.UserPrivilege;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.fxml.FXML;
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
		MapManager.getMapManager().createMarkers();
		mapView.setZoom(12);
		MapManager.getMapManager().createGeocodingService();
		mapVB.getChildren().add(mapView);
		backStackPane.getChildren().remove(1);
		if (LoginDAO.getLogedinUser().getPrivilege() != UserPrivilege.ADMIN)
			buttonsVbox.getChildren().remove(checkNewRequests);
		vbox.setPickOnBounds(false);
		MapManager.getMapManager().userLocationMarker();
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

