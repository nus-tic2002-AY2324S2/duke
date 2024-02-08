import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }
    }

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

            }
            else if (input.startsWith("mark ")) {
                markTaskAsDone(input);
            }
            else {
                Task t = new Task(input);
                System.out.println("added: " + t.description);
                tasks[taskCount] = t;
                taskCount++;
            }
        }

        System.out.println("Bye. Hope to see you again.");
    }

    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < taskCount; i++) {
                String marked =  tasks[i].getStatusIcon();

                System.out.printf("%d. [%s] %s \n", i + 1, marked, tasks[i].description);


            }
        }
    }

    public static void markTaskAsDone(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;

            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsDone();
                System.out.println("Marked task as done: " + tasks[taskIndex].description);
            } else {
                System.out.println("Invalid task index.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Invalid input for marking a task as done.");
        }
    }
}