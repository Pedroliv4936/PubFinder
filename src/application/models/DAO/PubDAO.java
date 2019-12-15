package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

import application.JDBC;
import application.models.Coordinate;
import application.models.DrinkForSale;
import application.models.Pub;
import application.models.PubType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe que efetua queries relativas aos pubs na base de dados
 * @author Franco Zalamena & Pedro Oliveira
 *
 */
public class PubDAO {

	private static ObservableList<Pub> pubsOrdered = FXCollections.observableArrayList();

	private PubDAO() {
	}

	// 1 KM = 0,0095859326983177
	private final static double kmToCoord = 0.0095859326983177;
	private final static double DISTANCIA_MINIMA = 5 * kmToCoord;

	/**
	 * Metodo para receber um pub com o nome especificado
	 * 
	 * @param pubName String com o nome do pub que se deseja
	 * @return Devolve o Pub com o nome especificado/
	 */
	public static Pub getPub(String pubName) {
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM pubs WHERE pub_name = ?";

		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, pubName);
			try (ResultSet rs = stat.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("pub_name");
					int typeId = rs.getInt("pub_type_id");
					PubType type = getPubType(typeId);
					int id = rs.getInt("pub_id");
					double price = rs.getDouble("entry_price");
					String address = rs.getString("address");
					double xCoord = rs.getDouble("xCoord");
					double yCoord = rs.getDouble("yCoord");
					Coordinate latLong = new Coordinate(xCoord, yCoord);
					boolean pending = rs.getBoolean("pending");
					return new Pub(id, name, type, price, address, latLong, pending);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo para receber todos os pubs na base de dados
	 * 
	 * @return
	 */
	public static ObservableList<Pub> getPubs() {
		ObservableList<Pub> pubList = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("SELECT * FROM pubs " + "INNER JOIN pub_types "
						+ "ON pubs.pub_type_id = pub_types.pub_type_id")) {
			while (rs.next()) {
				String name = rs.getString("pub_name");
				int typeId = rs.getInt("pub_type_id");
				String pubTypeName = rs.getString("pub_type_name");
				PubType type = new PubType(typeId, pubTypeName);
				int id = rs.getInt("pub_id");
				double price = rs.getDouble("entry_price");
				String address = rs.getString("address");
				double xCoord = rs.getDouble("xCoord");
				double yCoord = rs.getDouble("yCoord");
				Coordinate latLong = new Coordinate(xCoord, yCoord);
				boolean pending = rs.getBoolean("pending");
				pubList.add(new Pub(id, name, type, price, address, latLong, pending));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubList;
	}

	public static ObservableList<Pub> getPubsOrdered() {
		return pubsOrdered;
	}

	/**
	 * Define a lista de pubs ordenada por distancia as coordenadas X e Y
	 * fornecidas.
	 * 
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 */
	public static void setPubsOrdered(double x, double y) {
		pubsOrdered = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (PreparedStatement stat = conn.prepareStatement(
				"Select * from pubs " + "INNER JOIN pub_types " + "ON pubs.pub_type_id = pub_types.pub_type_id "
						+ "ORDER BY SQRT(POW(? - pubs.xCoord,2) + POW(? - pubs.yCoord,2)) ASC ")) {
			stat.setDouble(1, x);
			stat.setDouble(2, y);
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					String name = rs.getString("pub_name");
					int typeId = rs.getInt("pub_type_id");
					String pubTypeName = rs.getString("pub_type_name");
					PubType type = new PubType(typeId, pubTypeName);
					int id = rs.getInt("pub_id");
					double price = rs.getDouble("entry_price");
					String address = rs.getString("address");
					double xCoord = rs.getDouble("xCoord");
					double yCoord = rs.getDouble("yCoord");
					boolean pending = rs.getBoolean("pending");
					Pub pub = new Pub(id, name, type, price, address, new Coordinate(xCoord, yCoord), pending);
					pub.setRating();
					pubsOrdered.add(pub);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para reveber todos os pubs que nao estejam pendentes na base de dados
	 * @return Lista com Pubs que estejam com pending = 0
	 */
	public static ObservableList<Pub> getActivePubs() {
		ObservableList<Pub> pubList = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		ObservableList<DrinkForSale> drinks = DrinkDAO.getDrinksInPubs();
		try (Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("SELECT * FROM pubs " + "INNER JOIN pub_types "
						+ "ON pubs.pub_type_id = pub_types.pub_type_id " + "WHERE pending = 0")) {
			while (rs.next()) {
				String name = rs.getString("pub_name");
				int typeId = rs.getInt("pub_type_id");
				String pubTypeName = rs.getString("pub_type_name");
				PubType type = new PubType(typeId, pubTypeName);
				int id = rs.getInt("pub_id");
				double price = rs.getDouble("entry_price");
				String address = rs.getString("address");
				double xCoord = rs.getDouble("xCoord");
				double yCoord = rs.getDouble("yCoord");
				boolean pending = rs.getBoolean("pending");

				pubList.add(new Pub(id, name, type, price, address, new Coordinate(xCoord, yCoord), pending));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubList;
	}

	/**
	 * Metodo para receber o Pub com o id especificado
	 * @param id id do pub que se deseja receber
	 * @return Pub com o id do parametro
	 */
	public static Pub getPub(int id) {
		ObservableList<Pub> pubs = getPubs();
		for (Pub pub : pubs) {
			if (pub.getId() == id)
				return pub;
		}
		return null;
	}

	/**
	 * Metodo para receber todos os tipos de pubs existentes na base de dados.
	 * @return Lista com todos PubTypes disponiveis na base de dados.
	 */
	public static ObservableList<PubType> getPubTypes() {
		ObservableList<PubType> pubTypes = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery("SELECT * FROM pub_types")) {
			while (rs.next()) {
				String name = rs.getString("pub_type_name");
				int typeId = rs.getInt("pub_type_id");
				pubTypes.add(new PubType(typeId, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubTypes;
	}

	/**
	 * Recebe o id do pubType pretendido e devolve o objeto de PubType
	 * @param id id do pubtype pretendido
	 * @return PubType com o id especificado
	 */
	public static PubType getPubType(int id) {
		for (PubType pt : getPubTypes()) {
			if (pt.getId() == id)
				return pt;
		}
		return null;
	}

	/**
	 * Recebe o nome do pubType pretendido e devolve o objeto de PubType
	 * @param name nome do pubtype pretendido
	 * @return PubType com o nome especificado
	 */
	public static PubType getPubType(String name) {
		for (PubType pt : getPubTypes()) {
			if (pt.toString().equals(name))
				return pt;
		}
		return null;
	}

	/**
	 * Metodo para reveber todos os pubs que estejam pendentes na base de dados
	 * @return Lista com Pubs que estejam com pending = 1
	 */
	public static ObservableList<Pub> getPendingPubs() {
		ObservableList<Pub> pubList = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("SELECT * FROM pubs WHERE pending = 1")) {
			while (rs.next()) {
				String name = rs.getString("pub_name");
				int typeId = rs.getInt("pub_type_id");
				PubType type = getPubType(typeId);
				int id = rs.getInt("pub_id");
				double price = rs.getDouble("entry_price");
				String address = rs.getString("address");
				double xCoord = rs.getDouble("xCoord");
				double yCoord = rs.getDouble("yCoord");
				boolean pending = rs.getBoolean("pending");
				pubList.add(new Pub(id, name, type, price, address, new Coordinate(xCoord, yCoord), pending));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubList;
	}

	/**
	 * Metodo para aprovar um pub na base de dados. Ele define o pending do pub de 1 para 0
	 * @param pub pub o qual se deseja aprovar
	 */
	public static void aprovePub(Pub pub) {
		Connection conn = JDBC.getConnection();
		String sql = "UPDATE pubs " + "SET pending = 0 " + "WHERE pub_id = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, pub.getId());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pub para recusar um pub na base de dados.
	 * @param pub pub o qual se deseja deletar da base de dados
	 */
	public static void refusePub(Pub pub) {
		removePub(pub);
	}

	/**
	 * Metodo para deletar o pub especificado da base de dados.
	 * @param pub pub o qual se deseja deletar da base de dados
	 */
	public static void removePub(Pub pub) {
		Connection conn = JDBC.getConnection();
		String sql = "DELETE FROM pubs " + "WHERE pub_id = ?";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setInt(1, pub.getId());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo para adicionar o pub especificado a base de dados.
	 * @param pub pub o qual se deseja adicionar da base de dados
	 */
	public static void addPub(Pub pub) {
		Connection conn = JDBC.getConnection();
		String sql = "INSERT INTO pubs " + "(pub_name, entry_price, pub_type_id, xCoord, yCoord, address, pending)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, pub.toString());
			stat.setDouble(2, pub.getPrice());
			stat.setInt(3, pub.getType().getId());
			stat.setDouble(4, pub.getCoordinates().getX());
			stat.setDouble(5, pub.getCoordinates().getY());
			stat.setString(6, pub.getAddress());
			stat.setBoolean(7, true);
			stat.executeUpdate();
			System.out.println("Enviado a base de dados");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
