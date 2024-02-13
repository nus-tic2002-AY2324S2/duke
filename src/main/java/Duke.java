import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];
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
    public static void sayGoodbye() {
        System.out.println("    Flee, mortal! Until our paths cross again!");
        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        System.out.println("-------------------------------------------------");
    }
    public static void addTaskToList(Task task) {
        if (taskCount < MAX_TASKS) {
            taskList[taskCount] = task;
            taskCount++;
            echoUserCommand(task.getDescription());
        } else {
            System.out.println("    The list of tasks is full! I shall not be burdened further.");
        }
    }
    public static void displayList() {
        System.out.println("======= Scroll of Puny Tasks =======");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i + 1) + ". " + taskList[i]);
        }
        printHorizontalLine();
    }
    public static void echoUserCommand(String command) {
        System.out.println("    A foolish command, mortal! You dare to utter: \"" + command + "\"");
    }
    public static void executeCommand(String command) {
        String[] commandParts = command.split(" ", 10);

        switch (commandParts[0].toLowerCase()) {
            case "list":
                displayList();
                break;
            case "mark":
                if (commandParts.length > 1) {
                    markTaskAsDone(Integer.parseInt(commandParts[1]));
                } else {
                    System.out.println("    Specify the task number to mark as done.");
                }
                break;
            case "unmark":
                if (commandParts.length > 1) {
                    unmarkTaskAsDone(Integer.parseInt(commandParts[1]));
                } else {
                    System.out.println("    Specify the task number to mark as not done.");
                }
                break;
            default:
                addTaskToList(new Task(command));
        }
    }
    public static void markTaskAsDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            taskList[taskNumber - 1].markAsDone();
            System.out.println("    Hmph! I've smitten this task from the list:\n      [" +
                                        taskList[taskNumber - 1].getStatusIcon() + "] " +
                                            taskList[taskNumber - 1].getDescription());
        } else {
            System.out.println("    Fool! That task number is beyond the realm of your pitiful list!");
        }
    }
    public static void unmarkTaskAsDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            taskList[taskNumber - 1].unmarkAsDone();
            System.out.println("    Bah! I've restored this task to its pathetic existence:\n    [" +
                                        taskList[taskNumber - 1].getStatusIcon() + "] " +
                                            taskList[taskNumber - 1].getDescription());
        } else {
            System.out.println("    You dare invoke the invalid task number? Pathetic!");
        }
    }
    public static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= taskCount;
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
            executeCommand(userInput);
            printHorizontalLine();
        } while (!userInput.equalsIgnoreCase("bye"));
    }
}
