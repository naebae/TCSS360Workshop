import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculatorv01");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);

        JPanel panel = new JPanel();
        panel.setBounds(40,80,200,200);
        panel.setBackground(Color.lightGray);

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
                    try {
                        String content = "";
                        java.util.Scanner scanner = new java.util.Scanner(file);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            content += line + "\n";
                        }
                        scanner.close();
                        JOptionPane.showMessageDialog(null, content);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
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
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write("Hello, world!");
                        writer.close();
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

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        frame.add(panel);
        frame.setVisible(true);

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
}