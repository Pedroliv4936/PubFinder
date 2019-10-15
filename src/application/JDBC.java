package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JDBC {

	private static final String CONN = "jdbc:mysql://178.166.38.153:3306/mydb";

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	boolean connected;

	public JDBC() {
	}

	public void dbConnect(String username, String password) throws SQLException {

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
	
	public boolean isConnected() {
		return connected;
	}
}