package ToDoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * This class used to access the Task data
 * and manipulation of the task data
 */

public class Task {
    private int taskId;
    private String taskName;
    private int projectId;
    private Date dueDate;
    private String status;

    public Task(int taskId, String taskName, int projectId, Date dueDate, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.projectId = projectId;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getDueDateAsString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(dueDate);
    }
    public Date getDueDate() throws ParseException {
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
                ", projectId=" + projectId +
                ", dueDate=" + dueDate +
                ", status='" + status;
    }
}
