import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class identify extends JFrame {
    // Constructor
    public identify() {
        // Set up JFrame
        setTitle("Login Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Welcome! Please Log In", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        getContentPane().add(titleLabel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Customer", "Staff"});
        centerPanel.add(roleLabel);
        centerPanel.add(roleComboBox);

        getContentPane().add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Log In");
        buttonPanel.add(loginButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Action Listener for Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                // Retrieve customerId if role is Customer
                int customerId = -1;

                if (role.equals("Customer")) {
                    customerId = authenticateCustomer(username, password); // Get customer ID
                } else if (role.equals("Staff")) {
                    if (authenticateStaff(username, password)) {
                        JOptionPane.showMessageDialog(null, "Login Successful as Staff!");
                        openStaffPage();  // Opens Staff Frame
                        dispose();  // Close the login window
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                    return; // Skip further steps for staff login
                }

                if (customerId != -1) {
                    JOptionPane.showMessageDialog(null, "Login Successful as Customer!");
                    openCustomerPage(customerId); // Opens Customer Frame with customerId
                    dispose();  // Close the login window after successful login
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
