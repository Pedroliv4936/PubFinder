package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;

import application.MapManager;
import application.ScreenContainer;
import application.ScreenManager;
import application.models.UserPrivilege;
import application.models.DAO.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
/**
 * Controlador do Fxml MainScreenContent que apresenta o mapa, um botao para adicionar informacao, um para aprovar informacao (caso o utilizador seja administrador) 
 * e uma barra de pesquisas para se pesquisar os bares pelo nome ou entao colocar uma morada e e apresentado ao utilizador o bar mais proximo dessa morada.
 * 
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
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


	/**
	 * Busca o mapView a  classe MapManager e depois criado os markers dos bares dentro do mapa, e adicionado o botao de check new requests caso o utilizador seja administrador.
	 * a‰ tambem buscado o metodo da localizacao do utilizador a  classe MapManger.
	 * 
	 * @see application.MapManager#getMapView()
	 * @see application.MapManager#createGeocodingService()
	 * @see application.MapManager#userLocationMarker()
	 */
	@FXML
	public void initialize() {
		mapView = MapManager.getMapManager().getMapView();
		MapManager.getMapManager().createMarkers();
		mapView.setZoom(12);
		MapManager.getMapManager().createGeocodingService();
		mapVB.getChildren().add(mapView);
		if (LoginDAO.getLogedinUser().getPrivilege() != UserPrivilege.ADMIN)
			buttonsVbox.getChildren().remove(checkNewRequests);
		vbox.setPickOnBounds(false);
		MapManager.getMapManager().userLocationMarker();
	}
	/**
	 * Remove a vBox que contem o botao para adicionar informacao e a searchField, para o utilizador conseguir manipular o mapa apresentado (fazer zoom, mexer o mapa para os lados, etc).
	 */
	@FXML
	private void removeVBox() {
		backStackPane.getChildren().remove(vbox);
		
	}
	/**
	 * Adiciona a vBox que contem o botao para adicionar informacao e a searchField ao StackPane.
	 */
	@FXML
	private void addVBox() {
		backStackPane.getChildren().add(vbox);
	}
	/**
	 * Metodo para quando o utilizador clica no botao Check new Requests, que altera o ecra para o ecra que apresenta os bares e as bebidas por apresentar.
	 */
	@FXML
	private void checkNewRequests() {
		ScreenManager.setScreen(ScreenContainer.CHECK_NEW_REQUESTS);
	}
	/**
	 * Metodo para quando o utilizador clica no botao Adicionar Informacao, que altera o ecra para o Adicionar Informacao.
	 */
	@FXML
	private void addInformation() {
		ScreenManager.setScreen(ScreenContainer.ADD_INFO);
	}
	/**
	 * Busca a  classe MapManager o metodo centerMap com o que o utilizador desejou pesquisar na searchField.
	 * 
	 * @see application.MapManager#centerMap(String)
	 */
	@FXML
	private void centerMap() {
		MapManager.getMapManager().centerMap(searchField.getText());
	}

	@FXML
	public void openPub() {
	}
}

