import com.sun.jdi.IntegerValue;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
//    private static Task[] tasks = new Task[100];

    private static ArrayList<Task> tasks = new ArrayList<Task>();
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
                return "[X] " + description;
            } else {
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
            return "[E]" + super.toString() + " from: " + this.startTime + "to: " + this.endTime;
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

            } else if (input.startsWith("mark")) {

                try {
                    String description = input.substring(5).trim();
                    int index = Integer.parseInt(description);
                    if (index <= taskCount && taskCount > 0) {
                        markTaskAsDone(input);
                    } else {
                        System.out.printf("Task number %d not found \n", index);
                        System.out.printf("There are currently %d tasks \n", taskCount);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for marking a task as done. Please enter a valid task number.");
                }

            }
            else if (input.startsWith("delete")) {
                try {
                    String description = input.substring(6).trim();
                    int index = Integer.parseInt(description);
                    if (index <= taskCount && taskCount > 0) {
                        String taskDesc = tasks.get(index - 1).toString();
                        tasks.remove(index - 1 );
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(taskDesc);
                        System.out.printf("There are currently %d tasks \n", tasks.size());
                        taskCount--;
                    } else {
                        System.out.printf("Task number %d not found \n", index);
                        System.out.printf("There are currently %d tasks \n", taskCount);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for removing a task. Please enter a valid task number to remove.");
                }

            }
            else if (input.startsWith("todo")) {

                String description = input.substring(5).trim();
                if (!checkError(description)) {
                    if (!checkDuplicate(description)) {
                        Todo td = new Todo(description);
                        tasks.add(td);
                        taskCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(td.toString());
                        System.out.printf("Now you have %d tasks in the list." + "\n", taskCount);
                    }
                }

            } else if (input.startsWith("deadline")) {
                try {
                    String[] parts = input.split("/by");
                    parts[0] = parts[0].replace("deadline", "").trim();

                    if (!checkError(parts[0].trim()) && !checkDuplicate(parts[0])) { //check if description is empty or if theres an duplicate task


                        if (parts[1].trim().isEmpty()) {
                            System.out.println("Input is missing a day (Monday to Sunday). ");
                            return;
                        }

                        if (checkError(parts[1].trim())) {
                            return;
                        }

                        Deadline dl = new Deadline(parts[0], parts[1]);
                        tasks.add(dl);
                        taskCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(dl.toString());
                        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Input is missing '/by' or the day or both.");
                }


            } else if (input.startsWith("event")) {
                try {
                    int fromCount = input.split("/from", -1).length - 1;
                    int toCount = input.split("/to", -1).length - 1;

                    if (fromCount == 1 && toCount == 1) {
                        String[] parts = input.split("/from|/to");
                        parts[0] = parts[0].replace("event", "").trim();

                        if (!checkError(parts[0].trim())) {
                            if (!checkDuplicate(parts[0])) {
                                if (!parts[1].trim().isEmpty() && !parts[2].trim().isEmpty()) {
                                    Events evt = new Events(parts[0], parts[1], parts[2]);
                                    tasks.add(evt);
                                    taskCount++;
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println(evt.toString());
                                    System.out.printf("Now you have %d tasks in the list.\n", taskCount);
                                } else {
                                    System.out.println("Time is missing from input");
                                }
                            }
                        }
                    } else {
                        System.out.println("Input should contain exactly one '/from' and one '/to'");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Time is missing from input");
                }
            } else {

                System.out.println("Sorry, i could not understand. Please try again.");
            }
        }

        System.out.println("Bye. Hope to see you again.");
    }

    public static boolean checkDuplicate(String description) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null && tasks.get(i).description.equals(description)) {
                System.out.println("This task's description already exists in the list");
                return true;
            }

        }
        return false;
    }


    public static boolean checkError(String description) {
        if (description.isEmpty()) {
            System.out.println("The description should not be empty");
            return true;
        }

        try {
            Integer.parseInt(description);
            System.out.println("The description should not be only an integer");
            return true;
        } catch (NumberFormatException e) {

            return false;
        }
    }

    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                String marked = tasks.get(i).toString();

                System.out.printf("%d. %s \n", i + 1, marked);


            }
        }
    }

    public static void markTaskAsDone(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;

            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks.get(taskIndex).markAsDone();
                System.out.println("Marked task as done: " + tasks.get(taskIndex).description);
            } else {
                System.out.println("Invalid task index.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Invalid input for marking a task as done.");
        }
    }
}