package application.controllers;

import java.io.IOException;

import com.lynden.gmapsfx.GoogleMapView;

import application.Main;
import application.MapManager;
import application.ScreenContainer;
import application.ScreenManager;
import application.models.DrinkForSale;
import application.models.Pub;
import application.models.DAO.PubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
/**
 * Controlador do FXML BarScreen que apresenta a informacao dos pubs ao utilizador.
 * 
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public class BarScreenController{

	@FXML
	Pane drinkInfo1, drinkInfo2, drinkInfo3;

	@FXML
	Pane pubInfoFront, pubInfoLeft, pubInfoRight;

	@FXML
	private StackPane bgStackPane;

	@FXML
	private VBox vbox;

	private GoogleMapView mapView;

	private Pub selectedPub;
	
	private int drinksIndex;
	
	private ObservableList<DrinkForSale> availableDrinks = FXCollections.observableArrayList();
	
	private int index;
	
	FXMLLoader pubFrontLoader;
	FXMLLoader pubLeftLoader;
	FXMLLoader pubRightLoader;
	
	/**
	 * Construtor do controlador que define o Index do Pub selecionado, da lista dos Pubs Ordenados por Distancia e apresenta o index e o nome na consola.
	 * 
	 * @param pub Pub que o utilizador quer visualizar.
	 */
	public BarScreenController(Pub pub) {
		selectedPub = pub;
		pub.setRating();
		index = PubDAO.getPubsOrdered().indexOf(selectedPub);
		System.out.println("Pub com index : " + index);
		System.out.println("Nome do Pub: " + selectedPub.toString());
	}
	/**
	 * Busca o mapa e o centraliza no pub selecionado com mais zoom. Mostra as bebidas do pub selecionado e os mapas seguintes (em relaçao à distancia) nas respetivas panes.
	 * 
	 * @see application.MapManager#getMapView()
	 * @see #chooseDisplayedDrinks()
	 * @see #displaySelectedPubInfo()
	 */
	@FXML
	private void initialize() {
		mapView = MapManager.getMapManager().getMapView();
		mapView.setZoom(15);
		mapView.setCenter(selectedPub.getCoordinates().getX(), selectedPub.getCoordinates().getY());

		bgStackPane.getChildren().add(0, mapView);

		vbox.setPickOnBounds(false);
		displaySelectedPubInfo();
		chooseDisplayedDrinks();
	}
	/**
	 * Apaga a informação colocada anteriormente dentro das panes pubInfoFront,pubInfoLeft,pubInfoRight e preenche-as com a informacao do  pub selecionado. 
	 */
	private void displaySelectedPubInfo() {
		pubInfoFront.getChildren().clear();
		pubInfoLeft.getChildren().clear();
		pubInfoRight.getChildren().clear();
		
		pubFrontLoader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));
		pubLeftLoader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));
		pubRightLoader = new FXMLLoader(Main.class.getResource("views/PubInfo.fxml"));
		
		pubFrontLoader.setController(new PubInfoController(selectedPub));
		if(index <= 0)
		pubLeftLoader.setController(new PubInfoController(PubDAO.getPubsOrdered().get(PubDAO.getPubsOrdered().size() - 1)));
		else {
		pubLeftLoader.setController(new PubInfoController(PubDAO.getPubsOrdered().get(index - 1)));
		}
		if(index + 1 < PubDAO.getPubsOrdered().size())
		pubRightLoader.setController(new PubInfoController(PubDAO.getPubsOrdered().get(index + 1)));
		else {
		pubRightLoader.setController(new PubInfoController(PubDAO.getPubsOrdered().get(0)));
		}
		
		try {
			pubInfoFront.getChildren().add((Pane) pubFrontLoader.load());
			pubInfoLeft.getChildren().add((Pane) pubLeftLoader.load());
			pubInfoRight.getChildren().add((Pane) pubRightLoader.load());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Apaga a informação colocada anteriormente dentro das panes drinkInfo1, drinkInfo2, drinkInfo3 e preenche-as com as bebidas do pub selecionado. 
	 */
	private void chooseDisplayedDrinks() {
		drinkInfo1.getChildren().clear();
		drinkInfo2.getChildren().clear();
		drinkInfo3.getChildren().clear();
		availableDrinks = selectedPub.getDrinks();
		FXMLLoader drinkInfo1Loader;
		FXMLLoader drinkInfo2Loader;
		FXMLLoader drinkInfo3Loader;
		try {
		if(drinksIndex < availableDrinks.size()) {
			drinkInfo1Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		drinkInfo1Loader.setController(new DrinkInfoController(availableDrinks.get(drinksIndex)));
		drinkInfo1.getChildren().add((Pane) drinkInfo1Loader.load());
		}
		if(drinksIndex + 1 < availableDrinks.size()) {
			drinkInfo2Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		drinkInfo2Loader.setController(new DrinkInfoController(availableDrinks.get(drinksIndex + 1)));
		drinkInfo2.getChildren().add((Pane) drinkInfo2Loader.load());
		}
		if(drinksIndex + 2 < availableDrinks.size()) {
		drinkInfo3Loader = new FXMLLoader(Main.class.getResource("views/AvailableDrinks.fxml"));
		drinkInfo3Loader.setController(new DrinkInfoController(availableDrinks.get(drinksIndex + 2)));
		drinkInfo3.getChildren().add((Pane) drinkInfo3Loader.load());
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Aumenta o index da bebida mais por um de forma a apresentar a bebida seguinte.
	 */
	@FXML
	private void changePubDrinksRight() {
		if (drinksIndex < availableDrinks.size() - 3) {
			drinksIndex++;
		} else {
			drinksIndex = 0;
		}

		chooseDisplayedDrinks();
	}
	/**
	 * Diminui o index da bebida por um de forma a apresentar a bebida seguinte.
	 */
	@FXML
	private void changePubDrinksLeft() {
		if (drinksIndex > 3) {
			drinksIndex--;
		} else {
			drinksIndex = 0;
		}

		chooseDisplayedDrinks();
	}
	/**
	 * Troca de pub para o segundo pub mais proximo.
	 */
	@FXML 
	private void next() {
        ScreenManager.setScreen(new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml",
		new DefaultHeaderController(), new BarScreenController(((PubInfoController)pubRightLoader.getController()).getPub())));
	}
	/**
	 * troca de pub para o pub visualizado antes.
	 */
	@FXML
	private void prev() {
        ScreenManager.setScreen(new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml",
		new DefaultHeaderController(), new BarScreenController(((PubInfoController)pubLeftLoader.getController()).getPub())));
	}
}
