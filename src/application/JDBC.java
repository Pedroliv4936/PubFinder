package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JDBC {

	private static final String CONN = "jdbc:mysql://localhost:3306/teste";

	static Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
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