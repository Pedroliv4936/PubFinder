package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;

import application.MapManager;
import application.ScreenContainer;
import application.ScreenManager;
import application.models.Coordinate;
import application.models.Pub;
import application.models.PubType;
import application.models.DAO.PubDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


/**
 * Controlador do fxml destinado a preencher o formulario para adicionar bares à aplicação.
 * 
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public class AddPubController {

	@FXML
	JFXComboBox<PubType> pubType;

	@FXML
	JFXTextArea observacoes;

	@FXML
	JFXButton enviar;

	@FXML
	TextField pubXCoord, pubYCoord, barName, priceField, addressField;

	//@FXML
	//ChoiceBox<Integer> openHour, openMin, closeHour, closeMin;
	
	boolean fieldsFilled;

	boolean focused = false;
	LatLong latLong;
	GeocodingService geocodingService = new GeocodingService();
	@FXML
	private void initialize() {

		addressField.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(!newVal) {
					GeocodingService geocodingService = new GeocodingService();
			        geocodingService.geocode(addressField.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
			            LatLong geocode = new LatLong(0,0);
			            if(status == GeocoderStatus.ZERO_RESULTS) {
			                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
			                alert.show();
			            }
			            else if( results.length > 1 ) {
			            Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
			            alert.show();
			            geocode = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
			        } else {
			            geocode = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
			        }
			            pubXCoord.setText(String.valueOf(geocode.getLatitude()));
			            pubYCoord.setText(String.valueOf(geocode.getLongitude()));
			        });
			}
				focused = newVal;
			System.out.println(oldVal);
			System.out.println(newVal);
		});

		showPubTypes();
		//showHour();
		//showMin();
	}
	/**
	 * Define a lista de tipos de pubs para ser apresentado na ComboBox.
	 */
	private void showPubTypes() {
		ObservableList<PubType> pubTypes = FXCollections.observableArrayList();
		for(PubType pubType : PubDAO.getPubTypes()) {
			pubTypes.add(pubType);
		}
		pubType.setItems(pubTypes);
	}
	
	/**
	 * 
	 */
	/*private void showHour() {

		ObservableList<Integer> hours = FXCollections.observableArrayList();
		for (int h = 0; h < 24; h++) {
			hours.add(h);
		}
		openHour.setItems(hours);
		closeHour.setItems(hours);
		openHour.setValue(0);
		closeHour.setValue(0);
	}

	private void showMin() {
		ObservableList<Integer> min = FXCollections.observableArrayList();
		for (int m = 0; m < 60; m++) {
			min.add(m);
		}
		openMin.setItems(min);
		closeMin.setItems(min);
	}*/

	/**
	 * Adiciona informação preenchida do pub à lista de pubs do PubDAO e muda o ecrã para o main screen.
	 */
	@FXML
	private void sendInfo() {
		if(!fieldsFilled) {
			fieldsFilled();
		} else {
			String name = barName.getText();
			PubType type = pubType.getValue();
			Double price = Double.parseDouble(priceField.getText());
			String address = addressField.getText();
			Double pubXCoordinate = Double.parseDouble(pubXCoord.getText());
			Double pubYCoordinate = Double.parseDouble(pubYCoord.getText());
			//String openTime = (openHour.getValue() + ":" + openMin.getValue());
			//String closeTime = (closeHour.getValue() + ":" + closeMin.getValue());
			int id = PubDAO.getActivePubs().size();
			PubDAO.addPub(new Pub(id, name, type, price, address, new Coordinate(pubXCoordinate, pubYCoordinate), true));
			System.out.println(name + " Adicionado a lista de espera");

			ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
		}
	}
	String addressRegex = "\\w+(\\s\\w+[,]?)+";
/**
 * Certifica que todas as opcoes estao preenchidas, caso alguma opcao nao esteja preenchida é apresentado qual a caixa que falta preencher.
 * @return se todos campos estao preenchidos
 */
	private boolean fieldsFilled() {
		String nameRegex = "^[a-zA-Z]{1}.{0,19}";
		String priceRegex = "^[-]?[0-9]+([.][0-9]+)?$";
		fieldsFilled = true;

		if (!barName.getText().matches(nameRegex)) {
			barName.setPromptText("Nome Invalido");
			barName.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			barName.getStylesheets().clear();
		}
		if (pubType.getValue() == null) {
			pubType.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			pubType.getStylesheets().clear();
		}
		if (!priceField.getText().matches(priceRegex) || priceField.getText().isEmpty()) {
			priceField.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			priceField.getStylesheets().clear();
		}
		if (!addressField.getText().matches(addressRegex)) {
			addressField.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			addressField.getStylesheets().clear();
		}
		if (!pubXCoord.getText().matches(priceRegex)) {
			pubXCoord.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			pubXCoord.getStylesheets().clear();
		}
		if (!pubYCoord.getText().matches(priceRegex)) {
			pubYCoord.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		} else {
			pubYCoord.getStylesheets().clear();
		}
		/*if(openHour.getValue() == null || openMin.getValue() == null || closeHour.getValue() == null || closeMin.getValue() == null) {
			openHour.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			openMin.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			closeHour.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			closeMin.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
			fieldsFilled = false;
		}else {
			openHour.getStylesheets().clear();
			openMin.getStylesheets().clear();
			closeHour.getStylesheets().clear();
			closeMin.getStylesheets().clear();
		}*/
		return fieldsFilled;
	}
}