package Assignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Q8 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test"; 
		String user = "root"; 
		String password = "1234"; 

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database.");
			String sql = "SELECT * FROM students";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("\nStudent Records:");
			while (rs.next()) {
				int id = rs.getInt("id"); 			
				String name = rs.getString("name"); 
				int age = rs.getInt("age"); 		

				System.out.println(id + "  " + name + "  " + age);
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

