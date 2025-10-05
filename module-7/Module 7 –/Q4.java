package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Q4 {
public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "1234";

		try {
			// Step 1: Load JDBC driver (optional in newer versions, but good practice)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 2: Establish the connection
			Connection connection = DriverManager.getConnection(url, username, password);

			// Step 3: Print confirmation message
			System.out.println("Database connection established successfully!");

			// Step 4: Close connection
			connection.close();
			System.out.println("Connection closed.");

		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Failed to connect to the database.");
			e.printStackTrace();
		}
	}
}

