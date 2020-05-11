import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        // Read task list details
        System.out.println("\nWelcome to my TODO List\n");

        DataBaseOperations operation = new DataBaseOperations();
        ArrayList<ToDoListItem> toDoList = operation.getListData();
        int countOfStatusDone = operation.countOfCompletedTask();
        int countOfStatusToDo = toDoList.size() - countOfStatusDone;

        System.out.println("You have " + countOfStatusToDo + " tasks todo and " + countOfStatusDone + " tasks are done!\n");

        boolean BarsScreenDone = false;
        while (!BarsScreenDone) {

            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Pick an option:\n" +
                    "(1) Show Task List (by date or project)\n" +
                    "(2) Add, Update or Remove the Project\n" +
                    "(3) Add, Update or Remove Task\n" + //
                    "(0) Quit\n");
            String choice = myObj.nextLine();  // Read user input

            switch (choice) {
                case "1":
                    //Show Task List (by date or project);
                    operation.displayTaskDetails();
                    break;
                case "2":
                    operation.setProjectData();
                    break;
                case "3":
                    operation.setTaskData();
                    break;
                case "4":
                    //product.methodForCase4(proteinBarsList);
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
}
