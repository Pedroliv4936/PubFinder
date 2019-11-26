package application.controllers;

import application.ScreenManager;
import application.models.DrinkForSale;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import application.views.ScreenContainer;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;

public class CheckNewRequestsController {

	@FXML
	private ListView<Pub> pubLV;

	@FXML
	private ListView<DrinkForSale> drinkLV;

	@FXML
	private Tab drinkTab, pubTab;

	@FXML
	private void initialize() {
		pubLV.setCellFactory(lv -> new ListCell<Pub>() {
			@Override
			protected void updateItem(Pub pub, boolean empty) {
				super.updateItem(pub, empty);
				setText(pub == null ? null : pub.getPubInfo());
			}
		});
		for (Pub pub : PubDAO.getPubList()) {
			if (!pub.isPending())
				pubLV.getItems().add(pub);
		}
		drinkLV.setItems(DrinkDAO.getPendingDrinkList());
		pubLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		drinkLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	public void openInfo() {
		ScreenManager.setScreen(new ScreenContainer("views/DefaultHeader.fxml", "views/DrinkRequestInfo.fxml",
				new DefaultHeaderController(),
				new DrinkRequestInfoController(drinkLV.getSelectionModel().getSelectedItem(), "jfsldkfjslkdfjls")));
		System.out.println("Vendo informacoes da bebida");
	}

	@FXML
	private void accept() {

		// Checa se esta na tab dos drinks
		if (drinkTab.isSelected()) {

			// Cria variavel com o drink com mesmo tipo e pub
			DrinkForSale existingDrink = existingDrink(drinkLV.getSelectionModel().getSelectedItem());

			// Se nao for nulo quer dizer que existe um drink igual
			if (existingDrink != null) {

				// Cria janela de popUp informando do conflito
				String popUpMessage = "Bebida ja existente. Deseja sobrepor os dados existentes?";
				PopUpWindowController controller = new PopUpWindowController(popUpMessage);
				ScreenManager.createPopupWindow(controller);

				// Se for permitido o override, o drink antigo e` deletado e o novo aprovado
				if (controller.isAproved()) {

					overrideDrink(existingDrink);
					ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
				}

				// Se nao existir, apenas aprovar o drink
			} else if (existingDrink == null) {
				aproveDrink();
			}
		} else if (pubTab.isSelected()) {

			Pub existingPub = existingPub(pubLV.getSelectionModel().getSelectedItem());

			if (existingPub != null) {

				// Cria janela de popUp informando do conflito
				String popUpMessage = "Bar ja existente. Deseja sobrepor os dados existentes?";
				PopUpWindowController controller = new PopUpWindowController(popUpMessage);
				ScreenManager.createPopupWindow(controller);

				if (controller.isAproved()) {

					overridePub(existingPub);
					ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
				}
			} else if (existingPub == null) {
				aprovePub();
			}
		}
	}

	private DrinkForSale existingDrink(DrinkForSale drink) {
		for (DrinkForSale existingDrink : DrinkDAO.getDrinksInPubs()) {
			if (drink.getDrinkName().equals(existingDrink.getDrinkName())) {
				if (drink.getPub() == existingDrink.getPub()) {
					System.out.println("Bebida ja existente");
					return existingDrink;
				}
			}
		}
		return null;
	}

	private void overrideDrink(DrinkForSale existingDrink) {
		System.out.println("Dando override...");
		DrinkDAO.removeDrinkFromPub(existingDrink);
		aproveDrink();

	}

	private Pub existingPub(Pub pub) {
		for (Pub existingPub : PubDAO.getPubList()) {
			if (pub.getCoordinates() == existingPub.getCoordinates()) {
				return pub;
			}
		}
		return null;
	}

	private void overridePub(Pub existingPub) {
		System.out.println("Dando override...");
		aprovePub();

	}

	@FXML
	private void refuse() {
		System.out.println();
		System.out.println();

		if (pubTab.isSelected()) {
			refusePub();
		} else {
			refuseDrink();
		}
		ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}

	private void refuseDrink() {

		DrinkDAO.refuseDrinks(drinkLV.getSelectionModel().getSelectedItems());
		System.out.println("DRINK(s) REPROVADO(s):");
		for (DrinkForSale drink : drinkLV.getSelectionModel().getSelectedItems()) {
			drink.showDrinkInfo();
		}
	}

	private void refusePub() {
		PubDAO.refusePubs(pubLV.getSelectionModel().getSelectedItems());
		System.out.println("PUB(s) REPROVADO(s):");
		for (Pub pub : pubLV.getSelectionModel().getSelectedItems()) {
			pub.showPubInfo();
		}
	}

	private void aproveDrink() {
		System.out.println(drinkLV.getSelectionModel().getSelectedItem().toString());
		DrinkDAO.aproveDrinks(drinkLV.getSelectionModel().getSelectedItem());
		System.out.println("DRINK(s) APROVADO(s):");
		for (DrinkForSale drink : drinkLV.getSelectionModel().getSelectedItems()) {
			drink.showDrinkInfo();
		}
	}

	private void aprovePub() {
		PubDAO.aprovePub(pubLV.getSelectionModel().getSelectedItem());
	}
}