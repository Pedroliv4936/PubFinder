package application.models.DAO;

import java.sql.Date;

import application.models.Admin;
import application.models.Drink;
import application.models.LoginType;
import application.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginDAO {
	private static ObservableList<User> userList = FXCollections.observableArrayList();
	
	private static ObservableList<Admin> adminList = FXCollections.observableArrayList();
	
	private static User logedinUser;
	
	public static User getLogedinUser() {
		return logedinUser;
	}

	public static void setLogedinUser(User logedinUser) {
		LoginDAO.logedinUser = logedinUser;
	}

	public static ObservableList<Admin> getAdminList() {
		return adminList;
	}

	public static ObservableList<User> getUserList(){
		return userList;
	}
	
	public static void addUser(User user) {
		userList.add(user);
	}
	
	
	/*
	0 - logou como user
	1 - logou como admin
	2 - wrong username
	3 - wrong password
	 */
	public static LoginType connect(String username, String password) {
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password)) {
					setLogedinUser(user);
					return LoginType.USER_LOGIN;
				}else {
					return LoginType.WRONG_PASSWORD;
				}
			}
		}
		for(Admin admin : adminList) {
			if(admin.getUsername().equals(username)) {
				if(admin.getPassword().equals(password)) {
					setLogedinUser(admin);
					return LoginType.ADMIN_LOGIN;
				}else {
					return LoginType.WRONG_PASSWORD;
				}
			}
		}
		return LoginType.WRONG_USERNAME;
	}

	static {
		ObservableList<Drink> francoDrinks = FXCollections.observableArrayList();
		francoDrinks.add(Drink.SIDRA);
		francoDrinks.add(Drink.GIN);
		francoDrinks.add(Drink.CANECA_CERVEJA);
		francoDrinks.add(Drink.COPO_CERVEJA);
		francoDrinks.add(Drink.VODKA);
		adminList.add(new Admin(0, "Franco", "piriurna", "franco123", "francozalamena@gmail.com", new Date(10, 10, 10), francoDrinks));
		userList.add(new User(0, "Pedro", "yuri", "rabanete", "pedro@gmail.com", new Date(11, 11, 11), francoDrinks));
	}

}