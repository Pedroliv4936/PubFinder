package application.models.DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.google.gson.Gson;

import application.models.Drink;
import application.models.DrinkForPub;
import application.models.Pub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PubDAO {

	private PubDAO() {
	}

	private static ObservableList<Pub> pubList = FXCollections.observableArrayList();
	private static ObservableList<Pub> pendingPubList = FXCollections.observableArrayList();

	public static ObservableList<Pub> getPubList() {
		return pubList;
	}
	
	public static ObservableList<Pub> getPendingPubs() {
		return pendingPubList;
	}
	
	public static void addPub(Pub pub) {
		pubList.add(pub);
	}
	
	public static void removePub(Pub pub) {
		pubList.remove(pub);
	}
	
	public static void addPendingPub(Pub pub) {
		pendingPubList.add(pub);
	}
	
	public static void aprovePub(Pub pub) {
		pubList.add(pub);
		pendingPubList.remove(pub);
	}
	
	public static void aprovePubs(ObservableList<Pub> pubs) {
		pubList.addAll(pubs);
		pendingPubList.removeAll(pubs);
	}

	public void loadDatabase() throws FileNotFoundException, IOException {

		Properties p = new Properties();
		p.load(new FileReader("pubs.list"));
	}

	static {
		ObservableList<DrinkForPub> pedroDrinks = FXCollections.observableArrayList();
		ObservableList<DrinkForPub> francoDrinks = FXCollections.observableArrayList();
		Pub barDoPedro = new Pub(1, "Bar do Pedro", Pub.DISCOTECA, 10, 0, "Lar das laranjas", pedroDrinks, null);
		Pub barDoFranco = new Pub(69, "Bar do Franco", Pub.BAR, 0, 0, "Casa do Franco", francoDrinks, null);

		pedroDrinks.addAll(new DrinkForPub(Drink.VODKA, barDoPedro, Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.COPO_CERVEJA, barDoPedro, Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.CANECA_CERVEJA, barDoPedro, Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.GIN, barDoPedro, Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.SIDRA, barDoPedro, Math.random() * 5, Math.random() * 10));

		francoDrinks.addAll(new DrinkForPub(Drink.VODKA, barDoFranco, Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.COPO_CERVEJA, barDoFranco, Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.CANECA_CERVEJA, barDoFranco, Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.GIN, barDoFranco, Math.random() * 5, Math.random() * 10),
				new DrinkForPub(Drink.SIDRA, barDoFranco, Math.random() * 5, Math.random() * 10));

		pubList.addAll(barDoPedro, barDoFranco);
	}
}
