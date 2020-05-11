# User Manual for Todo list application

Please read through to document to under how the application works.

## Welcome screen

Once you open the application you will see below options:

```
Welcome to my TODO List

You have 3 tasks todo and 2 tasks are done!

Pick an option:
(1) Show Task List (by date or project)
(2) Add, Update or Remove the Project
(3) Add, Update or Remove Task
(0) Quit
```

You can observe that welcome screen gives you the summary of tasks which are complete or to be done.

By choosing option 1, you can list each task sorted either by date or project.
By choosing option 2, you can 
1. add new project 
1. update existing project or 
1. remove existing project

By choosing option 3, you can 
1. add new task 
1. update existing task or 
1. remove existing task

Select 0 to quit the application.


## Show task list

When you select option 1 in welcome screen then you will see below option to sort the tasks list 

```
Pick an option:
(1) Show Task List by date
(2) Show Task List by project
(0) Return to welcome screen
```

When select option 1 or 2 then application will show you the details of each task in a proper tabular format.
*Shows task list sorted by project*
```

Task Id	          Task Name 		   Project Name 		  Due Date 		Status
    2           ”Zumba class”              gym training			01/04/2020		to do
    3           ”Dentist”                  appointments			03/04/2020		 done
    4            ”ATC”                     gym training			06/04/2020		 done
```

You can select 0 to go back to welcome screen.


## Add, Update or Remove the Project

When you select option 2 in welcome screen then you will see the list of projects already saved in application.
You can now either 
* add new project or 
* update/delete existing project.

```
There are total 5 projects. Which is given below:
id	name
1	shopping
2	gym training
Pick an option:
(1) Add the Project
(2) Update the Project
(3) Delete the Project
(0) Return to welcome screen
```

### Add the project

When you select option to add project then you will see below screen to add the project name.
You can then add the project name (*My Awesome new project*) and press enter.
```
Please enter the project name 
=> My Awesome new project
```

You will return to previous screen.

### Update the project

When you select option to update the project then you will be asked to enter project Id.
You can then enter the project Id (*7*) and press enter.
Then it will display the existing project name and then will ask to update the name.
```
Please enter the project id to update
=> 7
The project is: My Awesome new project
Please update the project name: 
=> My Most Awesome new project
```

### Delete the Project
When you select option to delete the project then you will be asked to enter project Id.
You can then enter the project Id (*7*) and press enter. You will see the project name which just got deleted.

```
Please enter the project id to remove from the list
=> 7
The project: My Most Awesome new project has been deleted from the list.
```
You will return to previous screen. Press 0 to return to welcome screen.


## Add, Update or Remove Task

When you select option 3 in welcome screen then you will see the list of tasks already saved in application.

```
Task Id	          Task Name 		   Project Name 		  Due Date 		Status
    2           ”Zumba class”              gym training			01/04/2020		to do
    3           ”Dentist”                  appointments			03/04/2020		 done
    4            ”ATC”                     gym training			06/04/2020		 done
```

Now you can now either 
* Add the new task
* Update or delete the existing task

```
Pick an option:
(1) Add the Task
(2) Update the Task
(3) Delete the Task
(0) Return to welcome screen
```

### Add the Task

When you select option to add the task then you will see below screen to add the task details.
You can then add the task name (*my wonderful new task*) and press enter.
Then you have to enter the due date in dd/MM/yyyy format.
And in the end you have to select the project under which the task belongs to.
```
Please enter the task name to be added
=> my wonderful new task
Please enter the due date in the format dd/MM/yyyy
=> 30/04/2020
There are total 6 projects. Which is given below:
1	shopping
2	gym training
3	appointments
5	cooking
6	cleaning
7	My Awesome new project
Please enter the project id for this task from the list given above
=> 7
```

You will return to previous screen.

### Update the task

When you select option to update the task then you will be asked to enter task Id.
You can then enter the task Id (*6*) and press enter.
Then it will display the existing task details and then will ask to update the either name, due date or status.
```
Please enter the task id to be updated
=> 6
Task Id				Task Name 		   Project Name 		  Due Date 		Status
    6     my wonderful new task		             **			30/04/2020		to do
Please select an option
(1) Update task name
(2) Update due date
(3) Update status

=> 3
Please enter new status: 'done' or 'to do'
=> done
```
Once you finish updating the task details you will be returned to previous task options screen.

### Delete the task
When you select option to delete the task then you will be asked to enter task Id.
You can then enter the task Id (*6*) and press enter. You will see the task name which just got deleted.

```
Please enter the task id to be deleted
6
The task: my wonderful new task has been deleted from the list.
```
You will return to previous screen. Press 0 to return to welcome screen.


You can select 0 to quit the application when you are at welcome screen.