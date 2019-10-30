package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import application.ScreenManager;
import application.models.Pub;
import application.models.DAO.LoginDAO;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainScreenContentController implements MapComponentInitializedListener {

	@FXML
	JFXTextField searchField;
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
	
	@FXML
	private void initialize() {
		backStackPane.getChildren().clear();
		mapView = new GoogleMapView("en-US", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");
		backStackPane.getChildren().addAll(mapView, vbox);
		if(!LoginDAO.getLogedinUser().isAdmin())
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
	private void searchForPub() {
		Pub resultPub;
		resultPub = PubDAO.getPubByName(searchField.getText());
	}
	
	@FXML
	private void addInformation() {
	ScreenManager.setScreen(ScreenContainer.ADD_INFO);
	}

	@Override
	public void mapInitialized() {
		
		  LatLong joeSmithLocation = new LatLong(38.707438, -9.152532);
		  //Set the initial properties of the map.
	        MapOptions mapOptions = new MapOptions();
	        
	        mapOptions.center(new LatLong(38.707438, -9.152532))
	                .mapType(MapTypeIdEnum.ROADMAP)
	                .overviewMapControl(false)
	                .panControl(false)
	                .rotateControl(false)
	                .scaleControl(false)
	                .streetViewControl(false)
	                .zoomControl(false)
	                .zoom(12);
	                   
	        map = mapView.createMap(mapOptions);
	        //Add markers to the map
	        MarkerOptions markerOptions1 = new MarkerOptions();
	        markerOptions1.position(joeSmithLocation);
	        Marker joeSmithMarker = new Marker(markerOptions1);
	        map.addMarker( joeSmithMarker );

				  
	}
	
}
