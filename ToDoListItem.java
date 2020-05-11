import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This class is used for manipulation of ToDoList items
 */

public class ToDoListItem {
    private int taskId;
    private String taskName;
    private String projectName;
    private Date dueDate;
    private String status;

    public ToDoListItem(int taskId, String taskName, String projectName, Date dueDate, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.projectName = projectName;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDueDateAsString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(dueDate);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", dueDate=" + dueDate +
                ", status='" + status;
    }
}
