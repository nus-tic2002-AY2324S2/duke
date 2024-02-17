public class TaskListManager {
    private static final int MAX_TASKS = 100;
    protected static Task[] taskList = new Task[MAX_TASKS];
    protected static int taskCount = 0;

    public static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            taskList[taskCount] = task;
            taskCount++;
            Task.echoUserCommand(task);
            System.out.println("    Now you have " + taskCount + " task(s) in your list");
        } else {
            System.out.println("    The list of tasks is full! I shall not be burdened further.");
        }
    }
    public static void displayList() {
        System.out.println("======= Scroll of Puny Tasks =======");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i + 1) + ". " + taskList[i]);
        }
        Duke.printHorizontalLine();
    }
}
