package application.controllers;

import application.ScreenManager;
import application.models.DrinkForPub;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.fxml.FXML;
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
		pubLV.setItems(PubDAO.getPendingPubs());
		drinkLV.setItems(DrinkDAO.getPendingDrinkList());
	}

	@FXML
	private void accept() {
		System.out.println();
		System.out.println();
		if (pubTab.isSelected()) {
			PubDAO.aprovePubs(pubLV.getSelectionModel().getSelectedItems());
			System.out.println("PUB(s) APROVADO(s) COM ATRIBUTOS:");
			for (Pub pub : pubLV.getSelectionModel().getSelectedItems()) {
				pub.showPubInfo();
			}

		} else {
			DrinkDAO.aproveDrinks(drinkLV.getSelectionModel().getSelectedItems());
			System.out.println("DRINK(s) APROVADO(s) COM ATRIBUTOS:");
			for (DrinkForPub drink : drinkLV.getSelectionModel().getSelectedItems()) {
				drink.showDrinkInfo();
			}
		}
		ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}
}
