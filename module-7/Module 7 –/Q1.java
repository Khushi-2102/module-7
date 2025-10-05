package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Q1 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/khushi"; 
		String username = "root"; 
		String password = "1234"; 

		Connection connection = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(url, username, password);

			System.out.println("Connection successful!");

		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
		} finally {
			// Close the connection
			if (connection != null) {
				try {
					connection.close();
					System.out.println("Connection closed.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

