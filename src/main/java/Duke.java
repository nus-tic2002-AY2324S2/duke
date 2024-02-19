import java.util.Scanner;

public class Duke {
    protected static Task[] taskList = new Task[100];
    protected static int index = 0;

    public static void addTask(String input) {
        taskList[index++] = new Task(input);
        System.out.println("added: " + input);
    }

    public static void main(String[] args) {
        String greetUser = "Hello! I'm jelemiiBot\n"
                + "What can I do for you?\n";
        System.out.println(greetUser);

        boolean getInput = true;
        Scanner in = new Scanner(System.in);

        while (getInput) {
            String input = in.nextLine().trim();

            if (input.equals("list")) {
                if(index == 0) {
                    System.out.println("List is empty");
                }
                else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < index; i++) {
                        System.out.println((i+1) + ". " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
                    }
                }
            }
            else if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                getInput = false;
            }
            else if(input.startsWith("mark")){
                int getIndex = Integer.parseInt(input.substring(5)) - 1;
                if(getIndex >= 0 && getIndex < index) {
                    Task task = taskList[getIndex];
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("  [" + taskList[getIndex].getStatusIcon() + "] " + taskList[getIndex].description);
                } else {
                    System.out.println("Invalid index");
                }
            }
            else if(input.startsWith("unmark")){
                int getIndex = Integer.parseInt(input.substring(7)) - 1;
                if(getIndex >= 0 && getIndex < index) {
                    Task task = taskList[getIndex];
                    task.unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println("  [" + taskList[getIndex].getStatusIcon() + "] " + taskList[getIndex].description);
                } else {
                    System.out.println("Invalid index");
                }
            }
            else {
                addTask(input);
            }

        }

    }
}
