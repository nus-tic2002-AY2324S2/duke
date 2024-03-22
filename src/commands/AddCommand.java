package src.commands;
import src.storage.Storage;
import src.task.Task;
import src.task.TaskList;
import src.ui.Ui;

public class AddCommand extends Command {
    private final Task task;
    public AddCommand(String commandType, Task task) {
        super(commandType);
        this.task = task;
    }

    /***
     * Function to execute the command
     * @param tasklist: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.insertTask(task);
    }

    /***
     * function to set if this command will end the program
     */
    public boolean Exit(){return false;}
}
