package application.model.DAO;

import java.sql.Date;

import application.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class loginDAO {
	private static ObservableList<User> userList = FXCollections.observableArrayList();

	public static ObservableList<User> getUserList(){
		return userList;
	}
	
	public static boolean connect(String username, String password) {
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	static {
		userList.add(new User(0, "Franco", "piriurna", "franco123", "francozalamena@gmail.com", new Date(10, 10, 10)));
		userList.add(new User(0, "Pedrp", "yuri", "pedro123", "pedro@gmail.com", new Date(11, 11, 11)));
	}

}