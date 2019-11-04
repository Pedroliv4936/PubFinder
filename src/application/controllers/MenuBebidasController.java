package application.controllers;

import com.jfoenix.controls.JFXButton;

import application.Main;
import application.ScreenManager;
import application.models.DrinkForPub;
import application.models.DAO.DrinkDAO;
import application.views.ScreenContainer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class MenuBebidasController {

	@FXML
	JFXButton bebidasButton, logoButton, userButton;
	@FXML
	TableView<DrinkForPub> publistTV;
	@FXML
	TableColumn<DrinkForPub, String> drinkColumn;
	@FXML
	TableColumn<DrinkForPub, String> barColumn;
	@FXML
	TableColumn<DrinkForPub, Double> priceColumn;
	@FXML
	TableColumn<DrinkForPub, Double> ratingColumn;
	@FXML
	HBox hBox;

	@FXML
	private void initialize() {		
		publistTV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		        openBarInfo(newSelection);
		});
		drinkColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDrinkType().toString()));
		barColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPub().toString()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<DrinkForPub, Double>("price"));
		ratingColumn.setCellValueFactory(new PropertyValueFactory<DrinkForPub, Double>("rating"));
		publistTV.setItems(DrinkDAO.getDrinksInPubs());
	}
	
	private void openBarInfo(DrinkForPub drink) {
		ScreenContainer screen = new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml", new DefaultHeaderController(), new BarScreenController(drink.getPub()));
		ScreenManager.setScreen(screen);
	}
}
