package ToDoList;

import org.junit.Test;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestCSVFileOperation {
    @Test
    public void testProjectData() throws IOException {
        DataBaseOperations operation = new DataBaseOperations();
        ArrayList<Project> projectList = operation.accessProjectData();
        int result = projectList.size();
        assertEquals(7, result);
    }
    @Test
    public void testTaskData() throws IOException, ParseException {
        DataBaseOperations operation = new DataBaseOperations();
        ArrayList<Task> taskList = operation.accessTaskData();
        int result = taskList.size();
        assertEquals(6, result);
    }

    @Test
    public void testOfCountOfCompletedTask() throws IOException, ParseException {
        DataBaseOperations operation = new DataBaseOperations();
        ArrayList<ToDoListItem> toDoList = operation.getListData();

        int result = operation.countOfCompletedTask();
        assertEquals(2, result);
    }
}
