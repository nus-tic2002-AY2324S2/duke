import java.util.Scanner;

public class Duke {
    protected static Task[] taskList = new Task[100];
    protected static int index = 0;

    public static void addTodo(String input) {
        taskList[index++] = new Todo(input);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[index - 1].toString());
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    public static void addDeadline(String desc, String by) {
        taskList[index++] = new Deadline(desc, by);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[index - 1].toString());
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    public static void addEvent(String desc, String from, String to) {
        taskList[index++] = new Event(desc, from, to);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[index - 1].toString());
        System.out.println("Now you have " + index + " tasks in the list.");
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
                        System.out.println((i+1) + "." + taskList[i].toString());
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
                    System.out.println(taskList[getIndex].toString());
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
                    System.out.println(taskList[getIndex].toString());
                } else {
                    System.out.println("Invalid index");
                }
            }
            else if(input.startsWith("todo")) {
                addTodo(input);
            }
            else if(input.startsWith("deadline")) {
                int byIndex = input.indexOf("/by");
                String desc = input.substring(9, byIndex).trim();
                String by = input.substring(byIndex + 3).trim();
                addDeadline(desc, by);
            }
            else if(input.startsWith("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                String desc = input.substring(6, fromIndex).trim();
                String from = input.substring(fromIndex + 5, toIndex).trim();
                String to = input.substring(toIndex + 3).trim();
                addEvent(desc, from, to);
            }
            else {
                System.out.println("Please input a valid task");
            }
        }

    }
}
