package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

import application.JDBC;
import application.models.Coordinates;
import application.models.Pub;
import application.models.PubType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PubDAO {

	private PubDAO() {
	}

	// 1 KM = 0,0095859326983177
	private final static double kmToCoord = 0.0095859326983177;
	private final static double DISTANCIA_MINIMA = 5 * kmToCoord;

	public static ObservableList<Pub> getPubs() {
		ObservableList<Pub> pubList = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("SELECT * FROM pubs")) {
			while (rs.next()) {
				String name = rs.getString("pub_name");
				int typeId = rs.getInt("pub_type_id");
				PubType type = getPubType(typeId);
				int id = rs.getInt("pub_id");
				double price = rs.getDouble("entry_price");
				String address = rs.getString("address");
				double xCoord = rs.getDouble("xCoord");
				System.out.println(xCoord);
				double yCoord = rs.getDouble("yCoord");
				System.out.println(yCoord);
				Coordinates latLong = new Coordinates(xCoord,yCoord);
				boolean pending = rs.getBoolean("pending");
				pubList.add(new Pub(id, name, type, price, 5, address, latLong, pending));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubList;
	}	
	
	public static ObservableList<Pub> getPubsOrdered(double x,double y) {
		ObservableList<Pub> pubList = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (PreparedStatement stat = conn.prepareStatement("call getPubsByDistance(?,?)")){
				stat.setDouble(1, x);
				stat.setDouble(2, y);
				try(ResultSet rs = stat.executeQuery()) {
			while (rs.next()) {
				String name = rs.getString("pub_name");
				int typeId = rs.getInt("pub_type_id");
				PubType type = getPubType(typeId);
				int id = rs.getInt("pub_id");
				double price = rs.getDouble("entry_price");
				String address = rs.getString("address");
				double xCoord = rs.getDouble("xCoord");
				System.out.println(xCoord);
				double yCoord = rs.getDouble("yCoord");
				System.out.println(yCoord);
				Coordinates latLong = new Coordinates(xCoord,yCoord);
				boolean pending = rs.getBoolean("pending");
				pubList.add(new Pub(id, name, type, price, 5, address, latLong, pending));
			}}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubList;
	}
	
	public static ObservableList<Pub> getActivePubs() {
		ObservableList<Pub> pubList = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("SELECT * FROM pubs WHERE pending = 0")) {
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
				pubList.add(new Pub(id, name, type, price, 5, address, new Coordinates(xCoord, yCoord), pending));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubList;
	}
	
	
	public static Pub getPub(int id) {
		for(Pub pub: getPubs()) {
			if(pub.getId() == id)
				return pub;
		}
		return null;
	}

	public static ObservableList<PubType> getPubTypes() {
		ObservableList<PubType> pubTypes = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery("SELECT * FROM pub_types")) {
			while (rs.next()) {
				String name = rs.getString("pub_type_name");
				int typeId = rs.getInt("pub_type_id");
				pubTypes.add(new PubType(typeId, name));
				System.out.println(name + "ADICIONADO AS OPCOES");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubTypes;
	}

	public static PubType getPubType(int id) {
		for (PubType pt : getPubTypes()) {
			if (pt.getId() == id)
				return pt;
		}
		return null;
	}

	public static PubType getPubType(String name) {
		for (PubType pt : getPubTypes()) {
			if (pt.toString().equals(name))
				return pt;
		}
		return null;
	}

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
				pubList.add(new Pub(id, name, type, price, 5, address, new Coordinates(xCoord, yCoord), pending));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubList;
	}

	public static void aprovePub(Pub pub) {
		Connection conn = JDBC.getConnection();
		String sql = "UPDATE pubs "+
					 "SET pending = 0 "+
					 "WHERE pub_id = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, pub.getId());
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void refusePub(Pub pub) {
		removePub(pub);
	}

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

	public static void addPub(Pub pub) {
		Connection conn = JDBC.getConnection();
		String sql = "INSERT INTO pubs "
				+ "(pub_name, entry_price, pub_type_id, xCoord, yCoord, address, pending)"
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

	public static ObservableList<Pub> sortList(Coordinates loc) {
		ObservableList<Pub> sortedPubs = getActivePubs();
		Collections.sort(sortedPubs, (pub1, pub2) -> ((Double) pub2.distance(loc)).compareTo(pub1.distance(loc)));
		/*
		 * 
		 * new Comparator<Pub>() {
		 * 
		 * @Override public int compare(Pub pub2, Pub pub1) { return
		 * ((Double)pub2.distance(loc)).compareTo(pub1.distance(loc)); } });
		 */

//	private static ObservableList<Pub> nearPubs = FXCollections.observableArrayList();
//	
//	public static ObservableList<Pub> getNearPubs(LatLong local) {
//		ObservableList<Pub>  nearPubs = FXCollections.observableArrayList();
//		for (Pub pub : getActivePubs()) {
//			double distance = pub.getCoordinates().distanceFrom(local);
//			System.out.println(pub.toString() + " Distancia: " + distance);
//			if (distance <= DISTANCIA_MINIMA && distance != 0) {
//				System.out.println(pub.toString() + " Adicionado aos bares proximos");
//				nearPubs.add(pub);
//			}
//		}
//		PubDAO.nearPubs = nearPubs;
//		return nearPubs;
//	}
//	
//	public static ObservableList<Pub> next(LatLong local) {
//		ObservableList<Pub>  nearPubs = FXCollections.observableArrayList();
//		Pub first = PubDAO.nearPubs.get(0);
//		for(int i = 0; i< PubDAO.nearPubs.size()-1; i++) {
//			nearPubs.set(i, PubDAO.nearPubs.get(i+1));
//		}
//		nearPubs.add(first);
//		PubDAO.nearPubs = nearPubs;
//		return nearPubs;
//	}
		return sortedPubs;
	}
}
