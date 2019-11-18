package application.models;

import java.sql.Date;

import javafx.collections.ObservableList;

public class Admin extends User{

	public Admin(int id, String name, String username, String password, String email, Date birthday, ObservableList<Drink> favoriteDrinks) {
		super(id, name, username, password, email, birthday, favoriteDrinks, true);
	}

}
