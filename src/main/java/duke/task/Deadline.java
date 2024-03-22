package duke.task;

import duke.command.DateTimeParser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime byDateTime;

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }
    public LocalDateTime getBy() {
        return byDateTime;
    }
    public static Deadline createDeadlineFromCommand(String command) {
        String prefix = "deadline";
        String byKeyword = "/by";

        int prefixIndex = command.toLowerCase().indexOf(prefix);
        int byIndex = command.indexOf(byKeyword);

        if (prefixIndex != -1 && byIndex != -1) {
            String deadlineDescription = command.substring(prefixIndex + prefix.length(), byIndex).trim();
            String byDateTimeString = command.substring(byIndex + byKeyword.length()).trim();

            LocalDateTime byDateTime = DateTimeParser.parseDateTime(byDateTimeString);

            if (!deadlineDescription.isEmpty() && !byDateTimeString.isEmpty()) {
                return new Deadline(deadlineDescription, byDateTime);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
