package application.models.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import application.JDBC;
import application.models.Drink;
import application.models.User;
import application.models.UserPrivilege;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginDAO {
	private static User logedinUser;

	public static User getLogedinUser() {
		return logedinUser;
	}

	public static void setLogedinUser(User user) {
		LoginDAO.logedinUser = user;
	}

	public static ObservableList<User> getAdminList() {
		ObservableList<User> adminList = FXCollections.observableArrayList();
		Connection con = JDBC.getConnection();
		String sql = "call getAllFromUserType(1)";
		try (Statement stat = con.createStatement(); ResultSet rs = stat.executeQuery(sql)) {
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				int cellphone = rs.getInt("phone");
				int privilegeId = rs.getInt("user_privilege_id");
				UserPrivilege privilege = UserPrivilege.getPrivilege(privilegeId);

				adminList.add(new User(userId, name, username, password, email, birthday, cellphone, privilege));
				ObservableList<Drink> favDrinks = DrinkDAO.getFavDrinks(adminList.get(adminList.size() - 1));
				adminList.get(adminList.size() - 1).setFavoriteDrinks(favDrinks);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminList;
	}

	public static ObservableList<User> getUserList() {
		ObservableList<User> userList = FXCollections.observableArrayList();
		Connection con = JDBC.getConnection();
		String sql = "SELECT * FROM users";
		try (Statement stat = con.createStatement(); ResultSet rs = stat.executeQuery(sql)) {
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				int cellphone = rs.getInt("phone");
				int privilegeId = rs.getInt("user_privilege_id");
				UserPrivilege privilege = UserPrivilege.getPrivilege(1);

				userList.add(new User(userId, name, username, password, email, birthday, cellphone, privilege));
				ObservableList<Drink> favDrinks = DrinkDAO.getFavDrinks(userList.get(userList.size() - 1));
				userList.get(userList.size() - 1).setFavoriteDrinks(favDrinks);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public static void addUser(User user) {
		Connection con = JDBC.getConnection();
		String sql = "INSERT INTO users (username, password, name, birthday, email, phone, user_privilege_id) "
				+ "VALUES(?,MD5(?),?,?,?,?,?)";
		try (PreparedStatement stat = con.prepareStatement(sql)) {
			stat.setString(1, user.getUsername());
			stat.setString(2,user.getPassword());
			stat.setString(3,user.getName());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String format = formatter.format(user.getBirthday());
			stat.setString(4, format);
			stat.setString(5, user.getEmail());;
			stat.setInt(6, user.getCellphone());
			stat.setInt(7, 2);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 0 - logou como user 1 - logou como admin 2 - wrong username 3 - wrong
	 * password
	 */
	public static User connect(String username, String password) {
		User user = null;
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM users WHERE username = ? AND password = MD5(?)";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, username);
			stat.setString(2, password);
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					int userId = rs.getInt("user_id");
					String user_name = rs.getString("username");
					String pass_word = rs.getString("password");
					String name = rs.getString("name");
					Date birthday = rs.getDate("birthday");
					String email = rs.getString("email");
					int cellphone = rs.getInt("phone");
					int privilegeId = rs.getInt("user_privilege_id");
					System.out.println(privilegeId);
					UserPrivilege privilege = UserPrivilege.getPrivilege(privilegeId - 1);
					user = new User(userId, name, user_name, pass_word, email, birthday, cellphone, privilege);
					setLogedinUser(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}