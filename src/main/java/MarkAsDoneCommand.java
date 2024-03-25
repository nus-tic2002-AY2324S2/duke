<<<<<<< Updated upstream
public class MarkAsDoneCommand implements Command {
    private final int index;

=======
/**
 * Represents a command to mark a task as done in the Duke application.
 * Implements the Command interface.
 */
public class MarkAsDoneCommand implements Command {
    private final int index;

    /**
     * Constructs a MarkAsDoneCommand object with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
>>>>>>> Stashed changes
    public MarkAsDoneCommand(int index) {
        this.index = index;
    }

<<<<<<< Updated upstream
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        ui.showTaskMarkedAsDone(task);
        storage.saveTasks(taskList);
    }
        catch (IndexOutOfBoundsException e) {
        ErrorHandling.outOfRange();
        }

=======
    /**
     * Executes the command to mark the task as done.
     * If the index is valid, marks the task at the specified index as done,
     * displays a confirmation message, and saves the updated task list to storage.
     * If the index is out of range, displays an error message.
     *
     * @param taskList The task list containing the task to be marked as done.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.get(index - 1);
            task.markAsDone();
            ui.showTaskMarkedAsDone(task);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            ErrorHandling.outOfRange();
        }
>>>>>>> Stashed changes
    }
}