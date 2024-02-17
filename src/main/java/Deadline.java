public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public static Deadline createDeadlineFromCommand(String command) {
        int byIndex = command.indexOf("/by");
        if (byIndex != -1) {
            String deadlineDescription = command.substring(8, byIndex).trim(); // Assuming "deadline ".length() is 8
            String by = command.substring(byIndex + 3).trim(); // Assuming "/by".length() is 3

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
