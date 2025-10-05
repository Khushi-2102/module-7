package Assignment;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Q11 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database.\n");
			DatabaseMetaData metaData = conn.getMetaData();

			System.out.println("Database Information:");
			System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
			System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
			System.out.println("Driver Name: " + metaData.getDriverName());
			System.out.println("Driver Version: " + metaData.getDriverVersion());
			System.out.println();

			System.out.println("List of Tables:");
			ResultSet tables = metaData.getTables(null, null, "%", new String[] { "TABLE" });
			while (tables.next()) {
				System.out.println("- " + tables.getString("TABLE_NAME"));
			}
			tables.close();
			System.out.println();

			System.out.println("Supported SQL Features:");
			System.out.println("Supports Transactions: " + metaData.supportsTransactions());
			System.out.println("Supports Batch Updates: " + metaData.supportsBatchUpdates());
			System.out.println("Supports Stored Procedures: " + metaData.supportsStoredProcedures());
			System.out.println("Supports Outer Joins: " + metaData.supportsOuterJoins());
			System.out.println("Supports Full Outer Joins: " + metaData.supportsFullOuterJoins());
			System.out.println("Supports Group By: " + metaData.supportsGroupBy());
			System.out.println("Supports Subqueries in EXISTS: " + metaData.supportsSubqueriesInExists());
			System.out.println("Supports Union: " + metaData.supportsUnion());
			System.out.println("Supports Union All: " + metaData.supportsUnionAll());

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

