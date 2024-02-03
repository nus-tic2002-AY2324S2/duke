import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static String[] taskList = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        greetUser();
        runDuke();
        sayGoodbye();
    }
    public static void greetUser() {
        System.out.println("Greetings, mortal! I am Balrog, the fiery demon.");
        System.out.println("What foolish commands do you wish to utter?");
        printHorizontalLine();
    }
    public static void runDuke() {
        String userInput;
        Scanner in = new Scanner(System.in);

        do {
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                continue;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayList();
                continue;
            }
            echoUserInput(userInput);
            addInputToList(userInput);
            printHorizontalLine();
        } while (!userInput.equalsIgnoreCase("bye"));

    }
    public static void sayGoodbye() {
        System.out.println("    Flee, mortal! Until our paths cross again!");
        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        System.out.println("--------------------------------");
    }
    public static void echoUserInput(String input) {
        System.out.println("     A feeble command, mortal! You dare to decree: \"" + input + "\"");
    }
    public static void addInputToList(String input) {
        if (taskCount < MAX_TASKS) {
            taskList[taskCount] = input;
            taskCount++;
        } else {
            System.out.println("    The list of tasks is full! I shall not be burdened further.");
        }
    }
    public static void displayList() {
        System.out.println("=== Scroll of Puny Tasks ===");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i+1) + ". " + taskList[i]);
        }
        printHorizontalLine();
    }
}
