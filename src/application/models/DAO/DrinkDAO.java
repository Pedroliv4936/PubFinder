package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.JDBC;
import application.models.Drink;
import application.models.DrinkForSale;
import application.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DrinkDAO {
	private static ObservableList<Drink> drinkList = FXCollections.observableArrayList();

	public static ObservableList<DrinkForSale> getDrinksInPubs() {
		 ObservableList<DrinkForSale> drinksInPubs = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM drinks_for_sale WHERE pending = 0";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int drink = rs.getInt("drink_id");
				int pub = rs.getInt("pub_id");
				double rating = rs.getDouble("rating");
				double price = rs.getDouble("price");
				boolean pending = rs.getBoolean("pending");
				drinksInPubs.add(new DrinkForSale(DrinkDAO.drinkList.get(drink), PubDAO.getActivePubs().get(pub), rating, price, pending));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return drinksInPubs;
	}
	
	public static ObservableList<DrinkForSale> getPendingDrinks() {
		 ObservableList<DrinkForSale> pendingDrinks = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM drinks_for_sale WHERE pending = 1";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int drink = rs.getInt("drink_id");
				int pub = rs.getInt("pub_id");
				double rating = rs.getDouble("rating");
				double price = rs.getDouble("price");
				boolean pending = rs.getBoolean("pending");
				pendingDrinks.add(new DrinkForSale(DrinkDAO.drinkList.get(drink), PubDAO.getActivePubs().get(pub), rating, price, pending));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pendingDrinks;
	}
	
	
	public static ObservableList<Drink> getFavDrinks(User user) {
		ObservableList<Drink> favDrinkList = FXCollections.observableArrayList();
		Connection con = JDBC.getConnection();
		String sql = "SELECT drink_id FROM User_Favorite_Drink WHERE user_id is ?";
		try (PreparedStatement stat = con.prepareStatement(sql)) {
			stat.setInt(1, user.getId()+1);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("drink_id");
				String nome=rs.getString("drink_name");
				favDrinkList.add(new Drink (id,nome,null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
	}
		return favDrinkList;
	}
	
	public static void addDrinkFromPub(DrinkForSale drink) {
		Connection conn = JDBC.getConnection();
		int drink_id = drinkList.indexOf(drink.getDrinkType()) + 1;
		int pub = PubDAO.getActivePubs().indexOf(drink.getPub()) + 1;
		double rating = drink.getRating();
		double price = drink.getPrice();
		boolean pending = drink.isPending();
		String sql = "INSERT INTO "+"drinks_for_sale "+"(pub_id, drink_id, price, rating, pending) "+
					 "VALUES "+"("+pub + ", " + drink_id + ", " + price + ", " + rating + ", " + pending + ")";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void setDrinkList(ObservableList<Drink> drinkList) {
		DrinkDAO.drinkList = drinkList;
	}
	
	public static ObservableList<Drink> getDrinkTypes(){
		ObservableList<Drink> drinkTypes = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM drinks";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt("drink_id");
				String name = rs.getString("drink_name");
				drinkTypes.add(new Drink(id, name, null));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return drinkTypes;
	}
	
	public static void aproveDrinks(DrinkForSale drink) {
		drink.aprove();
		Connection conn = JDBC.getConnection();
		String sql = "UPDATE drink_for_sale "+
				 	 "SET pending = 0 "+
				 	 "WHERE pub_name = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, drink.toString());
			ResultSet rs = stat.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
