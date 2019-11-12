package application.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainScreenContentController implements MapComponentInitializedListener, Initializable {
	
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
	
	
	@FXML
	private void checkNewRequests() {
		ScreenManager.setScreen(ScreenContainer.CHECK_NEW_REQUESTS);
	}
	
	private ArrayList<String> possiblePubs() {
		ArrayList<String> possiblePubNames = new ArrayList<>();
		
		return possiblePubNames; 
	}
	
	@FXML
	private void addInformation() {
	ScreenManager.setScreen(ScreenContainer.ADD_INFO);
	}

	@Override
	public void mapInitialized() {
        MarkerOptions markerOptions = new MarkerOptions();
		ObservableList<Marker> pubMarkers = FXCollections.observableArrayList();
		for(Pub pub : PubDAO.getPubList()) {
			LatLong latLong = new LatLong(pub.getxCoord(), pub.getyCoord());
	        markerOptions.position(latLong);
	        Marker newPubMarker = new Marker(markerOptions);
	        newPubMarker.setTitle(pub.toString() + " Marker");
	        pubMarkers.add(newPubMarker);
	        System.out.println();
			System.out.println("Pub with coordinates : " + pub.getxCoord() + " and " + pub.getyCoord() + " added to map");
		}
		  //Set the initial properties of the map.
		
	        MapOptions mapOptions = new MapOptions();
	        
	        mapOptions.center(new LatLong(38.707438, -9.152532))
	                .mapType(MapTypeIdEnum.ROADMAP)
	                .overviewMapControl(true)
	                .panControl(false)
	                .rotateControl(false)
	                .scaleControl(false)
	                .streetViewControl(false)
	                .zoomControl(false)
	                .zoom(12);
	                   
	        map = mapView.createMap(mapOptions);
	        //Add markers to the map
	        map.addMarkers(pubMarkers);  
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<String> autoCompletePubs = new ArrayList<>();
		for(Pub pub : PubDAO.getPubList()) {
			autoCompletePubs.add(pub.toString());
			System.out.println(pub.toString());
		}
		TextFields.bindAutoCompletion(searchField, autoCompletePubs);
		backStackPane.getChildren().clear();
		mapView = new GoogleMapView("pt-BR", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");
		backStackPane.getChildren().addAll(mapView, vbox);
		if(!LoginDAO.getLogedinUser().isAdmin())
		buttonsVbox.getChildren().remove(checkNewRequests);
		
		backStackPane.setPickOnBounds(false);
		vbox.setPickOnBounds(false);
		mapView.addMapInitializedListener(this);
		
	}
	
	@FXML
	public void openPub() {
		Pub searchenPub = PubDAO.getPubByName(searchField.getText());
		ScreenManager.setScreen(new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml", new DefaultHeaderController(), new BarScreenController(searchenPub)));
	}
}
