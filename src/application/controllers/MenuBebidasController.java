package application.controllers;

import com.jfoenix.controls.JFXButton;

import application.ScreenManager;
import application.models.Drink;
import application.models.DrinkForSale;
import application.models.DAO.DrinkDAO;
import application.models.DAO.LoginDAO;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MenuBebidasController {

	@FXML
	JFXButton bebidasButton, logoButton, userButton;
	@FXML
	TableView<DrinkForSale> publistTV;
	@FXML
	TableColumn<DrinkForSale, String> drinkColumn;
	@FXML
	TableColumn<DrinkForSale, String> barColumn;
	@FXML
	TableColumn<DrinkForSale, Double> priceColumn;
	@FXML
	GridPane bebidasFavoritas;
	@FXML
	HBox hBox;

	private ObservableList<Drink> drinkTypesSelected = FXCollections.observableArrayList();
	private ObservableList<DrinkForSale> filteredDrinks = DrinkDAO.getDrinksInPubs();

	@FXML
	private void initialize() {
		drinkColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDrinkName()));
		barColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPub().toString()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<DrinkForSale, Double>("price"));
		publistTV.setItems(filteredDrinks);
		publistTV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			openBarInfo(newSelection);
		});
	}

	private void filterList() {
		for (Node node : bebidasFavoritas.getChildren()) {
			CheckBox checkBox = (CheckBox) node;
			if (checkBox.isSelected())
				drinkTypesSelected.add((Drink) checkBox.getUserData());
		}
		for (DrinkForSale drinkForSale : publistTV.getItems()) {
			for (Drink drink : drinkTypesSelected) {
				if (drinkForSale.getDrinkType() == drink)
					filteredDrinks.add(drinkForSale);
			}
		}
	}

	private void setFavoriteDrinks() {
		int columnIndex = 0, rowIndex = 0;
		for (Drink drink : DrinkDAO.getDrinkTypes()) {
			CheckBox newCheckBox = new CheckBox(drink.toString());
			newCheckBox.setOnAction(e -> filterList());
			newCheckBox.setUserData(drink);
			newCheckBox.setTextFill(Color.WHITE);
			System.out.println(drink + " foi adicionado");
			bebidasFavoritas.add(newCheckBox, columnIndex, rowIndex);
			if (columnIndex < 1) {
				columnIndex++;
			} else {
				columnIndex = 0;
				rowIndex++;
			}
		}
	}

	void openBarInfo(DrinkForSale drink) {
		System.out.println(drink.getPub().toString() + "E O PUB DA BEBIDA");
		PubDAO.setPubsOrdered(drink.getPub().getCoordinates().getX(), drink.getPub().getCoordinates().getY());
		ScreenContainer screen = new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml",
				new DefaultHeaderController(), new BarScreenController(drink.getPub()));

		ScreenManager.setScreen(screen);
	}
}
