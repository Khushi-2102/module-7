package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Q9 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "root";
		String password = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database.");

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String sql = "SELECT * FROM students";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.first()) {
				System.out.println(
						"First Record: " + rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getInt("age"));
			}

			if (rs.last()) {
				System.out.println(
						"Last Record: " + rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getInt("age"));
			}

			if (rs.absolute(2)) {
				System.out.println(
						"Absolute(2): " + rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getInt("age"));
			}

			if (rs.previous()) {
				System.out.println("Previous Record: " + rs.getInt("id") + " | " + rs.getString("name") + " | "
						+ rs.getInt("age"));
			}

			rs.beforeFirst();
			System.out.println("\nIterating with next():");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getInt("age"));
			}

			rs.close();
			stmt.close();
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

