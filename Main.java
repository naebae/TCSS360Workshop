import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/***
 * Main GUI controller panel
 *
 *  Created by Matthew Burgess
 */
public class Main {

    public static void saveProjects(ProjectManagementPanel projectManagementPanel, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Replace Project class with String, and change the data accordingly
            DefaultListModel<ProjectManagementPanel.Project> projectListModel = projectManagementPanel.getProjectListModel();
            int size = projectListModel.getSize();
            writer.write(size + "\n");
            for (int i = 0; i < size; i++) {
                ProjectManagementPanel.Project project = projectListModel.get(i);
                writer.write(project + "\n");
            }
        }
    }

    public static void main(String[] args) {

        // Shows the login/register form
        LoginRegisterForm loginRegisterForm = new LoginRegisterForm();
        loginRegisterForm.setVisible(true);

        // Wait for the user to close the form
        while (loginRegisterForm.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Create the main frame
        JFrame frame = new JFrame("Project Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        // Create an instance of ProjectManagementPanel
        ProjectManagementPanel projectManagementPanel = new ProjectManagementPanel();
        // Add the project management panel to the frame's content pane
        frame.getContentPane().add(projectManagementPanel, BorderLayout.CENTER);


        JPanel panel = new JPanel();
        panel.setBounds(40,80,200,200);
        panel.setBackground(Color.white);

        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            // Log out the current user

            // Close the main GUI and show the login/register form again
            frame.dispose();
            loginRegisterForm.setVisible(true);
        });
        // Create an instance of ProjectManagementPanel

        // Add the panel to the frame's content pane
        frame.getContentPane().add(logoutButton, BorderLayout.NORTH);

        // Set the frame visible
        frame.setVisible(true);

        // Open the project management panel
        projectManagementPanel.openProjectManagementPanel();

        JButton b1 = new JButton("About");
        b1.setBounds(50,100,80,30);
        b1.setBackground(Color.yellow);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                About about = new About();
            }
        });

        JButton b2 = new JButton("Open");
        b2.setBounds(140,100,80,30);
        b2.setBackground(Color.green);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Text files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    // read file and store the settings/data

                    JOptionPane.showMessageDialog(null, "File imported successfully.");
                }
            }
        });


        JButton b3 = new JButton("Save");
        b3.setBounds(230,100,80,30);
        b3.setBackground(Color.red);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Text files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showSaveDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        saveProjects(projectManagementPanel, file);
                        JOptionPane.showMessageDialog(null, "File saved successfully.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JButton b4 = new JButton("Reminders");
        b4.setBounds(320,100,100,30);
        b4.setBackground(Color.blue);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame reminderFrame = new JFrame("Reminders");
                reminderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                reminderFrame.setSize(500, 500);

                JPanel reminderPanel = new JPanel();
                reminderPanel.setBackground(Color.white);

                JLabel reminderLabel = new JLabel("Add a reminder:");
                JTextField reminderTextField = new JTextField(20);
                JButton addReminderButton = new JButton("Add Reminder");
                JTextArea reminderTextArea = new JTextArea();
                reminderTextArea.setEditable(false);

                addReminderButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String reminder = reminderTextField.getText();
                        if (reminder.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Reminder cannot be empty.");
                        } else {
                            reminderTextArea.append("- " + reminder + "\n");
                            reminderTextField.setText("");
                        }
                    }
                });

                reminderPanel.add(reminderLabel);
                reminderPanel.add(reminderTextField);
                reminderPanel.add(addReminderButton);
                reminderPanel.add(reminderTextArea);

                reminderFrame.add(reminderPanel);
                reminderFrame.setVisible(true);
            }
        });


        JButton b5 = new JButton("Organize");
        b5.setBounds(320,100,80,30);
        b5.setBackground(Color.green);
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPopupMenu popup = new JPopupMenu();
                JMenuItem menuItem1 = new JMenuItem("Create new folder");
                JMenuItem menuItem2 = new JMenuItem("Rename folder");
                JMenuItem menuItem3 = new JMenuItem("Delete folder");
                JMenuItem menuItem4 = new JMenuItem("Create new file");
                JMenuItem menuItem5 = new JMenuItem("Rename file");
                JMenuItem menuItem6 = new JMenuItem("Delete file");

                popup.add(menuItem1);
                popup.add(menuItem2);
                popup.add(menuItem3);
                popup.addSeparator();
                popup.add(menuItem4);
                popup.add(menuItem5);
                popup.add(menuItem6);

                popup.show(b5, b5.getWidth()/2, b5.getHeight()/2);
            }
        });

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void setVisible(boolean b) {
    }
}