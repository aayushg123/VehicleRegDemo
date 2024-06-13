package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection connection;

	private DBUtil() {

	}

	public static Connection getConnection() {
		return connection;
	}

	static {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver Loaded");

			connection = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "a");
			System.out.println("Original connection= " + connection.hashCode());
			System.out.println("Connected");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver couldn't be loaded.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection coudn't be established.");
			e.printStackTrace();
		}

	}

}
