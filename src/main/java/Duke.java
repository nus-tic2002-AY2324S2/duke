import java.util.Scanner;

public class Duke {

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
    public static void runDuke() {
        String userInput;
        Scanner in = new Scanner(System.in);

        do {
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                continue;
            } else if (userInput.equalsIgnoreCase("list")) {
                TaskListManager.displayList();
                continue;
            }
            executeCommand(userInput);
            printHorizontalLine();
        } while (!userInput.equalsIgnoreCase("bye"));
    }
    public static void executeCommand(String command) {
        String[] commandParts = command.split(" ", 2);

        switch (commandParts[0].toLowerCase()) {
            case "list":
                TaskListManager.displayList();
                break;
            case "mark":
                if (commandParts.length > 1) {
                    Task.markTaskAsDone(Integer.parseInt(commandParts[1]));
                } else {
                    System.out.println("    Specify the task number to mark as done.");
                }
                break;
            case "unmark":
                if (commandParts.length > 1) {
                    Task.unmarkTaskAsDone(Integer.parseInt(commandParts[1]));
                } else {
                    System.out.println("    Specify the task number to mark as not done.");
                }
                break;
            case "todo":
                if (commandParts.length > 1) {
                    String todoDescription = command.substring(commandParts[0].length()).trim();
                    TaskListManager.addTask(new ToDo(todoDescription));
                } else {
                    System.out.println("    Specify the task ToDo.");
                }
                break;
            default:
                TaskListManager.addTask(new Task(command));
        }
    }
    public static void printHorizontalLine() {
        System.out.println("-------------------------------------------------");
    }
}
