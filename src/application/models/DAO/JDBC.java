package application.models.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JDBC {

	private static final String CONN = "jdbc:mysql://localhost:3306/teste";

	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	static boolean connected;

	public JDBC() {
	}

	public static void  dbConnect(String username, String password) throws SQLException {

		try {
			con = DriverManager.getConnection(CONN, username, password);

			connected=true;
		} catch (SQLException e) {
			connected=false;
			System.out.println(e);
		}
	}
	
	public static void getPubs() throws SQLException {

	}
	
	public static void setCon(Connection con) {
		JDBC.con = con;
	}

	public static void setStmt(Statement stmt) {
		JDBC.stmt = stmt;
	}

	public static void setRs(ResultSet rs) {
		JDBC.rs = rs;
	}

	public static void setConnected(boolean connected) {
		JDBC.connected = connected;
	}

	public static String getConn() {
		return CONN;
	}

	public static Connection getCon() {
		return con;
	}

	public static Statement getStmt() {
		return stmt;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public void disconnectDb() {
		try {
			if(con!=null)
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isConnected() {
		return connected;
	}
}