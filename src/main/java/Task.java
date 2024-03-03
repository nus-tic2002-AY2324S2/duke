import java.util.List;

public class Task {
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