import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    private static final String HORIZONTAL_LINE = "    -------------------------------------------------";

    public Ui() {
        Scanner scanner = new Scanner(System.in);
    }

    public static void greetUser() {
        System.out.println("    Greetings, mortal! I am Balrog, the fiery demon.");
        System.out.println("    What foolish commands do you wish to utter?");
        printHorizontalLine();
    }

    public static void sayGoodbye() {
        Storage.saveTasksToFile(TaskList.taskList);
        displayMessage("    Flee, mortal! Until our paths cross again!");
        printHorizontalLine();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            displayMessage("    Your feeble Task List is Empty!");
        } else {
            displayMessage("    ======= Scroll of Puny Tasks =======");
            for (int i = 0; i < taskList.size(); i++) {
                displayMessage("        " + (i + 1) + ". " + taskList.get(i));
            }
        }
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        displayMessage(HORIZONTAL_LINE);
    }
}
