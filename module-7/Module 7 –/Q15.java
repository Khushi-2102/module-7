package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Q15 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database.\n");

			String insertSQL = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
			PreparedStatement pstmtInsert = conn.prepareStatement(insertSQL);
			pstmtInsert.setInt(1, 1);
			pstmtInsert.setString(2, "Alice");
			pstmtInsert.setInt(3, 22);
			int rowsInserted = pstmtInsert.executeUpdate();
			System.out.println("Inserted rows: " + rowsInserted);
			

			String updateSQL = "UPDATE students SET age = ?, name = ? WHERE id = ?";
			PreparedStatement pstmtUpdate = conn.prepareStatement(updateSQL);
			pstmtUpdate.setInt(1, 23);
			pstmtUpdate.setString(2, "Alice Smith");
			pstmtUpdate.setInt(3, 1);
			int rowsUpdated = pstmtUpdate.executeUpdate();
			System.out.println("Updated rows: " + rowsUpdated);

			String selectSQL = "SELECT * FROM students WHERE age > ?";
			PreparedStatement pstmtSelect = conn.prepareStatement(selectSQL);
			pstmtSelect.setInt(1, 21);
			ResultSet rs = pstmtSelect.executeQuery();

			System.out.println("\nSelected Records:");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				System.out.println(id + " | " + name + " | " + age);
			}

			String deleteSQL = "DELETE FROM students WHERE id = ?";
			PreparedStatement pstmtDelete = conn.prepareStatement(deleteSQL);
			pstmtDelete.setInt(1, 1);
			int rowsDeleted = pstmtDelete.executeUpdate();
			System.out.println("\nDeleted rows: " + rowsDeleted);

			rs.close();
			pstmtInsert.close();
			pstmtUpdate.close();
			pstmtSelect.close();
			pstmtDelete.close();
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

