package Assignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
public class Q17 {
	private static final String URL = "jdbc:mysql://localhost:3306/test";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	public static void main(String[] args) {
		JFrame frame = new JFrame("CRUD Application");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblId = new JLabel("ID:");
		JTextField txtId = new JTextField(20);

		JLabel lblFname = new JLabel("First Name:");
		JTextField txtFname = new JTextField(20);

		JLabel lblLname = new JLabel("Last Name:");
		JTextField txtLname = new JTextField(20);

		JLabel lblEmail = new JLabel("Email:");
		JTextField txtEmail = new JTextField(20);

		JButton btnInsert = new JButton("Insert");
		JButton btnUpdate = new JButton("Update");
		JButton btnSelect = new JButton("Select");
		JButton btnDelete = new JButton("Delete");

		gbc.gridx = 0;
		gbc.gridy = 0;
		frame.add(lblId, gbc);
		gbc.gridx = 1;
		frame.add(txtId, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.add(lblFname, gbc);
		gbc.gridx = 1;
		frame.add(txtFname, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		frame.add(lblLname, gbc);
		gbc.gridx = 1;
		frame.add(txtLname, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		frame.add(lblEmail, gbc);
		gbc.gridx = 1;
		frame.add(txtEmail, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		frame.add(btnInsert, gbc);
		gbc.gridx = 1;
		frame.add(btnUpdate, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		frame.add(btnSelect, gbc);
		gbc.gridx = 1;
		frame.add(btnDelete, gbc);

		ActionListener dbAction = e -> {
			try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
				Class.forName("com.mysql.cj.jdbc.Driver");

				if (e.getSource() == btnInsert) {
					String sql = "INSERT INTO users (id, fname, lname, email) VALUES (?, ?, ?, ?)";
					try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
						pstmt.setInt(1, Integer.parseInt(txtId.getText()));
						pstmt.setString(2, txtFname.getText());
						pstmt.setString(3, txtLname.getText());
						pstmt.setString(4, txtEmail.getText());
						int rows = pstmt.executeUpdate();
						JOptionPane.showMessageDialog(frame, rows + " record inserted!");
					}
				}

				else if (e.getSource() == btnUpdate) {
					String sql = "UPDATE users SET fname=?, lname=?, email=? WHERE id=?";
					try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
						pstmt.setString(1, txtFname.getText());
						pstmt.setString(2, txtLname.getText());
						pstmt.setString(3, txtEmail.getText());
						pstmt.setInt(4, Integer.parseInt(txtId.getText()));
						int rows = pstmt.executeUpdate();
						JOptionPane.showMessageDialog(frame, rows + " record updated!");
					}
				}

				else if (e.getSource() == btnSelect) {
					String sql = "SELECT * FROM users WHERE id=?";
					try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
						pstmt.setInt(1, Integer.parseInt(txtId.getText()));
						try (ResultSet rs = pstmt.executeQuery()) {
							if (rs.next()) {
								txtFname.setText(rs.getString("fname"));
								txtLname.setText(rs.getString("lname"));
								txtEmail.setText(rs.getString("email"));
								JOptionPane.showMessageDialog(frame, "Record found!");
							} else {
								JOptionPane.showMessageDialog(frame, "No record found.");
							}
						}
					}
				}

				else if (e.getSource() == btnDelete) {
					String sql = "DELETE FROM users WHERE id=?";
					try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
						pstmt.setInt(1, Integer.parseInt(txtId.getText()));
						int rows = pstmt.executeUpdate();
						JOptionPane.showMessageDialog(frame, rows + " record deleted!");
					}
				}

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
			} catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(frame, "JDBC Driver not found.");
			}
		};

		btnInsert.addActionListener(dbAction);
		btnUpdate.addActionListener(dbAction);
		btnSelect.addActionListener(dbAction);
		btnDelete.addActionListener(dbAction);

		frame.setVisible(true);
	}
}

