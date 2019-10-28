package application.controllers;

import application.models.Drink;
import application.models.DrinkForPub;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

public class CheckNewRequestsController {

	@FXML
	private ListView<Pub> pubLV;

	@FXML
	private ListView<DrinkForPub> drinkLV;

	@FXML
	private Tab drinkTab, pubTab;

	@FXML
	private void initialize() {
		pubLV.setCellFactory(param -> new ListCell<Pub>() {
			@Override
			protected void updateItem(Pub item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null || item.toString() == null) {
					setText(null);
				} else {
					setText(item.toString());
				}
			}
		});
		
		drinkLV.setCellFactory(param -> new ListCell<DrinkForPub>() {
			@Override
			protected void updateItem(DrinkForPub item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null || item.toString() == null) {
					setText(null);
				} else {
					setText(item.toString());
				}
			}
		});
		pubLV.setItems(PubDAO.getPendingPubs());
		drinkLV.setItems(DrinkDAO.getPendingDrinkList());
	}

	@FXML
	private void accept() {
		if (pubTab.isSelected()) {
			PubDAO.aprovePubs(pubLV.getSelectionModel().getSelectedItems());
			pubLV.setItems(PubDAO.getPendingPubs());
		} else
			DrinkDAO.aproveDrinks(drinkLV.getSelectionModel().getSelectedItems());
			pubLV.setItems(PubDAO.getPendingPubs());
	}
}
