package application.models.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.JDBC;
import application.models.Pub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PubDAO {

	private PubDAO() {
	}

	private static ObservableList<Pub> pendingPubList = FXCollections.observableArrayList();

	// 1 KM = 0,0095859326983177
	private final static double kmToCoord = 0.0095859326983177;
	private final static double DISTANCIA_MINIMA = 5 * kmToCoord;

	public static ObservableList<Pub> getPubList() {
		ObservableList<Pub> pubList = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		try (Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery("SELECT * FROM Pubs")) {
			while (rs.next()) {
				String name = rs.getString("name");
				String type = rs.getString("type");
				int id = rs.getInt("id");
				double price = rs.getDouble("price");
				double rating = rs.getDouble("rating");
				String address = rs.getString("address");
				double xCoord = rs.getDouble("xCoord");
				double yCoord = rs.getDouble("yCoord");
				pubList.add(new Pub(id, name, type, yCoord, yCoord, address, yCoord, yCoord));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pubList;
	}

	public static ObservableList<Pub> getPendingPubs() {
		return pendingPubList;
	}

	private static ObservableList<Pub> nearPubs = getPubList();

	public static Pub getPreviousPub() {
		return nearPubs.get(nearPubs.size() - 1);
	}

	public static Pub nextNearPub(Pub selectedPub) {
		System.out.println(nearPubs + " Bares Proximos");
		Pub prev = nearPubs.get(nearPubs.size() - 1);
		nearPubs = FXCollections.observableArrayList();
		for (Pub pub : PubDAO.getPubList()) {
			double distance = Math.sqrt(Math.pow(selectedPub.getxCoord() - pub.getxCoord(), 2)
					+ Math.pow(selectedPub.getyCoord() - pub.getyCoord(), 2));
			System.out.println(pub.toString() + " Distancia: " + distance);
			if (distance <= DISTANCIA_MINIMA && distance != 0) {
				if (!selectedPub.equals(pub) && !pub.equals(prev)) {
					System.out.println(pub.toString() + " Adicionado aos bares proximos");
					nearPubs.add(pub);
				}
			}
		}

		nearPubs.add(selectedPub);
		System.out.println(prev.toString() + " Esta na posicao " + nearPubs.indexOf(prev));
		System.out.println(nearPubs.get(nearPubs.size() - 1));
		return prev;
	}

	public static ObservableList<Pub> sortList(ObservableList<Pub> list, Pub selectedPub) {
		ObservableList<Pub> orderedList = FXCollections.observableArrayList();
		double nearestDistance = 9999;
		for (Pub pub : list) {
			double distance = selectedPub.distance(pub);
			if (distance < nearestDistance) {
				orderedList.add(0, pub);
			} else {
				orderedList.add(pub);
			}
		}
		System.out.println(orderedList);
		return orderedList;

	}

	public static ObservableList<Pub> getNearPubs() {
		return nearPubs;
	}
	static {
	}
}