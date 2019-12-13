package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.JDBC;
import application.controllers.MenuBebidasController;
import application.models.Coordinates;
import application.models.Drink;
import application.models.DrinkForSale;
import application.models.Pub;
import application.models.PubType;
import application.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DrinkDAO {
	public static ObservableList<DrinkForSale> getDrinksInPubs() {
		ObservableList<DrinkForSale> lista = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		
		String sql = "SELECT * FROM drinks_for_sale \r\n" + 
				"INNER JOIN drinks \r\n" + 
				"ON drinks_for_sale.drink_id = drinks.drink_id \r\n" + 
				"INNER JOIN pubs\r\n" + 
				"ON drinks_for_sale.pub_id = pubs.pub_id \r\n" + 
				"INNER JOIN pub_types \r\n"+
				"ON pubs.pub_type_id = pub_types.pub_type_id ";
				
		try (Statement stat = conn.createStatement()){
			try(ResultSet rs = stat.executeQuery(sql)){
				while(rs.next()) {
					System.out.println(rs.getRow());
					int drinkId = rs.getInt("drink_id");
					String drinkName = rs.getString("drink_name");
					Drink drink = new Drink(drinkId, drinkName, null);
					int pubId = rs.getInt("pub_id");
					String pubName = rs.getString("pub_name");
					String pubTypeName = rs.getString("pub_type_name");
					int pubTypeId = rs.getInt("pub_type_id");
					PubType pubType = new PubType(pubTypeId, pubTypeName);
					double entryPrice = rs.getDouble("entry_price");
					String address = rs.getString("address");
					double xCoord = rs.getDouble("xCoord");
					double yCoord = rs.getDouble("yCoord");
					Coordinates latLong = new Coordinates(xCoord,yCoord);
					Pub pub = new Pub(pubId, pubName, pubType, xCoord, yCoord, address, latLong, false);
					double rating = rs.getDouble("rating");
					double price = rs.getDouble("price");
					boolean pending = false;
					lista.add(new DrinkForSale(drink, pub , rating, price, pending));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();}
		return lista;
		}

	public static ObservableList<DrinkForSale> getDrinks(Pub pub) {
		ObservableList<DrinkForSale> drinks = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM drinks_for_sale WHERE pub_id = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, pub.getId());
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					int drink = rs.getInt("drink_id");
					double rating = rs.getDouble("rating");
					double price = rs.getDouble("price");
					boolean pending = rs.getBoolean("pending");
					drinks.add(new DrinkForSale(DrinkDAO.getDrinkType(drink), pub, rating, price, pending));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drinks;
	}

	public static ObservableList<DrinkForSale> getPendingDrinks() {
		ObservableList<DrinkForSale> pendingDrinks = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM drinks_for_sale WHERE pending = 1";
		try (Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)) {
			while (rs.next()) {
				int drinkId = rs.getInt("drink_sale_id");
				int drink = rs.getInt("drink_id");
				int pub = rs.getInt("pub_id");
				double rating = rs.getDouble("rating");
				double price = rs.getDouble("price");
				boolean pending = rs.getBoolean("pending");
				pendingDrinks.add(
						new DrinkForSale(drinkId, getDrinkType(drink), PubDAO.getPub(pub), rating, price, pending));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pendingDrinks;
	}

	public static void removeDrink(DrinkForSale drink) {
		Connection conn = JDBC.getConnection();
		String sql = "DELETE FROM drinks_for_sale WHERE drink_sale_id = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, drink.getId());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<Drink> getFavDrinks(User user) {
		ObservableList<Drink> favDrinkList = FXCollections.observableArrayList();
		Connection con = JDBC.getConnection();
		String sql = "SELECT * FROM user_favorite_drinks WHERE user_id = ?";
		try (PreparedStatement stat = con.prepareStatement(sql)) {
			stat.setInt(1, user.getId());
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("drink_id");
					String nome = rs.getString("drink_name");
					favDrinkList.add(new Drink(id, nome, null));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return favDrinkList;
	}

	public static void addFavDrink(User user, Drink drink) {
		Connection con = JDBC.getConnection();
		String sql = "INSERT INTO user_favorite_drinks (user_id, drink_id) VALUES(?, ?)";
		try (PreparedStatement stat = con.prepareStatement(sql)) {
			stat.setInt(1, user.getId());
			stat.setInt(2, drink.getId());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addDrink(DrinkForSale drink) {
		Connection conn = JDBC.getConnection();
		int drink_id = drink.getDrinkType().getId();
		int pub = drink.getPub().getId();
		double rating = drink.getRating();
		double price = drink.getPrice();
		boolean pending = drink.isPending();
		String sql = "INSERT INTO " + "drinks_for_sale " + "(pub_id, drink_id, price, rating, pending) " + "VALUES "
				+ "(?,?,?,?,?)";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, pub);
			stat.setInt(2, drink_id);
			stat.setDouble(3, price);
			stat.setDouble(4, rating);
			stat.setBoolean(5, pending);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<DrinkForSale> getDrinksFiltered(ObservableList<Integer> ids) {
		ObservableList<DrinkForSale> lista = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String iMarks = "?";
		for(int i = 1; i < ids.size(); i++) {
			iMarks += ",?";
		}
		System.out.println(iMarks);
		
		String sql = "SELECT * FROM drinks_for_sale \r\n" + 
				"INNER JOIN drinks \r\n" + 
				"ON drinks_for_sale.drink_id = drinks.drink_id \r\n" + 
				"INNER JOIN pubs\r\n" + 
				"ON drinks_for_sale.pub_id = pubs.pub_id \r\n" + 
				"INNER JOIN pub_types \r\n"+
				"ON pubs.pub_type_id = pub_types.pub_type_id \r\n" + 
				"WHERE drinks_for_sale.drink_id \r\n" + 
				"IN ("+ iMarks + ") AND drinks_for_sale.pending = 0 ";
				
		try (PreparedStatement stat = conn.prepareStatement(sql);){
			for(int i=0;i<ids.size();i++) {
				stat.setInt(i + 1, ids.get(i));
			}
			try(ResultSet rs = stat.executeQuery()){
				while(rs.next()) {
					System.out.println(rs.getRow());
					int drinkId = rs.getInt("drink_id");
					String drinkName = rs.getString("drink_name");
					Drink drink = new Drink(drinkId, drinkName, null);
					int pubId = rs.getInt("pub_id");
					String pubName = rs.getString("pub_name");
					String pubTypeName = rs.getString("pub_type_name");
					int pubTypeId = rs.getInt("pub_type_id");
					PubType pubType = new PubType(pubTypeId, pubTypeName);
					double entryPrice = rs.getDouble("entry_price");
					String address = rs.getString("address");
					double xCoord = rs.getDouble("xCoord");
					double yCoord = rs.getDouble("yCoord");
					Coordinates latLong = new Coordinates(xCoord,yCoord);
					Pub pub = new Pub(pubId, pubName, pubType, xCoord, yCoord, address, latLong, false);
					double rating = rs.getDouble("rating");
					double price = rs.getDouble("price");
					boolean pending = false;
					lista.add(new DrinkForSale(drink, pub , rating, price, pending));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public static ObservableList<Drink> getDrinkTypes() {
		ObservableList<Drink> drinkTypes = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM drinks";
		try (Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)) {
			while (rs.next()) {
				int id = rs.getInt("drink_id");
				String name = rs.getString("drink_name");
				drinkTypes.add(new Drink(id, name, null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drinkTypes;
	}

	public static Drink getDrinkType(int id) {
		ObservableList<Drink> drinks = getDrinkTypes();
		for (Drink drink : drinks) {
			if (drink.getId() == id)
				return drink;
		}
		return null;
	}

	public static void aproveDrink(DrinkForSale drink) {
		Connection conn = JDBC.getConnection();
		String sql = "UPDATE drinks_for_sale SET pending = 0 WHERE drink_sale_id = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, drink.getId());
			stat.executeUpdate();
			System.out.println("APROVADO DRINK COM ID: " + drink.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void refuseDrink(DrinkForSale drink) {
		removeDrink(drink);
	}
}
