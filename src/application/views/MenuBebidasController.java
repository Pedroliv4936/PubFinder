package application.views;

import com.jfoenix.controls.JFXButton;

import application.model.DAO.PubListDAO;
import application.models.Drink;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class MenuBebidasController {

	@FXML
	JFXButton bebidasButton, logoButton, userButton;
	@FXML
	TableView<Drink> publistTV;
	@FXML
	TableColumn<Drink, Image> imageColumn;
	@FXML
	TableColumn<Drink, String> drinkColumn;
	@FXML
	TableColumn<Drink, String> barColumn;
	@FXML
	TableColumn<Drink, Double> priceColumn;

	@FXML
	private void initialize() {
		imageColumn.setCellValueFactory(new PropertyValueFactory<Drink, Image>("Image"));
		drinkColumn.setCellValueFactory(new PropertyValueFactory<Drink, String>("name"));
		barColumn.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getPub().getName()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Drink, Double>("Price"));
		for (int i = 0; i < PubListDAO.getPubList().size(); i++) {
			publistTV.getItems().addAll(PubListDAO.getPubList().get(i).getDrinks());
		}
	}

}
