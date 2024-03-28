<<<<<<< Updated upstream
public class InvalidCommand implements Command {
=======
/**
 * Represents a command to handle invalid user input in the Duke application.
 * Implements the Command interface.
 */
public class InvalidCommand implements Command {

    /**
     * Executes the command to handle invalid user input.
     * Displays a message indicating that the command is invalid.
     *
     * @param taskList The task list (not used).
     * @param ui       The user interface to display the invalid command message.
     * @param storage  The storage (not used).
     */
>>>>>>> Stashed changes
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.showInvalidCommandMessage();
    }
}