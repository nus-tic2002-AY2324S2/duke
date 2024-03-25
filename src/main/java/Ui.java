import java.util.Scanner;
<<<<<<< Updated upstream

public class Ui {

=======
import java.util.Collections;

/**
 * Handles user interface operations for the Duke application.
 */
public class Ui {

    /**
     * Displays a welcome message when the application starts.
     */
>>>>>>> Stashed changes
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Maverick");
        System.out.println("What can I do for you?");
    }

<<<<<<< Updated upstream
=======
    /**
     * Retrieves user input from the console.
     *
     * @return The user input as a string.
     */
>>>>>>> Stashed changes
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

<<<<<<< Updated upstream
    public void showTaskList(TaskList taskList) {
=======
    /**
     * Displays the list of tasks.w
     *
     * @param taskList The task list to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        taskList.tasks.sort(new TaskComparator());
>>>>>>> Stashed changes
        if (taskList.size() == 0) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, taskList.get(i));
            }
        }
    }

<<<<<<< Updated upstream
=======
    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task        The task that has been added.
     * @param totalTasks  The total number of tasks after adding the new task.
     */
>>>>>>> Stashed changes
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", totalTasks);
    }

<<<<<<< Updated upstream
=======
    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
>>>>>>> Stashed changes
    public void showTaskMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

<<<<<<< Updated upstream
=======
    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task           The task that has been deleted.
     * @param remainingTasks The number of tasks remaining after deleting the task.
     */
>>>>>>> Stashed changes
    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.printf("Now you have %d tasks in the list.\n", remainingTasks);
    }

<<<<<<< Updated upstream
=======
    /**
     * Displays a goodbye message when the application exits.
     */
>>>>>>> Stashed changes
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

<<<<<<< Updated upstream
=======
    /**
     * Displays a message indicating that the user inputted an invalid command.
     */
>>>>>>> Stashed changes
    public void showInvalidCommandMessage() {
        System.out.println("Sorry, I don't understand that command.");
    }

<<<<<<< Updated upstream
=======
    /**
     * Displays a message indicating that a task description already exists in the list.
     */
>>>>>>> Stashed changes
    public void showDuplicateMessage() {
        System.out.println("Description already exists in the list");
    }


<<<<<<< Updated upstream
}

=======

}
>>>>>>> Stashed changes
