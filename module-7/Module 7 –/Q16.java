package Assignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Q16 {
public static void main(String[] args) {
		
		JFrame frame = new JFrame("User Information Form");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Labels and text fields
		JLabel lblId = new JLabel("ID:");
		JTextField txtId = new JTextField(20);

		JLabel lblFname = new JLabel("First Name:");
		JTextField txtFname = new JTextField(20);

		JLabel lblLname = new JLabel("Last Name:");
		JTextField txtLname = new JTextField(20);

		JLabel lblEmail = new JLabel("Email:");
		JTextField txtEmail = new JTextField(20);

		JButton btnSubmit = new JButton("Submit");

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
		gbc.gridwidth = 2;
		frame.add(btnSubmit, gbc);

		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText();
				String fname = txtFname.getText();
				String lname = txtLname.getText();
				String email = txtEmail.getText();

				JOptionPane.showMessageDialog(frame,
						"ID: " + id + "\nFirst Name: " + fname + "\nLast Name: " + lname + "\nEmail: " + email,
						"Submitted Data", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		frame.setVisible(true);
	}
}

