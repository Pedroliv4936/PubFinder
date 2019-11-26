package application.models;

import java.sql.Date;

import javafx.collections.ObservableList;

public class Admin extends User{

	public Admin(int id,  String username, String password, String name, Date birthday, String email, int cellphone) {
		super(id, username, password, name, birthday, email, cellphone, true);
	}

}
