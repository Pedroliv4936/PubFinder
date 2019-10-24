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
	Pub barDoPedro = new Pub(1,"Bar do Pedro", 0, "Lar das laranjas", pedroDrinks, null);
	Pub barDoFranco = new Pub(69, "Bar do Franco", 0, "Casa do Franco", francoDrinks, null);
	
	pedroDrinks.addAll(new Drink(0, "Vodka", Math.random()*6-1, Math.random()*10, null, barDoPedro),
						new Drink(1, "Gin", Math.random()*6-1, Math.random()*10, null, barDoPedro),
						new Drink(2, "Copo de Cerveja", Math.random()*6-1, Math.random()*10, null, barDoPedro),
						new Drink(3, "Caneca de Cerveja", Math.random()*6-1, Math.random()*10, null, barDoPedro),
						new Drink(4, "Rum", Math.random()*6-1, Math.random()*10, null, barDoPedro),
						new Drink(4, "Sidra", Math.random()*6-1, Math.random()*10, null, barDoPedro));
	
	francoDrinks.addAll(new Drink(0, "Vodka", Math.random()*6-1, Math.random()*10, null, barDoFranco),
			new Drink(1, "Gin", Math.random()*6-1, Math.random()*10, null, barDoFranco),
			new Drink(2, "Copo de Cerveja", Math.random()*6-1, Math.random()*10, null, barDoFranco),
			new Drink(3, "Caneca de Cerveja", Math.random()*6-1, Math.random()*10, null, barDoFranco),
			new Drink(4, "Rum", Math.random()*6-1, Math.random()*10, null, barDoFranco),
			new Drink(4, "Sidra", Math.random()*6-1, Math.random()*10, null, barDoFranco));

	
	pubList.addAll(barDoPedro, barDoFranco);
}
}
