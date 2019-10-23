package application.model.DAO;

import javafx.collections.FXCollections;
import application.models.Drink;
import application.models.Pub;
import javafx.collections.ObservableList;

public class PubListDAO {
	
private PubListDAO() {}
private static ObservableList<Pub> pubList= FXCollections.observableArrayList();

public static ObservableList<Pub> getPubList (){
	return pubList;
}

static {
	ObservableList<Drink> pedroDrinks = FXCollections.observableArrayList();
	pedroDrinks.add(new Drink(0,"Vodka",4.5,2.5,null));
	pubList.add(new Pub(1,"Bar do Pedro", 4.5, "Lar das laranjas", pedroDrinks, null));
	
}
}
