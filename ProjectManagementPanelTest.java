import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by Matthew Burgess
 */
class ProjectManagementPanelTest {
    private ProjectManagementPanel.Project project;

    @BeforeEach
    void setUp() {
        project = new ProjectManagementPanel.Project("Test Project");
    }

    @Test
    void getName() {
        assertEquals("Test Project", project.getName());
    }

    @Test
    void setName() {
        project.setName("New Project Name");
        assertEquals("New Project Name", project.getName());
    }

    @Test
    void getBudget() {
        assertEquals(0.0, project.getBudget());
    }

    @Test
    void setBudget() {
        project.setBudget(1000.0);
        assertEquals(1000.0, project.getBudget());
    }

    @Test
    void getDueDate() {
        assertNull(project.getDueDate());
    }

    @Test
    void setDueDate() throws ParseException {
        Date dueDate = new SimpleDateFormat("MM/dd/yyyy").parse("03/30/2023");
        project.setDueDate(dueDate);
        assertEquals(dueDate, project.getDueDate());
    }

    @Test
    void getDescription() {
        assertNull(project.getDescription());
    }

    @Test
    void setDescription() {
        project.setDescription("This is a test project.");
        assertEquals("This is a test project.", project.getDescription());
    }

    @Test
    void calculateBudget() {
        project.setBudget(500.0);
        assertEquals(500.0, project.calculateBudget());
    }
}
