package Assignment;

public class Q3 {
	public static void main(String[] args) {


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}

