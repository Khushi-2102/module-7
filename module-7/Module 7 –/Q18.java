package Assignment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Q18 {
	private static final String URL = "jdbc:mysql://localhost:3306/test";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	public static void main(String[] args) {
		JFrame frame = new JFrame("Swing CRUD Application");
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Labels and TextFields
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
		JButton btnSelect = new JButton("Display All");
		JButton btnDelete = new JButton("Delete");

		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		tableModel.addColumn("ID");
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Email");
		JScrollPane tableScroll = new JScrollPane(table);

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

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		frame.add(tableScroll, gbc);

		// Action Listeners
		btnInsert.addActionListener((ActionEvent e) -> {
			try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String sql = "INSERT INTO users (id, fname, lname, email) VALUES (?, ?, ?, ?)";
				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, Integer.parseInt(txtId.getText()));
					pstmt.setString(2, txtFname.getText());
					pstmt.setString(3, txtLname.getText());
					pstmt.setString(4, txtEmail.getText());
					int rows = pstmt.executeUpdate();
					JOptionPane.showMessageDialog(frame, rows + " record inserted!");
					displayTable(tableModel);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
			}
		});

		btnUpdate.addActionListener((ActionEvent e) -> {
			try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
				String sql = "UPDATE users SET fname=?, lname=?, email=? WHERE id=?";
				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setString(1, txtFname.getText());
					pstmt.setString(2, txtLname.getText());
					pstmt.setString(3, txtEmail.getText());
					pstmt.setInt(4, Integer.parseInt(txtId.getText()));
					int rows = pstmt.executeUpdate();
					JOptionPane.showMessageDialog(frame, rows + " record updated!");
					displayTable(tableModel);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
			}
		});

		btnSelect.addActionListener((ActionEvent e) -> displayTable(tableModel));

		btnDelete.addActionListener((ActionEvent e) -> {
			try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
				String sql = "DELETE FROM users WHERE id=?";
				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, Integer.parseInt(txtId.getText()));
					int rows = pstmt.executeUpdate();
					JOptionPane.showMessageDialog(frame, rows + " record deleted!");
					displayTable(tableModel);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
			}
		});

		frame.setVisible(true);
	}

	private static void displayTable(DefaultTableModel tableModel) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM users";
			try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

				tableModel.setRowCount(0);

				while (rs.next()) {
					Object[] row = { rs.getInt("id"), rs.getString("fname"), rs.getString("lname"),
							rs.getString("email") };
					tableModel.addRow(row);
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error fetching data: " + ex.getMessage());
		}
	}
}

