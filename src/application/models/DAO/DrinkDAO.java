package application.models.DAO;

import application.models.DrinkForPub;
import application.models.Pub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DrinkDAO {

	private static ObservableList<DrinkForPub> drinkList = FXCollections.observableArrayList();

	public static ObservableList<DrinkForPub> getDrinkList() {
		return drinkList;
	}
	
	static {
		for(Pub pub:PubDAO.getPubList()) {
			drinkList.addAll(pub.getDrinks());
		}
	}
}
