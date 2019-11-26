package application.controllers;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import application.ScreenManager;
import application.models.Drink;
import application.models.DrinkForSale;
import application.models.DAO.DrinkDAO;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.transformation.FilteredList;
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

	private ArrayList<CheckBox> allCheckBoxes = new ArrayList<CheckBox>();
	private FilteredList<DrinkForPub> filteredDrinks = new FilteredList<>(DrinkDAO.getDrinksInPubs());

	@FXML
	private void initialize() {
		drinkColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDrinkName()));
		barColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPub().toString()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<DrinkForSale, Double>("price"));
		publistTV.setItems(DrinkDAO.getDrinksInPubs());
		setFavoriteDrinks();
		publistTV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			openBarInfo(newSelection);
			filterDrinks();
		});

	}

	private void setFavoriteDrinks() {
		int columnIndex = 0, rowIndex = 0;
		for (Drink drink : LoginDAO.getLogedinUser().getFavoriteDrinks()) {
			CheckBox newCheckBox = new CheckBox(drink.toString());
			newCheckBox.setUserData(drink);
			newCheckBox.setTextFill(Color.WHITE);
			System.out.println(drink + " foi adicionado");
			bebidasFavoritas.add(newCheckBox, columnIndex, rowIndex);
			allCheckBoxes.add(newCheckBox);
			if (columnIndex < 1) {
				columnIndex++;
			} else {
				columnIndex = 0;
				rowIndex++;
			}
		}
	}

	private void selected() {

	}

	private void filterDrinks() {
		for (Node node: bebidasFavoritas.getChildren()) {
			if (node instanceof CheckBox) {
				CheckBox checkBox= (CheckBox) node;
				if(checkBox.isSelected()) {
					
				}
			}
		}
	}

 void openBarInfo(DrinkForSale drink) {
		ScreenContainer screen = new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml", new DefaultHeaderController(), new BarScreenController(drink.getPub()));

		ScreenManager.setScreen(screen);
	}
}
