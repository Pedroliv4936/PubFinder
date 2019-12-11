package application;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;

import application.controllers.BarScreenController;
import application.controllers.DefaultHeaderController;
import application.models.Pub;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class MapManager implements MapComponentInitializedListener{


	private GoogleMapView mapView;
    
    private GoogleMap map;
    
    private static MapManager mainMap;
    
	private GeocodingService geocodingService;

	private MapManager() {
		mapView = new GoogleMapView("pt-BR", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");
		mapView.addMapInializedListener(this);
	}

	@Override
	public void mapInitialized() {
		geocodingService = new GeocodingService();
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
	}
	
	public void createMarkers() {
		ObservableList<Marker> pubMarkers = FXCollections.observableArrayList();
		for(Pub pub : PubDAO.getActivePubs()) {
			MarkerOptions markerOptions = new MarkerOptions();
			LatLong latLong = new LatLong(pub.getCoordinates().getX(), pub.getCoordinates().getY());
	        markerOptions.position(latLong);
	        Marker newPubMarker = new Marker(markerOptions);
	        pubMarkers.add(newPubMarker);
	        System.out.println();
			System.out.println("Pub with coordinates : " + pub.getCoordinates().getX() + " and " + pub.getCoordinates().getY() + " added to map");
		}
		map.addMarkers(pubMarkers);
	}
	
	public void centerMap(String address) {
	        geocodingService.geocode(address, (GeocodingResult[] results, GeocoderStatus status) -> {
	            
	            LatLong latLong = null;
	            
	            if( status == GeocoderStatus.ZERO_RESULTS) {
	                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
	                alert.show();
	                return;
	            } else if( results.length > 1 ) {
	                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
	                alert.show();
	                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
	            } else {
	                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
	            }
	            
	            map.setCenter(latLong);
	            PubDAO.setPubsOrdered(latLong.getLatitude(), latLong.getLongitude());
	            ScreenManager.setScreen(new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml",
				new DefaultHeaderController(), new BarScreenController(PubDAO.getPubsOrdered().get(0))));
	        });
	}
	
	public static void createMap() {
		if (mainMap == null)
			mainMap = new MapManager();
	}
	
	public static MapManager getMapManager() {
		createMap();
		return mainMap;
	}
	
	public GoogleMapView getMapView() {
		return mapView;
	}

	public  GoogleMap getMap() {
		return map;
	}

}

