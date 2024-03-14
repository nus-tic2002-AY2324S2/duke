import java.util.Scanner;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Maverick");
        System.out.println("What can I do for you?");
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, taskList.get(i));
            }
        }
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", totalTasks);
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.printf("Now you have %d tasks in the list.\n", remainingTasks);
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showInvalidCommandMessage() {
        System.out.println("Sorry, I don't understand that command.");
    }

    public void showDuplicateMessage() {
        System.out.println("Description already exists in the list");
    }


}

