import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
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

}

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> userInputHistory = new ArrayList<>();
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
            // read
            String userInput = scanner.nextLine().trim();
            String[] tokenized = userInput.split("\\s+", 2);

            // if list
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task historyItem : userInputHistory) {
                    System.out.println(i + ".[" + historyItem.getStatusIcon() + "] " + historyItem.description);
                    i++;
                }
                System.out.println("____________________________________________________________");
            }

            else if (tokenized[0].equals("mark") && tokenized.length > 1) {
                int markDone = Integer.parseInt(tokenized[1]) - 1;
                userInputHistory.get(markDone).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + userInputHistory.get(markDone).description);
                System.out.println("____________________________________________________________");
            }

            else if (tokenized[0].equals("unmark") && tokenized.length > 1) {
                int markUndone = Integer.parseInt(tokenized[1]) - 1;
                userInputHistory.get(markUndone).markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + userInputHistory.get(markUndone).description);
                System.out.println("____________________________________________________________");
            }

            // if bye
            else if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            }

            //else save into history
            else {
                userInputHistory.add(new Task(userInput));
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
