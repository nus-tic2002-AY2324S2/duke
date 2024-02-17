import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    protected String type; // Added task type
    protected String description;
    protected boolean isDone;

    public Task(String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void printTaskInfo(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + this.getType() + "][" + this.getStatusIcon() + "] " + this.getDescription());
        System.out.println("Now you have " + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in the list.");
        System.out.println("____________________________________________________________");
    }
}

// Todo inherit Task
class TodoTask extends Task {
    public TodoTask(String description) {
        super("T", description);
    }
}

// Deadline inherit Task
class DeadlineTask extends Task {
    public DeadlineTask(String description, String by) {
        super("D", description + " (by: " + by + ")");
    }
}

// Event inherit Task
class EventTask extends Task {
    public EventTask(String description, String from, String to) {
        super("E", description + " (from: " + from + " to: " + to + ")");
    }
}

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\n");
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Firefly!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim();
            String[] tokenized = userInput.split("\\s+", 2);

            // list
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task task : taskList) {
                    System.out.println(i + ".[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
                    i++;
                }
                System.out.println("____________________________________________________________");
            }

            // todo <string>
            else if (tokenized[0].equals("todo") && tokenized.length > 1) {
                taskList.add(new TodoTask(tokenized[1]));
                taskList.get(taskList.size() - 1).printTaskInfo(taskList);
            }

            // deadline <string> /by <string>
            else if (tokenized[0].equals("deadline") && tokenized.length > 1 && userInput.contains("/by")) {
                String[] deadlineInfo = tokenized[1].split("\\s*/by\\s*", 2);
                taskList.add(new DeadlineTask(deadlineInfo[0], deadlineInfo[1].trim()));
                taskList.get(taskList.size() - 1).printTaskInfo(taskList);
            }

            // event <string> /from <string> /to <string>
            else if (tokenized[0].equals("event") && tokenized.length > 1 && userInput.contains("/from") && userInput.contains("/to")) {
                String[] eventInfo = tokenized[1].split("\\s*/from\\s*", 2);
                String[] timeInfo = eventInfo[1].split("\\s*/to\\s*", 2);
                taskList.add(new EventTask(eventInfo[0], timeInfo[0].trim(), timeInfo[1].trim()));
                taskList.get(taskList.size() - 1).printTaskInfo(taskList);
            }

            // mark <int>
            else if (tokenized[0].equals("mark") && tokenized.length > 1) {
                int markDone = Integer.parseInt(tokenized[1]) - 1;
                String taskType = taskList.get(markDone).getType();
                taskList.get(markDone).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + taskType + "][X] " + taskList.get(markDone).getDescription());
                System.out.println("____________________________________________________________");
            }

            // unmark <int>
            else if (tokenized[0].equals("unmark") && tokenized.length > 1) {
                int markUndone = Integer.parseInt(tokenized[1]) - 1;
                String taskType = taskList.get(markUndone).getType();
                taskList.get(markUndone).markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + taskType + "][ ] " + taskList.get(markUndone).getDescription());
                System.out.println("____________________________________________________________");
            }

            // bye
            else if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // else error and ignore
            else {
                System.out.println("____________________________________________________________");
                System.out.println("Invalid command. Please try again.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
