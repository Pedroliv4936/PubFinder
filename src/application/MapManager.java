package application;

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

public class MapManager implements MapComponentInitializedListener{

	private static GoogleMapView mapView = new GoogleMapView("pt-BR", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");
    
    private static GoogleMap map;
    
    public static MapManager mainMap = new MapManager();

	public MapManager() {
		mapView.addMapInitializedListener(this);
	}

	@Override
	public void mapInitialized() {
        MarkerOptions markerOptions = new MarkerOptions();
		ObservableList<Marker> pubMarkers = FXCollections.observableArrayList();
		for(Pub pub : PubDAO.getActivePubs()) {
			LatLong latLong = new LatLong(pub.getCoordinates().getX(), pub.getCoordinates().getY());
	        markerOptions.position(latLong);
	        Marker newPubMarker = new Marker(markerOptions);
	        newPubMarker.setTitle(pub.toString() + " Marker");
	        pubMarkers.add(newPubMarker);
	        System.out.println();
			System.out.println("Pub with coordinates : " + pub.getCoordinates().getX() + " and " + pub.getCoordinates().getY() + " added to map");
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
	        //Add markers to the map
	        map.addMarkers(pubMarkers);  
	        
	        map = mapView.createMap(mapOptions);
	
	}

	public static GoogleMapView getMapView() {
		return mapView;
	}

	public void setMapView(GoogleMapView mapView) {
		MapManager.mapView = mapView;
	}

	public  GoogleMap getMap() {
		return map;
	}

	public void setMap(GoogleMap map) {
		MapManager.map = map;
	}
}
