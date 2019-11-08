package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import application.models.Pub;
import application.models.DAO.PubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class MapManager implements MapComponentInitializedListener, Initializable {

	private static GoogleMapView mapView;
    
    private static GoogleMap map;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mapView = new GoogleMapView("pt-BR", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");
		mapView.addMapInitializedListener(this);
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

	public static GoogleMapView getMapView() {
		return mapView;
	}

	public static void setMapView(GoogleMapView mapView) {
		MapManager.mapView = mapView;
	}

	public static GoogleMap getMap() {
		return map;
	}

	public static void setMap(GoogleMap map) {
		MapManager.map = map;
	}
	
	
	
}
