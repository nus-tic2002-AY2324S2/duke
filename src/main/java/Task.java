import java.util.ArrayList;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return isDone;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void unmarkAsDone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public static void echoUserCommand(Task task) {
        System.out.println("    A foolish command, mortal! You dare to utter:\n      " + task.toString());
    }
    protected static void markTaskAsDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber, TaskListManager.taskList)) {
            Task task = TaskListManager.taskList.get(taskNumber - 1);
            if (!task.isDone()) {
                task.markAsDone();
                System.out.println("    Hmph! I've smitten this task from the list:\n      " + task.toString());
            } else {
                System.out.println("    Fool! This task has already been marked as done!\n      " + task.toString());
            }
        } else {
            System.out.println("    Fool! That task number is beyond the realm of your pitiful list!");
        }
    }
    public static void unmarkTaskAsDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber, TaskListManager.taskList)) {
            Task task = TaskListManager.taskList.get(taskNumber - 1);
            if (task.isDone()) {
                task.unmarkAsDone();
                System.out.println("    Bah! I've restored this task to its pathetic existence:\n      " + task.toString());
            } else {
                System.out.println("    Fool! This task is already in its wretched, incomplete state!\n      " + task.toString());
            }
        } else {
            System.out.println("    You dare invoke the invalid task number? Pathetic!");
        }
    }
    public static void deleteTask(int taskNumber, ArrayList<Task> taskList) {
        if (isValidTaskNumber(taskNumber, taskList)) {
            Task deletedTask = taskList.remove(taskNumber - 1);
            System.out.println("    Witness the eradication of this feeble task:\n      " + deletedTask.toString());
            System.out.println("    Now you have " + taskList.size() + " task(s) in the list. Tremble!");
        } else {
            System.out.println("    Fool! That task number is beyond the realm of your pitiful list!");
        }
    }
    public static boolean isValidTaskNumber(int taskNumber, ArrayList<Task> taskList) {
        return taskNumber > 0 && taskNumber <= taskList.size();
    }
}