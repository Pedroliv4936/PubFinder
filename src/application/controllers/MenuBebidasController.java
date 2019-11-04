package application.controllers;

import com.jfoenix.controls.JFXButton;

import application.ScreenManager;
import application.models.Drink;
import application.models.DrinkForPub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.LoginDAO;
import application.views.ScreenContainer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
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
	GridPane bebidasFavoritas;
	@FXML
	HBox hBox;
	
	@FXML
	private void initialize() {		
		drinkColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDrinkType().toString()));
		barColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPub().toString()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<DrinkForPub, Double>("price"));
		ratingColumn.setCellValueFactory(new PropertyValueFactory<DrinkForPub, Double>("rating"));
		publistTV.setItems(DrinkDAO.getDrinksInPubs());
		setFavoriteDrinks();
		publistTV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        openBarInfo(newSelection);
	});
	}
	private void setFavoriteDrinks() {
		int columnIndex=0,rowIndex=0;
		for(Drink drink: LoginDAO.getLogedinUser().getFavoriteDrinkList().getDrinks()) {
			CheckBox newCheckBox= new CheckBox(drink.toString());
			newCheckBox.setUserData(drink);
			newCheckBox.setTextFill(Color.WHITE);
			System.out.println(drink+ " foi adicionado");
			bebidasFavoritas.add(newCheckBox, columnIndex,rowIndex);
			if(columnIndex<1) {
				columnIndex++;
			}else {
				columnIndex=0;
				rowIndex++;
			}
		}
	}
	
	private void openBarInfo(DrinkForPub drink) {
		ScreenContainer screen = new ScreenContainer("views/DefaultHeader.fxml", "views/BarScreen.fxml", new DefaultHeaderController(), new BarScreenController(drink.getPub()));
		ScreenManager.setScreen(screen);
	}
}
