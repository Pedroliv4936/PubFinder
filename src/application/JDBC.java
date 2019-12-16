package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Classe de ligacao com a base de dados.
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public final class JDBC {
	private static final String URL = "jdbc:mysql://remotemysql.com:3306/Inr8NyMJYV";

	private static Connection con;
	private static final String USER = "Inr8NyMJYV";
	private static final String PASS = "Gd1YhhXXL5";

	private JDBC() {
	}

	/**
	 * Cria(quando preciso) e retorna o objeto de ligacao a base de dados
	 * @return objeto Connection.
	 */
	public static Connection getConnection() {
		try {
			if (con == null)
				con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
	}
		return con;
	}
}