import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100]; // Assuming a maximum of 100 tasks
    private static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Maverick");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else {
                addTask(input);
            }
        }

        System.out.println("Bye. Hope to see you again.");
    }

    private static void addTask(String task) {
        tasks[taskCount++] = task;
        System.out.println("added: " + task);
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
}