import java.util.ArrayList;

public class TaskListManager {
    public static ArrayList<Task> taskList = new ArrayList<>();

    protected static void addTask(Task task) {
        taskList.add(task);
        Task.echoUserCommand(task);
        System.out.println("    Now you have " + taskList.size() + " task(s) in your list.");
        Duke.printHorizontalLine();
        TaskFileManager.saveTasksToFile(taskList);
    }
    public static void displayList() {
        if (taskList.isEmpty()) {
            System.out.println("    Your feeble Task List is Empty!");
        } else {
            System.out.println("    ======= Scroll of Puny Tasks =======");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("        " + (i + 1) + ". " + taskList.get(i));
            }
        }
        Duke.printHorizontalLine();
    }

}
