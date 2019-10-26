package application.controllers;

import com.jfoenix.controls.JFXButton;

import application.models.DrinkForPub;
import application.models.DAO.DrinkDAO;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
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
		drinkColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDrink().getName()));
		barColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPub().getName()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<DrinkForPub, Double>("price"));
		ratingColumn.setCellValueFactory(new PropertyValueFactory<DrinkForPub, Double>("rating"));
		publistTV.setItems(DrinkDAO.getDrinkList());
	}
}
