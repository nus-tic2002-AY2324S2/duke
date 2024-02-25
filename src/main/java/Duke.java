import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static Scanner in = new Scanner(System.in);
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

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

        tasks[taskCount] = task;
        taskCount++;
        System.out.println("Meow, added : " + task);
        printTaskCount();
        printUnderScoreLine();
    }

    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        printUnderScoreLine();
    }

    public static void printTaskCount() {
        System.out.println("Now you have " + taskCount + " tasks in the list.");
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

            printUnderScoreLine();
            switch (line) {
                case "Add":
                    addInput();
                    break;
                case "Echo":
                    echoInput();
                    break;
                case "Bye":
                    printBye();
                    break;
                case "list":
                    listTask();
                    options();
                    break;
                default:
                    if (line.startsWith("todo")) {
                        String[] parts = line.split(" ");
                        if (!Objects.equals(parts[0], "todo")) {
                            options();
                            break;
                        }
                        if (parts.length >= 2) {
                            String description = parts[1].trim();
                            addTask(new TodoTask(description));
                        } else {
                            throw new DukeException(" Meow!!! The description of a todo cannot be empty.");
                        }
                    } else if (line.startsWith("deadline")) {
                        int byIndex = line.indexOf("/by");
                        String[] parts = line.substring(9).split("/by");
                        if (!Objects.equals(parts[0], "deadline")) {
                            System.out.println("Meow?");
                            options();
                            break;
                        }
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        addTask(new DeadlineTask(description, by));
                    } else if (line.startsWith("event")) {
                        String[] parts = line.substring(6).split("/from");
                        if (!Objects.equals(parts[0], "event")) {
                            System.out.println("Meow?");
                            options();
                            break;
                        }
                        String description = parts[0].trim();
                        String[] dateTime = parts[1].split("/to");
                        String from = dateTime[0].trim();
                        String to = dateTime[1].trim();
                        addTask(new EventTask(description, from, to));
                    } else if (line.startsWith("mark")) {
                        String[] parts = line.split(" ");
                        if (!Objects.equals(parts[0], "mark")) {
                            System.out.println("Meow?");
                            options();
                            break;
                        }
                        if (parts.length >= 2) {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber > 0 && taskNumber <= taskCount) {
                                tasks[taskNumber - 1].markAsDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(tasks[taskNumber - 1]);
                            } else {
                                System.out.println("Meow?");
                            }
                        }
                    } else if (line.startsWith("unmark")) {
                        String[] parts = line.split(" ");
                        if (!Objects.equals(parts[0], "unmark")) {
                            System.out.println("Meow?");
                            options();
                            break;
                        }
                        if (parts.length >= 2) {
                            int taskNumber = Integer.parseInt(parts[1]);
                            if (taskNumber > 0 && taskNumber <= taskCount) {
                                tasks[taskNumber - 1].markAsDone();
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println(tasks[taskNumber - 1]);
                            } else {
                                System.out.println("Meow?");
                            }
                        }
                    } else {
                        throw new DukeException("Fish! Fish! Fish!");
                    }
                    options();
                    break;
            }
        }catch (DukeException e) {
            System.out.println(e.getMessage());
            options(); // Prompt again after handling exception
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

