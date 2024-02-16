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

        public String toString() {
            if (this.isDone) {
            return "[X] " + description; }
            else {
                return "[] " + description;
            }
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }


        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Events extends Task {
        protected String startTime;
        protected String endTime;


        public Events(String description, String startTime, String endTime) {
            super(description);
            this.startTime = startTime;
            this.endTime = endTime;

        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "from: " + this.startTime + "to: " + this.endTime;
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
            else if (input.startsWith("todo")) {
                Todo td = new Todo(input.substring(5));
                tasks[taskCount] = td;
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println(td.toString());
                System.out.printf("Now you have %d tasks in the list." + "\n", taskCount);
            }
            else if (input.startsWith("deadline")) {
                String[] parts = input.split("/by");
                parts[0] = parts[0].replace("deadline", "");
                Deadline dl = new Deadline(parts[0], parts[1]);
                tasks[taskCount] = dl;
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println(dl.toString());
                System.out.printf("Now you have %d tasks in the list." + "\n", taskCount);
            }
            else if(input.startsWith("event")) {
                String[] parts = input.split("/from|/to");
                parts[0] = parts[0].replace("event", "");
                Events evt = new Events(parts[0], parts[1], parts[2]);
                tasks[taskCount] = evt;
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println(evt.toString());
                System.out.printf("Now you have %d tasks in the list." + "\n", taskCount);
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
                String marked =  tasks[i].toString();

                System.out.printf("%d. %s \n", i + 1, marked);


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