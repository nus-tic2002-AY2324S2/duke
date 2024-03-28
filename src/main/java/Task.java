<<<<<<< Updated upstream
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

=======
/**
 * Represents a task in the Duke application.
 */
public class Task  {
    protected String description;

    protected TaskType type;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks the task as done.
     */
>>>>>>> Stashed changes
    public void markAsDone() {
        this.isDone = true;
    }

<<<<<<< Updated upstream
=======
    /**
     * Returns a string representation of the Task object.
     *
     * @return A string representing the task, including its completion status and description.
     */
>>>>>>> Stashed changes
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + description;
    }
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
}