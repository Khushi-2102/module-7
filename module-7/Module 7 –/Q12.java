package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class Q12 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database.\n");

			String sql = "SELECT * FROM students";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columnCount = rsMeta.getColumnCount();

			System.out.println("Number of Columns: " + columnCount);

			System.out.println("\nColumn Details:");
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsMeta.getColumnName(i);
				String columnType = rsMeta.getColumnTypeName(i);
				int columnSize = rsMeta.getColumnDisplaySize(i);
				System.out.println("- " + columnName + " | Type: " + columnType + " | Size: " + columnSize);
			}
			System.out.println("\nData from ResultSet:");
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}

			rs.close();
			pstmt.close();
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

