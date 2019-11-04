package application.models.DAO;

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
	
	public static Pub getPubById(int id) {
		for(Pub pub: pubList) {
			if(id == pub.getId()) {
				return pub;
			}
		}
		return null;
	}
	
	public static Pub getPubByName(String name) {
		for(Pub pub: pubList) {
			if(name.equals(pub.toString())) {
				return pub;
			}
		}
		return null;
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

	static {		
		Pub barDoPedro = new Pub(1, "Bar do Pedro", Pub.DISCOTECA, 10, 0, "Lar das laranjas", 38.728601, -9.136331, null);
		Pub barDoFranco = new Pub(69, "Bar do Franco", Pub.BAR, 0, 0, "Casa do Franco", 38.707567, -9.152537, null);

		pubList.addAll(barDoPedro, barDoFranco);
	}
}
