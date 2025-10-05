package Assignment;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Q10 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database.");
			DatabaseMetaData metaData = conn.getMetaData();

			System.out.println("\nDatabase Metadata Information:");
			System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
			System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
			System.out.println("Database Major Version: " + metaData.getDatabaseMajorVersion());
			System.out.println("Database Minor Version: " + metaData.getDatabaseMinorVersion());
			System.out.println("Driver Name: " + metaData.getDriverName());
			System.out.println("Driver Version: " + metaData.getDriverVersion());
			System.out.println("Driver Major Version: " + metaData.getDriverMajorVersion());
			System.out.println("Driver Minor Version: " + metaData.getDriverMinorVersion());
			System.out.println("JDBC Major Version: " + metaData.getJDBCMajorVersion());
			System.out.println("JDBC Minor Version: " + metaData.getJDBCMinorVersion());
			System.out.println("URL: " + metaData.getURL());
			System.out.println("User Name: " + metaData.getUserName());
			System.out.println("Supports Transactions: " + metaData.supportsTransactions());
			System.out.println("Supports Batch Updates: " + metaData.supportsBatchUpdates());
			System.out.println("Max Connections: " + metaData.getMaxConnections());

			conn.close();
			System.out.println("\nConnection closed.");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database error occurred.");
			e.printStackTrace();
		}
	}
}

