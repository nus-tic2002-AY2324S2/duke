// Event inherit Task
public class EventTask extends Task {
    public EventTask(String description, String from, String to) {
        super("E", description + " (from: " + from + " to: " + to + ")");
    }
}
