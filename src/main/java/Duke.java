import java.util.Scanner;

public class Duke {

    public static void main(String[] args){
        greetUser();
        runDuke();
        sayGoodbye();
    }
    public static void greetUser() {
        System.out.println("    Greetings, mortal! I am Balrog, the fiery demon.");
        System.out.println("    What foolish commands do you wish to utter?");
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
            userInput = in.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println(new DukeException("Enter a valid command").getErrorMessage());
            }
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
                    try {
                        Task.markTaskAsDone(Integer.parseInt(commandParts[1]));
                    } catch (NumberFormatException e) {
                        DukeException.handleGracefulError(DukeException.invalidTaskNumber());
                    }
                } else {
                    DukeException.handleGracefulError(DukeException.invalidTaskNumber());
                }
                break;
            case "unmark":
                if (commandParts.length > 1) {
                    try {
                        Task.unmarkTaskAsDone(Integer.parseInt(commandParts[1]));
                    } catch (NumberFormatException e) {
                        DukeException.handleGracefulError(DukeException.invalidTaskNumber());
                    }
                } else {
                    DukeException.handleGracefulError(DukeException.invalidTaskNumber());
                }
                break;
            case "todo":
                if (commandParts.length > 1) {
                    ToDo toDoTask = ToDo.createToDoFromCommand(command);
                    if (toDoTask != null) {
                        TaskListManager.addTask(toDoTask);
                    } else {
                        DukeException.handleGracefulError(DukeException.invalidToDoFormat());
                    }
                } else {
                    DukeException.handleGracefulError(DukeException.invalidToDoFormat());
                }
                break;
            case "deadline":
                if (commandParts.length > 1) {
                    Deadline deadlineTask = Deadline.createDeadlineFromCommand(command);
                    if (deadlineTask != null) {
                        TaskListManager.addTask(deadlineTask);
                    } else {
                        DukeException.handleGracefulError(DukeException.invalidDeadlineFormat());
                    }
                }
                else {
                    DukeException.handleGracefulError(DukeException.invalidDeadlineFormat());
                }
                break;
            case "event":
                if (commandParts.length > 1) {
                    Event eventTask = Event.createEventFromCommand(command);
                    if (eventTask != null) {
                        TaskListManager.addTask(eventTask);
                    } else {
                        DukeException.handleGracefulError(DukeException.invalidEventFormat());
                    }
                } else {
                    DukeException.handleGracefulError(DukeException.invalidEventFormat());
                }
                break;
            case "delete":
                if (commandParts.length > 1) {
                    try {
                        int taskNumber = Integer.parseInt(commandParts[1]);
                        Task.deleteTask(taskNumber, TaskListManager.taskList);
                    } catch (NumberFormatException e) {
                        DukeException.handleGracefulError(DukeException.invalidTaskNumber());
                    }
                } else {
                    DukeException.handleGracefulError(DukeException.invalidTaskNumber());
                }
                break;
            default:
                TaskListManager.addTask(new Task(command));
        }
    }
    public static void printHorizontalLine() {
        System.out.println("    -------------------------------------------------");
    }
}
