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
	ObservableList<Drink> francoDrinks = FXCollections.observableArrayList();
	Pub barDoPedro = new Pub(1,"Bar do Pedro", 4.5, "Lar das laranjas", pedroDrinks, null);
	Pub barDoFranco = new Pub(69, "Bar do Franco", 5, "Casa do Franco", francoDrinks, null);
	pedroDrinks.add(new Drink(0,"Vodka",4.5,2.5,null, barDoPedro));
	francoDrinks.addAll(new Drink(0, "Vodka", 3 , 1.5, null, barDoFranco), new Drink(1, "Cerveja", 3.5, 0.5, null, barDoFranco));
	pubList.addAll(barDoPedro, barDoFranco);
}
}
