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
        System.out.println("    A foolish command, mortal! You dare to utter:\n " + "     " + task.toString());
    }
    protected static void markTaskAsDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            TaskListManager.taskList[taskNumber - 1].markAsDone();
            System.out.println("    Hmph! I've smitten this task from the list:\n      " +
                    TaskListManager.taskList[taskNumber - 1].toString());
        } else {
            System.out.println("    Fool! That task number is beyond the realm of your pitiful list!");
        }
    }
    public static void unmarkTaskAsDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            TaskListManager.taskList[taskNumber - 1].unmarkAsDone();
            System.out.println("    Bah! I've restored this task to its pathetic existence:\n    " +
                    TaskListManager.taskList[taskNumber - 1].toString());
        } else {
            System.out.println("    You dare invoke the invalid task number? Pathetic!");
        }
    }
    public static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= TaskListManager.taskCount;
    }
}