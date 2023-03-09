import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectManagementPanel extends JPanel implements ActionListener {
    // Declare components
    private JButton newProjectButton, editProjectButton, budgetProjectButton, setReminderButton;
    private JTextField projectNameTextField, projectBudgetTextField, projectDueDateTextField;
    private JTextArea projectDescriptionTextArea;
    private JList<Project> projectList;
    private DefaultListModel<Project> projectListModel;

    // Constructor
    public ProjectManagementPanel() {
        // Set layout
        setLayout(new BorderLayout());

        // Create components
        JPanel buttonPanel = new JPanel(new FlowLayout());
        newProjectButton = new JButton("New Project");
        editProjectButton = new JButton("Edit Project");
        budgetProjectButton = new JButton("Budget Project");
        setReminderButton = new JButton("Set Reminder");
        buttonPanel.add(newProjectButton);
        buttonPanel.add(editProjectButton);
        buttonPanel.add(budgetProjectButton);
        buttonPanel.add(setReminderButton);

        JPanel projectDetailsPanel = new JPanel(new GridLayout(4, 2));
        JLabel projectNameLabel = new JLabel("Project Name:");
        projectNameTextField = new JTextField(20);
        JLabel projectBudgetLabel = new JLabel("Project Budget:");
        projectBudgetTextField = new JTextField(20);
        JLabel projectDueDateLabel = new JLabel("Due Date:");
        projectDueDateTextField = new JTextField(20);
        JLabel projectDescriptionLabel = new JLabel("Description:");
        projectDescriptionTextArea = new JTextArea(5, 20);
        projectDetailsPanel.add(projectNameLabel);
        projectDetailsPanel.add(projectNameTextField);
        projectDetailsPanel.add(projectBudgetLabel);
        projectDetailsPanel.add(projectBudgetTextField);
        projectDetailsPanel.add(projectDueDateLabel);
        projectDetailsPanel.add(projectDueDateTextField);
        projectDetailsPanel.add(projectDescriptionLabel);
        projectDetailsPanel.add(new JScrollPane(projectDescriptionTextArea));

        projectListModel = new DefaultListModel<Project>();
        projectList = new JList<Project>(projectListModel);
        projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane projectListScrollPane = new JScrollPane(projectList);

        // Add components
        add(buttonPanel, BorderLayout.NORTH);
        add(projectDetailsPanel, BorderLayout.CENTER);
        add(projectListScrollPane, BorderLayout.SOUTH);

        // Add action listeners
        newProjectButton.addActionListener(this);
        editProjectButton.addActionListener(this);
        budgetProjectButton.addActionListener(this);
        setReminderButton.addActionListener(this);
    }

    // Action listener for buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newProjectButton) {
            // Create new project
            String projectName = projectNameTextField.getText();
            String projectBudgetStr = projectBudgetTextField.getText();
            double projectBudget = 0.0;
            try {
                projectBudget = Double.parseDouble(projectBudgetStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid project budget", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String projectDueDateStr = projectDueDateTextField.getText();
            Date projectDueDate = null;
            try {
                projectDueDate = new SimpleDateFormat("MM/dd/yyyy").parse(projectDueDateStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid due date", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String projectDescription = projectDescriptionTextArea.getText();
            Project project = new Project(projectName, projectBudget, projectDueDate, projectDescription);
            projectListModel.addElement(project);
            projectNameTextField.setText("");
            projectBudgetTextField.setText("");
            projectDueDateTextField.setText("");
            projectDescriptionTextArea.setText("");
        } else if (e.getSource() == editProjectButton) {
            // Edit selected project
            int selectedIndex = projectList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a project to edit", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Project selectedProject = projectListModel.get(selectedIndex);
            String projectName = projectNameTextField.getText();
            String projectBudgetStr = projectBudgetTextField.getText();
            double projectBudget = 0.0;
            try {
                projectBudget = Double.parseDouble(projectBudgetStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid project budget", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String projectDueDateStr = projectDueDateTextField.getText();
            Date projectDueDate = null;
            try {
                projectDueDate = new SimpleDateFormat("MM/dd/yyyy").parse(projectDueDateStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid due date", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String projectDescription = projectDescriptionTextArea.getText();
            selectedProject.setName(projectName);
            selectedProject.setBudget(projectBudget);
            selectedProject.setDueDate(projectDueDate);
            selectedProject.setDescription(projectDescription);
            projectList.repaint();
        } else if (e.getSource() == budgetProjectButton) {
            // Calculate project budget
            int selectedIndex = projectList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a project to calculate budget", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Project selectedProject = projectListModel.get(selectedIndex);
            double budget = selectedProject.calculateBudget();
            JOptionPane.showMessageDialog(this, "The project budget is " + budget, "Budget Calculation", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == setReminderButton) {
            // Set reminder for project due date
            int selectedIndex = projectList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a project to set reminder", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Project selectedProject = projectListModel.get(selectedIndex);
            Date dueDate = selectedProject.getDueDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dueDate);
            int daysBefore = 7; // Set reminder 7 days before due date
            calendar.add(Calendar.DAY_OF_YEAR, -daysBefore);
            Date reminderDate = calendar.getTime();
            JOptionPane.showMessageDialog(this, "Reminder set for " + new SimpleDateFormat("MM/dd/yyyy").format(reminderDate), "Set Reminder", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void openProjectManagementPanel() {
    }

    // Project class (Still a work in progress and not overall functional)
    private class Project {
        private String name;
        private double budget;
        private Date dueDate;
        private String description;

        public Project(String name, double budget, Date dueDate, String description) {
            this.name = name;
            this.budget = budget;
            this.dueDate = dueDate;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBudget() {
            return budget;
        }

        public void setBudget(double budget) {
            this.budget = budget;
        }

        public Date getDueDate() {
            return dueDate;
        }

        public void setDueDate(Date dueDate) {
            this.dueDate = dueDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double calculateBudget() {
            // Some complex calculation to calculate the project budget
            // for not it will just return the budget and will not calculate
            return budget;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}