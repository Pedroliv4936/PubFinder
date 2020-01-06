package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.JDBC;
import application.models.Coordinate;
import application.models.Coordinate;
import application.models.Drink;
import application.models.DrinkForSale;
import application.models.Pub;
import application.models.PubType;
import application.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Classe que efetua queries relativas aos drinks na base de dados
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public class DrinkDAO {
	/**
	 * Metodo para receber todas as bebidas que estejam aprovadas e em pubs na aplicacao
	 * @return Lista com todos os DrinksForSale que nao estao pendentes
	 */
	public static ObservableList<DrinkForSale> getDrinksInPubs() {
		ObservableList<DrinkForSale> lista = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		
		String sql = "SELECT * FROM drinks_for_sale \r\n" + 
				"INNER JOIN drinks \r\n" + 
				"ON drinks_for_sale.drink_id = drinks.drink_id \r\n" + 
				"INNER JOIN pubs\r\n" + 
				"ON drinks_for_sale.pub_id = pubs.pub_id \r\n" + 
				"INNER JOIN pub_types \r\n"+
				"ON pubs.pub_type_id = pub_types.pub_type_id "
				+ "WHERE drinks_for_sale.pending = 0";
				
		try (Statement stat = conn.createStatement()){
			try(ResultSet rs = stat.executeQuery(sql)){
				while(rs.next()) {
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
					Coordinate latLong = new Coordinate(xCoord,yCoord);
					Pub pub = new Pub(pubId, pubName, pubType, entryPrice, address, latLong, false);
					double rating = rs.getDouble("rating");
					double price = rs.getDouble("price");
					boolean pending = false;
					int drinkfsID = rs.getInt("drink_sale_id");
					lista.add(new DrinkForSale(drinkfsID, drink, pub , rating, price, pending));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();}
		return lista;
		}

	/**
	 * Metodo para receber a lista de bebidas do pub que � passado como parametro
	 * @param pub pub o qual se deseja receber as bebidas
	 * @return Lista com as DrinksForSale do pub especificado
	 */
	public static ObservableList<DrinkForSale> getDrinks(Pub pub) {
		ObservableList<DrinkForSale> drinks = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM drinks_for_sale "
				+ "INNER JOIN drinks "
				+ "ON drinks_for_sale.drink_id = drinks.drink_id "
				+ "WHERE pub_id = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, pub.getId());
			try(ResultSet rs = stat.executeQuery()){
			while (rs.next()) {
				int drinkfsID = rs.getInt("drink_sale_id");
				int drinkID = rs.getInt("drink_id");
				String drinkName = rs.getString("drink_name");
				Drink drink = new Drink(drinkID, drinkName, null);
				double rating = rs.getDouble("rating");
				double price = rs.getDouble("price");
				boolean pending = rs.getBoolean("pending");
				drinks.add(
						new DrinkForSale(drinkfsID, drink, pub , rating, price, pending));
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drinks;
	}

	/**
	 * Metodo para receber as bebidas que ainda nao foram aprovadas
	 * @return Lista com DrinkForSale que ainda estao com pending = 1
	 */
	public static ObservableList<DrinkForSale> getPendingDrinks() {
		ObservableList<DrinkForSale> lista = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		
		String sql = "SELECT * FROM drinks_for_sale \r\n" + 
				"INNER JOIN drinks \r\n" + 
				"ON drinks_for_sale.drink_id = drinks.drink_id \r\n" + 
				"INNER JOIN pubs\r\n" + 
				"ON drinks_for_sale.pub_id = pubs.pub_id \r\n" + 
				"INNER JOIN pub_types \r\n"+
				"ON pubs.pub_type_id = pub_types.pub_type_id "
				+ "WHERE drinks_for_sale.pending = 1";
				
		try (Statement stat = conn.createStatement()){
			try(ResultSet rs = stat.executeQuery(sql)){
				while(rs.next()) {
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
					Coordinate latLong = new Coordinate(xCoord,yCoord);
					Pub pub = new Pub(pubId, pubName, pubType, entryPrice, address, latLong, false);
					double rating = rs.getDouble("rating");
					double price = rs.getDouble("price");
					boolean pending = true;
					int drinkfsID = rs.getInt("drink_sale_id");
					lista.add(new DrinkForSale(drinkfsID, drink, pub , rating, price, pending));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();}
		return lista;
		}
	
	/**
	 * Deleta a bebida especificada da base de dados.
	 * @param drink bebida a qual se deseja deletar
	 */
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
	public static void defineRating(double rating, int nRating,int drink_id) {
		Connection conn = JDBC.getConnection();
		String sql = "UPDATE drinks_for_sale SET rating=?, n_ratings=? WHERE drink_sale_id = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setDouble(1, rating);
			stat.setDouble(2, nRating);
			stat.setDouble(3, drink_id);
			stat.executeUpdate();
			System.out.println("Rating: " + rating);
			System.out.println("Rating: " + nRating);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static double getRating(DrinkForSale drink) {
		double rating = 0;
		Connection con = JDBC.getConnection();
		String sql = "SELECT rating FROM drinks_for_sale "
					+ "WHERE drink_sale_id = ?";
		try (PreparedStatement stat = con.prepareStatement(sql)) {
			stat.setInt(1, drink.getId());
			try (ResultSet rs = stat.executeQuery()) {
				if(rs.next())
				rating = rs.getDouble("rating");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rating;
	}
	public static int getNRating(DrinkForSale drink) {
		int nRating = 0;
		Connection con = JDBC.getConnection();
		String sql = "SELECT n_ratings FROM drinks_for_sale "
					+ "WHERE drink_sale_id = ?";
		try (PreparedStatement stat = con.prepareStatement(sql)) {
			stat.setInt(1, drink.getId());
			try (ResultSet rs = stat.executeQuery()) {
				if(rs.next())
				nRating = rs.getInt("n_ratings");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nRating;
	}
	
	/**
	 * Metodo para receber todas as Drinks que o user definiu como favoritas
	 * @param user o utilizador no qual se deseja saber as bebidas
	 * @return Lista com Drinks favoritas do user
	 */
	public static ObservableList<Drink> getFavDrinks(User user) {
		ObservableList<Drink> favDrinkList = FXCollections.observableArrayList();
		Connection con = JDBC.getConnection();
		String sql = "SELECT * FROM user_favorite_drinks "
					+ "INNER JOIN drinks "
					+ "ON user_favorite_drinks.drink_id = drinks.drink_id "
					+ "WHERE user_id = ?";
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

	/**
	 * Define uma nova bebida como favorita para o User
	 * @param user utilizador o qual se deseja adicionar uma bebida favorita nova
	 * @param drink Bebida favorita que se deseja adicionar
	 */
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

	/**
	 * Adiciona uma nova DrinkForSale a base de dados
	 * @param drink bebida que se deseja adicionar a base de dados
	 */
	public static void addDrinkForSale(DrinkForSale drink) {
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

	/**
	 * Metodo para receber bebidas especificadas por algum dos ids recebidos.
	 * @param ids ids para filtrar as bebidas
	 * @return Lista com as bebidas apos filtro
	 */
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
					Coordinate latLong = new Coordinate(xCoord,yCoord);
					Pub pub = new Pub(pubId, pubName, pubType, entryPrice, address, latLong, false);
					double rating = rs.getDouble("rating");
					double price = rs.getDouble("price");
					boolean pending = false;
					int drinkfsID = rs.getInt("drink_sale_id");
					lista.add(new DrinkForSale(drinkfsID, drink, pub , rating, price, pending));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}


	/**
	 * Metodo para receber todos os tipos de bebidas disponiveis na base de dados
	 * @return Lista com os Drinks que a base de dados suporta
	 */
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

	/**
	 * Metodo para descobrir o tipo de bebida com o id especificado
	 * @param id id do tipo de bebida que se deseja descobrir
	 * @return o Drink com o id do paramentro
	 */
	public static Drink getDrinkType(int id) {
		ObservableList<Drink> drinks =  getDrinkTypes();
		for (Drink drink : drinks) {
			if (drink.getId() == id)
				return drink;
		}
		return null;
	}

	
	/**
	 * Metodo para aprovar o drink passado como parametro
	 * @param drink drink o qual se deseja aprovar
	 */
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

	/**
	 * Metodo para deletar a bebida da base de dados.
	 * @param drink drink o qual se deseja recusar e deletar da base de dados.
	 */
	public static void refuseDrink(DrinkForSale drink) {
		removeDrink(drink);
	}
}
