<<<<<<< Updated upstream
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
=======
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Duke application.
 * Extends the Task class.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     * Format: [D][Status] Description (by: Deadline)
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }
}
>>>>>>> Stashed changes
