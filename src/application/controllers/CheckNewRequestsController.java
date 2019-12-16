package application.controllers;

import application.ScreenContainer;
import application.ScreenManager;
import application.models.DrinkForSale;
import application.models.Pub;
import application.models.DAO.DrinkDAO;
import application.models.DAO.PubDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;

/**
 * Apresenta ao administrador todos os bares e bebidas à espera de aprovacao.
 * 
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public class CheckNewRequestsController {

	@FXML
	private ListView<Pub> pubLV;

	@FXML
	private ListView<DrinkForSale> drinkLV;

	@FXML
	private Tab drinkTab, pubTab;
	/**
	 * Preenche a ListView pubLV com os pubs que estao pending (espera de aprovacao de admin) e a ListView  drinkLV com as bebidas que estao pending. 
	 */
	@FXML
	private void initialize() {
		pubLV.setCellFactory(lv -> new ListCell<Pub>() {
			@Override
			protected void updateItem(Pub pub, boolean empty) {
				super.updateItem(pub, empty);
				setText(pub == null ? null : pub.getPubInfo());
			}
		});
		drinkLV.setItems(DrinkDAO.getPendingDrinks());
		pubLV.setItems(PubDAO.getPendingPubs());
		pubLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		drinkLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	/**
	 * Muda o ecrã de forma a ver mais informacao sobre a bebida selecionada.
	 */
	@FXML
	public void openInfo() {
		ScreenManager.setScreen(new ScreenContainer("views/DefaultHeader.fxml", "views/DrinkRequestInfo.fxml",
				new DefaultHeaderController(),
				new DrinkRequestInfoController(drinkLV.getSelectionModel().getSelectedItem(), "jfsldkfjslkdfjls")));
		System.out.println("Vendo informacoes da bebida");
	}
	/**
	 * Vê em que tab o utilizador se encontra e corre o respetivo método para aprovar cada entidade.
	 * 
	 * @see #aproveDrink()
	 * @see #aprovePub()
	 */
	@FXML
	private void accept() {
		if (drinkTab.isSelected()) {
			aproveDrink();
		} else if (pubTab.isSelected()) {
			aprovePub();
		}
		ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN);
	}

//	private DrinkForSale existingDrink(DrinkForSale drink) {
//		for (DrinkForSale existingDrink : DrinkDAO.getDrinksInPubs()) {
//			if (drink.getDrinkName().equals(existingDrink.getDrinkName())) {
//				if (drink.getPub() == existingDrink.getPub()) {
//					System.out.println("Bebida ja existente");
//					return existingDrink;
//				}
//			}
//		}
//		return null;
//	}
//
//	private void overrideDrink(DrinkForSale existingDrink) {
//		System.out.println("Dando override...");
//		DrinkDAO.removeDrink(existingDrink);
//		aproveDrink();
//
//	}
//
//	private Pub existingPub(Pub pub) {
//		for (Pub existingPub : PubDAO.getActivePubs()) {
//			if (pub.getCoordinates() == existingPub.getCoordinates()) {
//				return pub;
//			}
//		}
//		return null;
//	}
//
//	private void overridePub(Pub existingPub) {
//		System.out.println("Dando override...");
//		PubDAO.removePub(existingPub);
//		aprovePub();
//
//	}
	/**
	 * Vê a Tab que o utilizador tem selecionado e corre o respetivo método para recusar o item dessa Tab.
	 * 
	 * @see #refuseDrink()
	 * @see #refusePub()
	 */
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
	/**
	 * Retira a bebida recusada da lista na classe DrinkDAO e da ListView.
	 * 
	 * @see application.models.DAO.DrinkDAO#refuseDrink(DrinkForSale)
	 */
	private void refuseDrink() {
		DrinkDAO.refuseDrink(drinkLV.getSelectionModel().getSelectedItem());
		System.out.println("DRINK(s) REPROVADO(s):");
		for (DrinkForSale drink : drinkLV.getSelectionModel().getSelectedItems()) {
			drink.showDrinkInfo();
		}
	}
	/**
	 * Retira o bar recusado da lista de bares no PubDAO e da ListView pubLV.
	 * 
	 * @see application.models.DAO.PubDAO#refusePub(Pub)
	 */
	private void refusePub() {
		PubDAO.refusePub(pubLV.getSelectionModel().getSelectedItem());
		System.out.println("PUB(s) REPROVADO(s):");
		for (Pub pub : pubLV.getSelectionModel().getSelectedItems()) {
			pub.showPubInfo();
		}
	}
	/**
	 * A bebida é aprovada e será visivel na lista de bebidas e na informacao do respetivo bar onde é vendida.
	 * 
	 * @see application.models.DAO.DrinkDAO#aproveDrink(DrinkForSale)
	 */
	private void aproveDrink() {
		System.out.println(drinkLV.getSelectionModel().getSelectedItem().toString() + " Aprovado. ID: "
				+ drinkLV.getSelectionModel().getSelectedItem().getId());
		DrinkDAO.aproveDrink(drinkLV.getSelectionModel().getSelectedItem());
	}
	/**
	 * O pub é aprovado e passará a ser possivel adicionar bebidas a esse bar.
	 * 
	 * @see application.models.DAO.PubDAO#aprovePub(Pub)
	 */
	private void aprovePub() {
		PubDAO.aprovePub(pubLV.getSelectionModel().getSelectedItem());
	}

	/*
	 * // Cria variavel com o drink com mesmo tipo e pub DrinkForSale existingDrink
	 * = existingDrink(drinkLV.getSelectionModel().getSelectedItem()); // Se nao for
	 * nulo quer dizer que existe um drink igual if (existingDrink != null) { //
	 * Cria janela de popUp informando do conflito String popUpMessage =
	 * "Bebida ja existente. Deseja sobrepor os dados existentes?";
	 * PopUpWindowController controller = new PopUpWindowController(popUpMessage);
	 * ScreenManager.createPopupWindow(controller); // Se for permitido o override,
	 * o drink antigo e` deletado e o novo aprovado if (controller.isAproved()) {
	 * overrideDrink(existingDrink);
	 * ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN); } // Se nao existir,
	 * apenas aprovar o drink } else if (existingDrink == null) {
	 */

	/*
	 * Pub existingPub = existingPub(pubLV.getSelectionModel().getSelectedItem());
	 * if (existingPub != null) { // Cria janela de popUp informando do conflito
	 * String popUpMessage =
	 * "Bar ja existente. Deseja sobrepor os dados existentes?";
	 * PopUpWindowController controller = new PopUpWindowController(popUpMessage);
	 * ScreenManager.createPopupWindow(controller); if (controller.isAproved()) {
	 * overridePub(existingPub);
	 * ScreenManager.setScreen(ScreenContainer.MAIN_SCREEN); } } else if
	 * (existingPub == null) {
	 */
}