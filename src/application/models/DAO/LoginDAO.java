package application.models.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.JDBC;
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

	public static ObservableList<User> getUserList() {
		Connection con = JDBC.getConnection();
		String sql = "SELECT * FROM User";
		try (Statement stat = con.createStatement(); ResultSet rs = stat.executeQuery(sql)) {
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				int cellphone = rs.getInt("cellphone");
				boolean isAdmin = rs.getBoolean("isAdmin");
				
				userList.add(new User(userId,username,password,name,birthday,email,cellphone,isAdmin));
				ObservableList<Drink> favDrinks = DrinkDAO.getFavDrinks(userList.get(userList.size()-1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public static void addUser(User user) {
		userList.add(user);
	}

	/*
	 * 0 - logou como user 1 - logou como admin 2 - wrong username 3 - wrong
	 * password
	 */
	public static LoginType connect(String username, String password) {
		for (User user : userList) {
			if (user.getUsername().equals(username)) {
				if (user.getPassword().equals(password)) {
					setLogedinUser(user);
					return LoginType.USER_LOGIN;
				} else {
					return LoginType.WRONG_PASSWORD;
				}
			}
		}
		for (Admin admin : adminList) {
			if (admin.getUsername().equals(username)) {
				if (admin.getPassword().equals(password)) {
					setLogedinUser(admin);
					return LoginType.ADMIN_LOGIN;
				} else {
					return LoginType.WRONG_PASSWORD;
				}
			}
		}
		return LoginType.WRONG_USERNAME;
	}
}