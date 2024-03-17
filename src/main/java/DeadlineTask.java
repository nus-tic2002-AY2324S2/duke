// Deadline inherit Task
public class DeadlineTask extends Task {
    public DeadlineTask(String description, String by) {
        super("D", description + " (by: " + by + ")");
    }
}
