import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoginRegisterForm extends JFrame implements ActionListener {

    // Login components
    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Register components
    private JLabel registerLabel;
    private JLabel newUsernameLabel;
    private JLabel newPasswordLabel;
    private JTextField newUsernameTextField;
    private JPasswordField newPasswordField;
    private JButton registerButton;


    public LoginRegisterForm() {
        // Set up the frame
        setTitle("Login/Register Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        // Initialize the login components
        loginLabel = new JLabel("Login");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Initialize the register components
        registerLabel = new JLabel("Register");
        newUsernameLabel = new JLabel("New Username:");
        newPasswordLabel = new JLabel("New Password:");
        newUsernameTextField = new JTextField(20);
        newPasswordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        // Set up the login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.add(loginLabel);
        loginPanel.add(new JLabel(""));
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);

        // Set up the register panel
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(3, 2));
        registerPanel.add(registerLabel);
        registerPanel.add(new JLabel(""));
        registerPanel.add(newUsernameLabel);
        registerPanel.add(newUsernameTextField);
        registerPanel.add(newPasswordLabel);
        registerPanel.add(newPasswordField);
        registerPanel.add(new JLabel(""));
        registerPanel.add(registerButton);

        // Set up the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Register", registerPanel);

        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Add action listeners to the buttons
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // Display the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameTextField.getText();
            String password = new String(passwordField.getPassword());
            // Checks if the username and password are valid
            boolean isValid = checkCredentials(username, password);
            if (isValid) {
                // Close the LoginRegisterForm window
                dispose();
                // Show the Main GUI
                Main main = new Main();
                main.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        } else if (e.getSource() == registerButton) {
            String newUsername = newUsernameTextField.getText();
            String newPassword = new String(newPasswordField.getPassword());
            // Register the new user
            try {
                File file = new File("users.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(newUsername + "," + newPassword + "\n");
                writer.close();
                JOptionPane.showMessageDialog(this, "Registration successful!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean checkCredentials(String username, String password) {
        try {
            File file = new File("users.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                // if user/password = same
                if (tokens.length == 2 && tokens[0].equals(username) && tokens[1].equals(password)) {
                    return true;
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        new LoginRegisterForm();
    }
}