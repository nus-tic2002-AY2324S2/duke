package commands;
import task.*;
import ui.Ui;
import storage.Storage;

public class AddCommand extends Command {
    private final Task task;
    public AddCommand(String commandType, Task task) {
        super(commandType);
        this.task = task;
    }

    /***
     * Function to execute the command
     * @param taskList: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.insertTask(task);
    }

    /***
     * function to set if this command will end the program
     */
    public boolean Exit(){return false;}
}
