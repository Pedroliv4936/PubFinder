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
 * Apresenta ao administrador todos os bares e bebidas e  espera de aprovacao.
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
	 * Muda o ecra de forma a ver mais informacao sobre a bebida selecionada.
	 */
	@FXML
	public void openInfo() {
		ScreenManager.setScreen(new ScreenContainer("views/DefaultHeader.fxml", "views/DrinkRequestInfo.fxml",
				new DefaultHeaderController(),
				new DrinkRequestInfoController(drinkLV.getSelectionModel().getSelectedItem(), "jfsldkfjslkdfjls")));
		System.out.println("Vendo informacoes da bebida");
	}
	/**
	 * Ve em que tab o utilizador se encontra e corre o respetivo metodo para aprovar cada entidade.
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
	
	/**
	 * Ve a Tab que o utilizador tem selecionado e corre o respetivo metodo para recusar o item dessa Tab.
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
	 * A bebida e aprovada e sera visivel na lista de bebidas e na informacao do respetivo bar onde e vendida.
	 * 
	 * @see application.models.DAO.DrinkDAO#aproveDrink(DrinkForSale)
	 */
	private void aproveDrink() {
		System.out.println(drinkLV.getSelectionModel().getSelectedItem().toString() + " Aprovado. ID: "
				+ drinkLV.getSelectionModel().getSelectedItem().getId());
		DrinkDAO.aproveDrink(drinkLV.getSelectionModel().getSelectedItem());
	}
	/**
	 * O pub e aprovado e passara a ser possivel adicionar bebidas a esse bar.
	 * 
	 * @see application.models.DAO.PubDAO#aprovePub(Pub)
	 */
	private void aprovePub() {
		PubDAO.aprovePub(pubLV.getSelectionModel().getSelectedItem());
	}
}