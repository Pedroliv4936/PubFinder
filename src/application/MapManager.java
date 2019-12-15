package application;

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
import com.lynden.gmapsfx.util.MarkerImageFactory;

import application.controllers.BarScreenController;
import application.controllers.DefaultHeaderController;
import application.models.Pub;
import application.models.DAO.PubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
/**
 * Classe responsavel por adicionar o Google maps à aplicacao
 * @author pedrooliveira
 *
 */
public class MapManager implements MapComponentInitializedListener{
	
	private GoogleMapView mapView;
	static Marker userLoc;
    
    private GoogleMap map;
    
    private static MapManager mainMap;
    
	private GeocodingService geocodingService;

	private MapManager() {
		mapView = new GoogleMapView("pt-BR", "AIzaSyDxUrIiTvQ6FSgAUULl9JF4AS6Jfz-35gc");
		mapView.addMapInializedListener(this);
	}
	/**
	 * Inicia o mapa com as definiçoes/propriedades.
	 */
	@Override
	public void mapInitialized() {
	        MapOptions mapOptions = new MapOptions();
	        mapOptions.center(new LatLong(38.707438, -9.152532))
            .mapType(MapTypeIdEnum.ROADMAP)
            .mapTypeControl(false)
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(12);	      
	        
	        map = mapView.createMap(mapOptions);
	}
	/**
	 * Cria os markers de todos os Pubs (disponiveis) da base de Dados.
	 */
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
	/**
	 * Cria o marker e envia as coordenadas do marker para a consola da posiçao que o utilizador carregou com o rato, de forma a simular a posicao do utilizador.
	 */
	public void userLocationMarker() {
		MarkerOptions markerOptions = new MarkerOptions();
		userLoc= new Marker(markerOptions);
        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
        	 map.removeMarker(userLoc);
        LatLong mouseLatLong = event.getLatLong();
        	   System.out.println("Latitude: " + mouseLatLong.getLatitude());
        	   System.out.println("Longitude: " + mouseLatLong.getLongitude());
        markerOptions.title("User Location");
        markerOptions.position(mouseLatLong);
        userLoc= new Marker(markerOptions);
        map.addMarker(userLoc);
        });
	}
	/**
	 * Cria o servico de Geocoding que é utilizado para pesquisar bares pela morada.
	 */
	public void createGeocodingService() {
		geocodingService = new GeocodingService();
	}
	/**
	 * Centraliza o mapa no Pub mais proximo da morada que o utilizador introduziu.E muda a tela para o barScreen
	 * 
	 * @param address Morada que o utilizador introduziu na barra de pesquisa.
	 * 
	 * @see application.models.DAO.PubDAO#getPubsOrdered()
	 * @see application.controllers.BarScreenController
	 */
	public void centerMap(String address) {
	        geocodingService.geocode(address, (GeocodingResult[] results, GeocoderStatus status) -> {
	            
	            LatLong latLong = null;
	            
            	for(Pub pub : PubDAO.getActivePubs()) {
            		if(pub.toString().toLowerCase().equals(address.toLowerCase())) {
            			latLong = new LatLong(pub.getCoordinates().getX(), pub.getCoordinates().getY());
        	            map.setCenter(latLong);
        	            PubDAO.setPubsOrdered(latLong.getLatitude(), latLong.getLongitude());
        	            ScreenManager.setScreen(new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml",
        				new DefaultHeaderController(), new BarScreenController(PubDAO.getPubsOrdered().get(0))));
        	            System.out.println("ENCONTROU!");
        	            return;
            		}
            	}
            	
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
	/**
	 * Cria um novo mainMap caso não exista já.
	 */
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

