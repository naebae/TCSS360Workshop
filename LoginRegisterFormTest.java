import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Test cases created by Matthew Burgess
 */
public class LoginRegisterFormTest {

    @Test
    void testCheckCredentials() {
        LoginRegisterForm form = new LoginRegisterForm();
        // Add a test user to the users.txt file
        try {
            File file = new File("users.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write("testuser,testpassword\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Test a valid login
        Assertions.assertTrue(form.checkCredentials("testuser", "testpassword"));
        // Test an invalid login
        Assertions.assertFalse(form.checkCredentials("invaliduser", "invalidpassword"));
    }

    @Test
    void testActionPerformed() {
        LoginRegisterForm form = new LoginRegisterForm();
        // Test login with valid credentials
        form.usernameTextField.setText("testuser");
        form.passwordField.setText("testpassword");
        form.actionPerformed(new ActionEvent(form.loginButton, ActionEvent.ACTION_PERFORMED, ""));
        Assertions.assertFalse(form.isVisible()); // Check if the LoginRegisterForm window is closed
        // Test login with invalid credentials
        form.usernameTextField.setText("invaliduser");
        form.passwordField.setText("invalidpassword");
        form.actionPerformed(new ActionEvent(form.loginButton, ActionEvent.ACTION_PERFORMED, ""));
        // Test user registration
        form.newUsernameTextField.setText("newuser");
        form.newPasswordField.setText("newpassword");
        form.actionPerformed(new ActionEvent(form.registerButton, ActionEvent.ACTION_PERFORMED, ""));
    }
}
