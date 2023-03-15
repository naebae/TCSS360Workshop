import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/***
 * Login/Register panel GUI
 *
 * Created by: Matthew Burgess
 */
public class LoginRegisterForm extends JFrame implements ActionListener {

    // Login components
    private JPanel loginPanel;

    private JLabel loginLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    protected JTextField usernameTextField;
    protected JPasswordField passwordField;
    protected JButton loginButton;
    private JButton forgotUsernameButton;
    private JButton forgotPasswordButton;
    private JTextField forgotPasswordUsername;
    private JButton enterUsername;
    private JLabel returnPassword;

    // Register components
    private JLabel registerLabel;
    private JLabel newUsernameLabel;
    private JLabel newPasswordLabel;
    protected JTextField newUsernameTextField;
    protected JPasswordField newPasswordField;
    protected JButton registerButton;
    private JButton forgotUsernameOrPasswordButton;



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
        forgotUsernameButton = new JButton("Forgot Username");
        forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordUsername = new JTextField(20);
        enterUsername = new JButton("Enter Username");

        // Initialize the register components
        registerLabel = new JLabel("Register");
        newUsernameLabel = new JLabel("New Username:");
        newPasswordLabel = new JLabel("New Password:");
        newUsernameTextField = new JTextField(20);
        newPasswordField = new JPasswordField(20);
        registerButton = new JButton("Register");
        forgotUsernameOrPasswordButton = new JButton("Forgot Username or Password");

        // Set up the login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(5, 2));
        loginPanel.add(loginLabel);
        loginPanel.add(new JLabel(""));
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(forgotUsernameButton);
        loginPanel.add(forgotPasswordButton);
        loginPanel.add(new JLabel(""));




        // Set up the register panel
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(4, 2));
        registerPanel.add(registerLabel);
        registerPanel.add(new JLabel(""));
        registerPanel.add(newUsernameLabel);
        registerPanel.add(newUsernameTextField);
        registerPanel.add(newPasswordLabel);
        registerPanel.add(newPasswordField);
        registerPanel.add(registerButton);
        registerPanel.add(forgotUsernameOrPasswordButton);

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

    /***
     * Action listeners for initial login screen
     *
     * @param e Action events made by user
     */
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
        else if (e.getSource() == forgotPasswordButton){
            loginPanel.add(forgotPasswordUsername);
            loginPanel.add(enterUsername);
            setVisible(true);

        }
        else if(e.getSource() == enterUsername){
            String username = usernameTextField.getText();
            String password = "";
            try {
                File file = new File("users.txt");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(",");
                    // if user/password = same
                    if (tokens.length == 2 && tokens[0].equals(username)) {
                        password = tokens[1];
                    }
                }
                scanner.close();

            } catch (IOException ex) {
                ex.printStackTrace();

            }
            returnPassword = new JLabel(password);
            loginPanel.add(returnPassword);
            setVisible(true);

        }
    }

    /**
     *
     * @param username username by user stored in file
     * @param password password made by user stored in text file.
     * @return username + password = correct -> go to main GUI
     */
    protected boolean checkCredentials(String username, String password) {
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