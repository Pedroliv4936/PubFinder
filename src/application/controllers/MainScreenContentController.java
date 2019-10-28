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
import application.models.DAO.loginDAO;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainScreenContentController implements MapComponentInitializedListener {

	@FXML
	JFXTextField searchField;
	@FXML
	VBox vbox;
	
	@FXML
	JFXButton checkNewRequests;
	
	private GoogleMapView mapView;
    
    private GoogleMap map;
	
	@FXML
	private void initialize() {
		
		vbox.getChildren().clear();
		mapView = new GoogleMapView("en-US", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");
		vbox.getChildren().add(mapView);
		mapView.addMapInitializedListener(this);
		/*if(!loginDAO.getAdminList().contains(loginDAO.getLogedinUser())) {
			vbox.getChildren().remove(checkNewRequests);
		}*/
	}
	
	@FXML
	private void checkNewRequests() {
		ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, "views/CheckNewRequests.fxml", new DefaultHeaderController(), new CheckNewRequestsController());
	}
	
	@FXML
	private void addInformation() {
	ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, "views/AddInformationScreen.fxml", new DefaultHeaderController(), new AddInfoController());
	}

	@Override
	public void mapInitialized() {
		
		  LatLong joeSmithLocation = new LatLong(47.6097, -122.3331);
		  //Set the initial properties of the map.
	        MapOptions mapOptions = new MapOptions();
	        
	        mapOptions.center(new LatLong(47.6097, -122.3331))
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
