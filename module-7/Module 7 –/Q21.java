package Assignment;
import java.sql.*;

public class Q21 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "root";
		String password = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database.");

			String sql = "{CALL GetEmployeeFullName(?, ?)}"; 
			CallableStatement cstmt = conn.prepareCall(sql);


			int empId = 3;
			cstmt.setInt(1, empId); 

			cstmt.registerOutParameter(2, Types.VARCHAR); 
			cstmt.execute();
			String fullName = cstmt.getString(2);
			System.out.println("Employee Full Name (ID " + empId + "): " + fullName);

			cstmt.close();
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

