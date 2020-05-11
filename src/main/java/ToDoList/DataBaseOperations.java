package ToDoList;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * This is the main operation class of ToDoList
 * It has three supporting classes Task, Project and ToDoListItem
 * This class access the task data and corresponding to its projectId
 * fetch the project name from project data and show to the customer
 */



public class DataBaseOperations {
    // method to read a csv file
    // and return BufferedReader
    // if file not found, create a new file
    BufferedReader readCSVFIle(String fileName) throws IOException {
        File projectData = new File(fileName);
        BufferedReader csvReader = null;
        if (!projectData.exists()) {
            System.out.println("No project found, please create a project to proceed.");
            projectData.createNewFile();

        } else {
            FileReader fr = new FileReader(projectData);
            csvReader = new BufferedReader(fr);
        }

        return csvReader;
    }

    // method to fetch project csv file data and
    // make a project class object
    // and return ArrayList of project
    ArrayList<Project> accessProjectData() throws IOException {
        String projectFile = "Projects.csv";
        BufferedReader csvReaderProject = readCSVFIle(projectFile);
        csvReaderProject.readLine();
        String rowProject;
        ArrayList<Project> projectList = new ArrayList<>();
        while ((rowProject = csvReaderProject.readLine()) != null) {
            String[] eachProject = rowProject.split(",");
            int id = Integer.parseInt(eachProject[0]);
            String projectName = eachProject[1];
            Project eachLine = new Project(id, projectName);
            projectList.add(eachLine);
        }
        return projectList;
    }

    // method to fetch task csv file data and
    // make a task class object
    // and return ArrayList of task
    ArrayList<Task> accessTaskData() throws IOException, ParseException {
        String taskFile = "Tasks.csv";
        BufferedReader csvReaderTask = readCSVFIle(taskFile);
        csvReaderTask.readLine();
        String rowTask;
        ArrayList<Task> taskList = new ArrayList<>();
        while ((rowTask = csvReaderTask.readLine()) != null) {
            String[] eachTask = rowTask.split(",");
            int taskId = Integer.parseInt(eachTask[0]);
            String taskName = eachTask[1];
            int projectId = Integer.parseInt(eachTask[2]);
            Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(eachTask[3]);
            String status = eachTask[4];
            Task task = new Task(taskId, taskName, projectId, dueDate, status);
            taskList.add(task);
        }
        return taskList;
    }

    // method to make TODO List item from task and project list
    ArrayList<ToDoListItem> getListData () throws IOException, ParseException {
        ArrayList<Task> taskList = accessTaskData();
        ArrayList<Project> projectList = accessProjectData();
        ArrayList<ToDoListItem> toDoList = new ArrayList<>();

        for(Task task : taskList) {

            int taskId = task.getTaskId();
            String taskName = task.getTaskName();
            int projectId = task.getProjectId();
            Date dueDate = task.getDueDate();
            String status = task.getStatus();
            if(status.equals("true")){
                status = "done";
            } else{
                status = "to do";
            }
            String projectName = new String();
            for(Project project : projectList) {
                int id = project.getId();
                if(id == projectId){
                    projectName = project.getName();
                }
            }
            ToDoListItem eachRow = new ToDoListItem(taskId, taskName, projectName, dueDate, status);
            toDoList.add(eachRow);
        }
        //toDoList.forEach(eachRow -> System.out.println(eachRow.toString()));
        return toDoList;
    }

    // method to display the ToDo list ArrayList
    void display(ArrayList<ToDoListItem> toDoList){
        System.out.println("Task Id\t\t\t\t"+ "Task Name \t\t"+ "   Project Name \t\t"+ "  Due Date \t"+ "\tStatus");
        for(ToDoListItem eachItem : toDoList){
            System.out.println(
                    String.format("%5d", eachItem.getTaskId()) +
                    " "+
                    String.format("%25s", eachItem.getTaskName()) +
                    "\t\t"+
                    String.format("%15s", eachItem.getProjectName()) +
                    "\t\t\t"+
                    String.format("%10s", eachItem.getDueDateAsString()) +
                    "\t\t"+
                    String.format("%5s", eachItem.getStatus()));
        }
    }

    // method to count completed task from a
    // ToDo list ArrayList
    int countOfCompletedTask() throws IOException, ParseException {
        ArrayList<ToDoListItem> toDoList = getListData();

        int countOfStatusDone = 0;
        for(ToDoListItem  eachTask : toDoList){
            if (eachTask.getStatus().equals("done")){
                countOfStatusDone++;
            }
        }
        return countOfStatusDone;
    }

    // method to display the task details
    // with the use of display method
    // while accessing the ToDo list items
    void displayTaskDetails() throws IOException, ParseException {

        ArrayList<ToDoListItem> toDoList = getListData();
        boolean BarsScreenDone = false;

        while (!BarsScreenDone) {

            Scanner myObj = new Scanner(System.in);
            System.out.println("Pick an option:\n" +
                    "(1) Show Task List by date\n" +
                    "(2) Show Task List by project\n" +
                    "(0) Return to welcome screen\n");
            String choice = myObj.nextLine();

            switch (choice) {
                case "1":
                    String sortFor = "date";
                    sortDisplay(toDoList, sortFor);
                    break;
                case "2":
                    sortFor = "project";
                    sortDisplay(toDoList, sortFor);
                    break;
                case "0":
                    BarsScreenDone = true;
                    break;
                default:
                    System.out.println("Invalid Selection!");
                    break;
            }
            display(toDoList);
        }

    }


    // method to add, update or remove project
    void setProjectData() throws IOException {

        ArrayList<Project> projectList = accessProjectData();
        System.out.println("There are total " + projectList.size() + " projects. Which is given below:");
        System.out.println("id" + "\tname");
        projectList.forEach(eachRow -> System.out.println(eachRow.toString()));

        boolean BarsScreenDone = false;
        while (!BarsScreenDone) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Pick an option:\n" +
                    "(1) Add the Project\n" +
                    "(2) Update the Project\n" +
                    "(3) Delete the Project\n" +
                    "(0) Return to welcome screen");

            String choice = myObj.nextLine();

            switch (choice) {
                case "1":
                    projectList = addProject(projectList);

                    break;
                case "2":
                    projectList = updateProject(projectList);
                    break;
                case "3":
                    projectList = removeProject(projectList);
                    break;
                case "0":
                    BarsScreenDone = true;
                    break;
                default:
                    System.out.println("Invalid Selection!");
                    break;
            }
        }
    }

    // method to add a new project
    // and write to the project csv file
    ArrayList<Project> addProject(ArrayList<Project> projectList) throws IOException {
        System.out.println("Please enter the project name ");
        Scanner myObj = new Scanner(System.in);
        String name = myObj.nextLine();

        if (name.equals("")){
            System.out.println("Please enter the project name to add");
            return projectList;
        }
        else{
            int id = 0;
            for(Project project: projectList){
                id = project.getId()+1;
            }
            Project projectToAdd = new Project(id, name);
            projectList.add(projectToAdd);

            writeProjectCSV(projectList);
            return projectList;
        }
    }

    // method to update a project
    // and write to the project csv file
    ArrayList<Project> updateProject(ArrayList<Project> projectList) throws IOException {
        System.out.println("Please enter the project id to update");
        Scanner myObj = new Scanner(System.in);
        int id = 0;
        try {
            id = Integer.parseInt(myObj.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct id!");
        }
        int count = 0;
        for(Project project: projectList){
            if(id == project.getId()){
                System.out.println("The project is: " + project.getName());
                System.out.println("Please update the project name: ");
                String name = myObj.nextLine();
                project.setName(name);
                count++;
            }
        }
        if(count == 0){
            System.out.println("Please enter the correct project id");
        }
        else{
            writeProjectCSV(projectList);
        }
        return projectList;
    }

    // method to delete a project
    // and write to the project csv file
    ArrayList<Project> removeProject(ArrayList<Project> projectList) throws IOException {
        System.out.println("Please enter the project id to remove from the list");
        Scanner myObj = new Scanner(System.in);
        int id = 0;
        try {
            id = Integer.parseInt(myObj.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct id!");
        }
        Project projectToBeRemoved = null;
        int count = 0;
        for(Project project: projectList){
            if(id == project.getId()){
                System.out.println("The project: " + project.getName() + " has been deleted from the list.");
                projectToBeRemoved = project;
                count++;
            }
        }
        if(count == 0){
            System.out.println("Please enter the correct project id");
        }
        else{
            projectList.remove(projectToBeRemoved);
            writeProjectCSV(projectList);
        }
        return projectList;
    }

    // method to write into the project csv file
    void writeProjectCSV(ArrayList<Project> projectList) throws IOException {
        // add project to the file
        FileWriter csvWriter = new FileWriter("Projects.csv");
        csvWriter.append("id");
        csvWriter.append(",");
        csvWriter.append("name");
        csvWriter.append("\n");

        for(Project project : projectList){
            //for (List<String> rowData : rows) {
            csvWriter.append(String.join(",", project.getId()+","+project.getName()));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    // method to add, update or remove Task
    void setTaskData() throws IOException, ParseException {

        ArrayList<ToDoListItem> toDoList = getListData();

        System.out.println("There are total " + toDoList.size() + " tasks in this list, which is given below: ");
        //toDoList.forEach(eachRow -> System.out.println(eachRow.toString()));
        display(toDoList);

        boolean BarsScreenDone = false;
        while (!BarsScreenDone) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Pick an option:\n" +
                    "(1) Add the Task\n" +
                    "(2) Update the Task\n" +
                    "(3) Delete the Task\n" +
                    "(0) Return to welcome screen");

            String choice = myObj.nextLine();

            switch (choice) {
                case "1":
                    addTask();

                    break;
                case "2":
                    updateTask();
                    break;
                case "3":
                    removeTask();
                    break;
                case "0":
                    BarsScreenDone = true;
                    break;
                default:
                    System.out.println("Invalid Selection!");
                    break;
            }
        }
    }

    // method to add a new task
    // and write to the task csv file
    void addTask() throws IOException, ParseException {
        System.out.println("Please enter the task name to be added");
        Scanner myObj = new Scanner(System.in);
        String taskName = myObj.nextLine();
        if (taskName.equals("")){
            System.out.println("Please enter the task name to add");
        }
        else {

            System.out.println("Please enter the due date in the format dd/MM/yyyy only");
            Date dueDate = null;
            final String DATE_PATTERN = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern(DATE_PATTERN);
            try
            {
                /**
                 * Make is strict validation [SHOULD THROW ERROR]
                 * */
                sdf.setLenient(false);

                //Strict conversion validates the date correctly
                dueDate = sdf.parse(myObj.nextLine());
                System.out.println(dueDate);
            }
            catch(ParseException e)
            {
                System.out.println("You have entered the incorrect date format. I am taking you to the previous menu");
                return;
            }

            ArrayList<Project> projectList = accessProjectData();
            System.out.println("There are total " + projectList.size() + " projects. Which is given below:");
            projectList.forEach(eachRow -> System.out.println(eachRow.toString()));

            System.out.println("Please enter the project id for this task from the list given above");
            int projectId = 0;
            try {
                projectId = Integer.parseInt(myObj.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter the correct id!");
            }

            ArrayList<Task> taskList = accessTaskData();

            int taskId = 0;
            for (Task eachTask : taskList) {
                taskId = eachTask.getTaskId() + 1;
            }
            int count = 0;
            for (Project project : projectList) {
                if (project.getId() == projectId) {
                    String status = "false";
                    Task newTask = new Task(taskId, taskName, projectId, dueDate, status);
                    taskList.add(newTask);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Please enter the correct project id");
            } else {
                writeTaskCSV(taskList);
            }
        }
    }

    // method to update a task
    // and write to the task csv file
    void updateTask() throws IOException, ParseException {
        System.out.println("Please enter the task id to be updated");
        Scanner myObj = new Scanner(System.in);
        int taskId = 0;
        try {
            taskId = Integer.parseInt(myObj.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct id!");
        }

        ArrayList<Task> taskList = accessTaskData();

        int count = 0;
        for(Task  eachTask : taskList){
            if(eachTask.getTaskId() == taskId){
                ArrayList<ToDoListItem> oneItem = new ArrayList<>();
                String status = eachTask.getStatus();
                if(status.equals("true")){
                    status = "done";
                } else{
                    status = "to do";
                }
                ToDoListItem oneListItem = new ToDoListItem(eachTask.getTaskId(), eachTask.getTaskName(), "**",
                        eachTask.getDueDate(), status);
                oneItem.add(oneListItem);
                // display the task detail which is going to be updated
                display(oneItem);
                count++;
                System.out.println("Please select an option\n" +
                        "(1) Update task name\n" +
                        "(2) Update due date\n" +
                        "(3) Update status\n");

                int choice = 0;
                try {
                    choice = Integer.parseInt(myObj.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please select the correct option!");
                }

                if(choice == 1){
                    System.out.println("Please enter new task name");
                    String newTaskName = myObj.nextLine();

                    if (newTaskName.equals("")){
                        System.out.println("Please enter the task name to add");
                    }
                    else {
                        eachTask.setTaskName(newTaskName);
                    }
                }
                if(choice == 2){
                    System.out.println("Please enter new due date in the format dd/MM/yyyy only");
                    Date dueDate = null;
                    final String DATE_PATTERN = "dd/MM/yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyPattern(DATE_PATTERN);
                    try
                    {
                        /**
                         * Make is strict validation [SHOULD THROW ERROR]
                         * */
                        sdf.setLenient(false);

                        //Strict conversion validates the date correctly
                        dueDate = sdf.parse(myObj.nextLine());
                        System.out.println(dueDate);
                    }
                    catch(ParseException e)
                    {
                        System.out.println("You have entered the incorrect date format. I am taking you to the previous menu.");
                        return;
                    }
                    eachTask.setDueDate(dueDate);
                }
                if(choice == 3){
                    System.out.println("Please enter new status: 'done' or 'to do'");
                    String newStatus = myObj.nextLine();
                    if(newStatus.equalsIgnoreCase("done")){
                        eachTask.setStatus("true");
                    }
                    else{
                        if(newStatus.equalsIgnoreCase("to do")){
                            eachTask.setStatus("false");
                        }
                        else{
                            System.out.println("You have entered the incorrect status. I am taking you to the previous menu.");
                        }
                    }
                }
            }
        }
        if (count == 0){
            System.out.println("Please selected the correct task id");
        }
        else{
            writeTaskCSV(taskList);
        }
    }

    // method to delete a task
    // and write to the task csv file
    void removeTask() throws IOException, ParseException {

        System.out.println("Please enter the task id to be deleted");
        Scanner myObj = new Scanner(System.in);
        int taskId = 0;
        try {
            taskId = Integer.parseInt(myObj.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct id!");
        }

        ArrayList<Task> taskList = accessTaskData();
        Task taskToBeRemoved = null;
        int count = 0;
        for(Task  eachTask : taskList){
            if(eachTask.getTaskId() == taskId){
                System.out.println("The task: " + eachTask.getTaskName() + " has been deleted from the list.");
                taskToBeRemoved = eachTask;
                count++;
            }
        }
        if (count == 0){
            System.out.println("Please selected the correct task id");
        }
        else {
            taskList.remove(taskToBeRemoved);
            writeTaskCSV(taskList);
        }
    }

    // method to write task into task csv file
    void writeTaskCSV(ArrayList<Task> taskList) throws IOException {
        // add tasks to the file
        FileWriter csvWriter = new FileWriter("Tasks.csv");
        csvWriter.append("taskId");
        csvWriter.append(",");
        csvWriter.append("taskName");
        csvWriter.append(",");
        csvWriter.append("projectId");
        csvWriter.append(",");
        csvWriter.append("dueDate");
        csvWriter.append(",");
        csvWriter.append("status");
        csvWriter.append("\n");

        for(Task task : taskList){
            csvWriter.append(String.join(",", task.getTaskId()+","+task.getTaskName()+
                    ","+task.getProjectId()+","+task.getDueDateAsString()+","+task.getStatus()));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    // method for sorting the task details
    public void sortDisplay(ArrayList<ToDoListItem> toDoList, String sortFor){
        System.out.println("");
        for(int k=0; k< toDoList.size(); k++){
            int maxIndex = getHighestIndex(toDoList, k, sortFor);
            ToDoListItem listItem = toDoList.get(k);
            ToDoListItem maxListItem = toDoList.get(maxIndex);
            toDoList.set(k, maxListItem);
            toDoList.set(maxIndex, listItem);
        }
    }

    public int getHighestIndex(ArrayList<ToDoListItem> toDoList, int from, String sortFor) {
        int maxIndex = from;
        for(int i=from; i< toDoList.size(); i++){
            if(sortFor.equals("date")){
                if(toDoList.get(i).getDueDate().compareTo(toDoList.get(maxIndex).getDueDate()) == -1) {
                    maxIndex = i;
                }
            }
            if(sortFor.equals("project")){
                if(toDoList.get(i).getProjectName().compareToIgnoreCase(toDoList.get(maxIndex).getProjectName()) == -1){
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }
}
