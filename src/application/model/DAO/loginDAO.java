package application.model.DAO;

import java.sql.Date;

import application.models.Admin;
import application.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class loginDAO {
	private static ObservableList<User> userList = FXCollections.observableArrayList();
	
	private static ObservableList<Admin> adminList = FXCollections.observableArrayList();
	
	public static ObservableList<Admin> getAdminList() {
		return adminList;
	}

	public static ObservableList<User> getUserList(){
		return userList;
	}
	
	
	/*
	0 - logou como user
	1 - logou como admin
	2 - wrong username
	3 - wrong password
	 */
	public static int connect(String username, String password) {
		int exception;
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password)) {
					return 0;
				}else {
					return 3;
				}
			}
		}
		for(Admin admin : adminList) {
			if(admin.getUsername().equals(username)) {
				if(admin.getPassword().equals(password)) {
					return 1;
				}else {
					return 3;
				}
			}
		}
		return 2;
	}

	static {
		userList.add(new User(0, "Franco", "piriurna", "franco123", "francozalamena@gmail.com", new Date(10, 10, 10)));
		userList.add(new User(0, "Pedrp", "yuri", "pedro123", "pedro@gmail.com", new Date(11, 11, 11)));
	}

}