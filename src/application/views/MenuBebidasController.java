package application.views;

import com.jfoenix.controls.JFXButton;

import application.model.DAO.PubListDAO;
import application.models.Drink;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuBebidasController {

	@FXML
	JFXButton bebidasButton, logoButton, userButton;
	@FXML
	TableView<Drink> publistTV;
	@FXML
	TableColumn<Drink, String> drinkColumn;
	@FXML
	TableColumn<Drink, String> barColumn;
	@FXML
	TableColumn<Drink, Double> priceColumn;
	@FXML
	TableColumn<Drink, Double> ratingColumn;

	@FXML
	private void initialize() {
		drinkColumn.setCellValueFactory(new PropertyValueFactory<Drink, String>("name"));
		barColumn.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getPub().getName()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Drink, Double>("Price"));
		ratingColumn.setCellValueFactory(cellData ->   new ReadOnlyObjectWrapper<>(cellData.getValue().getRating()));
		for (int i = 0; i < PubListDAO.getPubList().size(); i++) {
			publistTV.getItems().addAll(PubListDAO.getPubList().get(i).getDrinks());
		}
	}

}
