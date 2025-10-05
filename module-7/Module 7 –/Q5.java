package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Q5 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "1234";

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database.");
			Statement stmt = conn.createStatement();

			String insertSQL = "INSERT INTO students (id, name, age) VALUES (1, 'Alice', 22)";
			int rowsInserted = stmt.executeUpdate(insertSQL);
			System.out.println("Inserted rows: " + rowsInserted);

			// Step 5: UPDATE data
			String updateSQL = "UPDATE students SET age = 23 WHERE id = 1";
			int rowsUpdated = stmt.executeUpdate(updateSQL);
			System.out.println("Updated rows: " + rowsUpdated);

			// Step 6: SELECT data
			String selectSQL = "SELECT * FROM students";
			ResultSet rs = stmt.executeQuery(selectSQL);

			System.out.println("\nStudent Records:");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				System.out.println(id + "  " + name + "  " + age);
			}

			// Step 7: DELETE data
			String deleteSQL = "DELETE FROM students WHERE id = 1";
			int rowsDeleted = stmt.executeUpdate(deleteSQL);
			System.out.println("Deleted rows: " + rowsDeleted);

			// Step 8: Close connection
			stmt.close();
			conn.close();
			System.out.println("Connection closed.");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database error occurred.");
			e.printStackTrace();
		}
	}
}

