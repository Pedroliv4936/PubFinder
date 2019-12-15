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
 * e uma barra de pesquisas para se pesquisar os bares pelo nome ou então colocar uma morada e é apresentado ao utilizador o bar mais próximo dessa morada.
 * 
 * @author pedrooliveira and Franco Zalamena
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
	 * Busca o mapView à classe MapManager e depois criado os markers dos bares dentro do mapa, é adicionado o botao de check new requests caso o utilizador seja administrador.
	 * É também buscado o método da localizacao do utilizador à classe MapManger.
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
	 * Remove a vBox que contém o botao para adicionar informacao e a searchField, para o utilizador conseguir manipular o mapa apresentado (fazer zoom, mexer o mapa para os lados, etc).
	 */
	@FXML
	private void removeVBox() {
		backStackPane.getChildren().remove(vbox);
		
	}
	/**
	 * Adiciona a vBox que contém o botao para adicionar informacao e a searchField ao StackPane.
	 */
	@FXML
	private void addVBox() {
		backStackPane.getChildren().add(vbox);
	}
	/**
	 * Método para quando o utilizador clica no botao Check new Requests, que altera o ecrã para o ecrã que apresenta os bares e as bebidas por apresentar.
	 */
	@FXML
	private void checkNewRequests() {
		ScreenManager.setScreen(ScreenContainer.CHECK_NEW_REQUESTS);
	}
	/**
	 * Método para quando o utilizador clica no botao Adicionar Informacao, que altera o ecrã para o Adicionar Informacao.
	 */
	@FXML
	private void addInformation() {
		ScreenManager.setScreen(ScreenContainer.ADD_INFO);
	}
	/**
	 * Busca à classe MapManager o método centerMap com o que o utilizador desejou pesquisar na searchField.
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

