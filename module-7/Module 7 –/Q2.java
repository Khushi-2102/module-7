package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Q2 {
	 public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/test";
	        String username = "root";
	        String password = "1234";
	        
	        try {
	            // Step 1: Load JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            System.out.println("JDBC Driver loaded successfully.");
	            
	            // Step 2: Establish connection
	            Connection connection = DriverManager.getConnection(url, username, password);
	            System.out.println("Connection established successfully!");
	            
	            // Close the connection
	            connection.close();
	            System.out.println("Connection closed.");
	            
	        } catch (ClassNotFoundException e) {
	            System.out.println("Failed to load JDBC Driver.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("Failed to establish connection.");
	            e.printStackTrace();
	        }
	    }
	}

