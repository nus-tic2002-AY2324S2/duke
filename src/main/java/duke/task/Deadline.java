package duke.task;
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return by;
    }
    public static Deadline createDeadlineFromCommand(String command) {
        String prefix = "deadline";
        String byKeyword = "/by";

        int prefixIndex = command.toLowerCase().indexOf(prefix);
        int byIndex = command.indexOf(byKeyword);

        if (prefixIndex != -1 && byIndex != -1) {
            String deadlineDescription = command.substring(prefixIndex + prefix.length(), byIndex).trim();
            String by = command.substring(byIndex + byKeyword.length()).trim();

            if (!deadlineDescription.isEmpty() && !by.isEmpty()) {
                return new Deadline(deadlineDescription, by);
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
