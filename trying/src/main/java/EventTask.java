public class EventTask extends Task {
    protected String taskFrom;
    protected String taskTo;

    public EventTask(String description, String taskFrom, String taskTo) {
        super(description);
        this.taskFrom = taskFrom;
        this.taskTo = taskTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + taskFrom + ")" + " (To: " + taskTo + ")";
    }

}