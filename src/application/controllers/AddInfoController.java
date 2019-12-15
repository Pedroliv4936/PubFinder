package application.controllers;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
/**
 * Controlador do fxml AddInfo que contem os FXMLs addPub e addDrink.
 * 
 * @author Franco Zalamena & Pedro Oliveira
 *
 */
public class AddInfoController {

	@FXML
	TabPane tb;
	
	@FXML
	Tab addPubTab, addDrinkTab;
	
	@FXML
	private void initialize() {		
		FXMLLoader pubLoader = new FXMLLoader(Main.class.getResource("views/AddPubScreen.fxml"));
		pubLoader.setController(new AddPubController());
		FXMLLoader drinkLoader = new FXMLLoader(Main.class.getResource("views/AddDrinkScreen.fxml"));
		drinkLoader.setController(new AddDrinkController());
		VBox drinkVbox=null;
		VBox pubVbox = null;
		try {
			drinkVbox = drinkLoader.load();
			pubVbox = pubLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addPubTab.setContent(pubVbox);
		addDrinkTab.setContent(drinkVbox);
	}
	
}
