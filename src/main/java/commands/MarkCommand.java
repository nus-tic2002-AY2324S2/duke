package src.commands;
import src.storage.Storage;
import src.task.TaskList;
import src.ui.Ui;

public class MarkCommand extends Command {
    private final String index;

    public MarkCommand(String commandType, String index) {
        super(commandType);
        this.index = index;
    }
    /***
     * Function to execute the command
     * @param tasklist: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        tasklist.updateStatus(getCommandType(), index);
    }
    /***
     * function to set if this command will end the program
     */
    public boolean Exit(){return false;}
}
