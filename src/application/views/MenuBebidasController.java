package application.views;

import com.jfoenix.controls.JFXButton;

import application.model.DAO.PubListDAO;
import application.models.Drink;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class MenuBebidasController {

	@FXML
	JFXButton bebidasButton,logoButton,userButton;
	@FXML
	TableView<Drink> publistTV;
	@FXML
	TableColumn<Drink,Image> imageColumn;
	@FXML
	TableColumn<Drink,String> drinkColumn;
	@FXML
	TableColumn<Drink,String> BarColumn;
	@FXML
	TableColumn<Drink,Float> priceColumn;	
	
	@FXML
	private void initialize() {
		imageColumn.setCellValueFactory(new PropertyValueFactory<Drink, Image>("Image"));
		drinkColumn.setCellValueFactory(new PropertyValueFactory<Drink, String>("Name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Drink, Float>("Price"));
		publistTV.setItems(PubListDAO.getPubList().get(0).getDrinks());
	}
	
}
