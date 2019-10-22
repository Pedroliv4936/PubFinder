package application.models;

import java.sql.Date;

public class Admin extends User{

	public Admin(int id, String name, String username, String password, String email, Date birthday) {
		super(id, name, username, password, email, birthday); 
	}

}
