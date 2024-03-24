import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void printUnderScoreLine() {
        char[] underScoreLine = new char[50];
        Arrays.fill(underScoreLine, '_');
        System.out.println(underScoreLine);
    }

    public static void printBye() {
        printUnderScoreLine();
        System.out.println("Bye. Hope to see you again meow!");
        printUnderScoreLine();
    }

    public static void addTask(Task task) {
        boolean isDuplicate = false;
        for (Task t : tasks) {
            if (t.getDescription().equals(task.getDescription())) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            tasks.add(task);
            System.out.println("Meow, added : " + task);
            printTaskCount();
            printUnderScoreLine();
        } else {
            System.out.println("Meow, this task already exists meow!");
            printUnderScoreLine();
        }
    }

    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printTaskCount();
        printUnderScoreLine();
    }

    public static void deleteTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            System.out.println("Removed below meow:");
            System.out.println("  " + tasks.get(index - 1));
            tasks.remove(index - 1);
        } else {
            System.out.println("Invalid task number!");
        }
        printTaskCount();
        printUnderScoreLine();
    }

    public static void printTaskCount() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void echoInput() {
        System.out.println("Echo, meow?");
        printUnderScoreLine();
        while (true) {
            String line = in.nextLine();
            String temp = line.toUpperCase();
            if (temp.equals("BYE")) {
                printBye();
                break;
            } else if (temp.equals("BACK")) {
                options();
                break;
            }
            printUnderScoreLine();
            System.out.println(line);
            printUnderScoreLine();
        }
    }

    public static void addInput() {
        ArrayList<String> input = new ArrayList<>();
        while (true) {
            System.out.println("Add any fish?");
            printUnderScoreLine();
            String line = in.nextLine();
            String temp = line.toUpperCase();
            if (line.isEmpty()) {
                System.out.println("Meow?");
            } else if (temp.equals("LIST")) {
                for (int i = 0; i < input.size(); i++) {
                    System.out.println((i + 1) + ". " + input.get(i));
                }
                printUnderScoreLine();
            } else if (temp.equals("BYE")) {
                printBye();
                break;
            } else if (temp.equals("BACK")) {
                options();
                break;
            } else {
                printUnderScoreLine();
                if (input.contains(line)) {
                    System.out.println("Duplicated!");
                } else {
                    input.add(line);
                    System.out.println("Added : " + line);
                    printUnderScoreLine();
                }
            }
        }
    }

    public static void options() {
        try {
            System.out.println("What can I do for you, meow?");
            printUnderScoreLine();

            String line = in.nextLine();
            ActionType action = ActionType.fromString(line);

            printUnderScoreLine();

            switch (action) {
                case add:
                    addInput();
                    break;
                case echo:
                    String[] echoInfo = line.split(" ");
                    if (echoInfo.length > 1) {
                        throw new DukeException(" Meow!!! The echo cannot be done.");
                    } else {
                        echoInput();
                    }
                    break;
                case bye:
                    String[] byeInfo = line.split(" ");
                    if (byeInfo.length > 1) {
                        throw new DukeException(" Meow!!! The bye cannot be done.");
                    } else {
                        printBye();
                    }
                    break;
                case list:
                    String[] listInfo = line.split(" ");
                    if (listInfo.length > 1) {
                        throw new DukeException(" Meow!!! The list cannot be done.");
                    } else {
                        listTask();
                    }
                    options();
                    break;
                case todo:
                    String[] todoInfo = line.split(" ");
                    if (todoInfo.length >= 2) {
                        String description = todoInfo[1].trim();
                        addTask(new TodoTask(description));
                    } else {
                        throw new DukeException(" Meow!!! The description of a todo cannot be empty.");
                    }
                    options();
                    break;
                case event:
                    String[] eventInfo = line.split("/from|/to");
                    if (eventInfo.length != 3) {
                        throw new DukeException("Invalid event format!");
                    }
                    String description = eventInfo[0].trim();
                    String from = eventInfo[1].trim();
                    String to = eventInfo[2].trim();
                    addTask(new EventTask(description, from, to));
                    options();
                    break;
                case delete:
                    String[] deleteInfo = line.split(" ");
                    if (!Objects.equals(deleteInfo[0], "delete")) {
                        options();
                        break;
                    }
                    if (deleteInfo.length >= 2) {
                        try {
                            int index = Integer.parseInt(deleteInfo[1].trim());
                            deleteTask(index);
                        } catch (NumberFormatException e) {
                            throw new DukeException(" Meow!!! The delete must come with int meow.");
                        }
                    } else {
                        throw new DukeException(" Meow!!! The description of a delete cannot be empty.");
                    }
                    options();
                    break;
                case deadline:
                    int byIndex = line.indexOf("/by");
                    String[] parts = line.substring(9).split("/by");
                    String deadlineInfo = parts[0].trim();
                    String by = parts[1].trim();
                    if (deadlineInfo.isEmpty() || by.isEmpty()) {
                        throw new DukeException("Meow!!! The description or deadline of a deadline cannot be empty.");
                    }
                    addTask(new DeadlineTask(deadlineInfo, by));
                    options();
                    break;
                case mark:
                    String[] markInfo = line.split(" ");
                    if (markInfo.length == 2) {
                        int taskNumber = Integer.parseInt(markInfo[1]);
                        if (taskNumber > 0 && taskNumber <= tasks.size()) {
                            tasks.get(taskNumber - 1).markAsDone();
                            System.out.println("Meow! I've marked this task as done:");
                            System.out.println(tasks.get(taskNumber - 1));
                        } else {
                            throw new DukeException("Meow? Invalid task number!");
                        }
                    } else {
                        throw new DukeException("Meow? Invalid mark action!");
                    }
                    options();
                    break;
                case unmark:
                    String[] unmarkInfo = line.split(" ");
                    if (unmarkInfo.length == 2) {
                        int taskNumber = Integer.parseInt(unmarkInfo[1]);
                        if (taskNumber > 0 && taskNumber <= tasks.size()) {
                            tasks.get(taskNumber - 1).markAsNotDone();
                            System.out.println("Meow! I've marked this task as done:");
                            System.out.println(tasks.get(taskNumber - 1));
                        } else {
                            throw new DukeException("Meow? Invalid task number!");
                        }
                    } else {
                        throw new DukeException("Meow? Invalid unmark action!");
                    }
                    options();
                    break;
                default:
                    options();
            }
        } catch (DukeException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            options();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printUnderScoreLine();
        String botName = "KunKun";
        System.out.println("My name is " + botName);
        options();
    }
}

