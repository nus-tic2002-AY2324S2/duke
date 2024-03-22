package src.commands;

import src.storage.Storage;
import src.task.TaskList;
import src.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String commandType) {
        super(commandType);
    }

    /***
     * Function to execute the command
     * @param tasklist: the task list
     * @param ui: ui functions
     * @param storage： make use of the storage
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Exit();
        ui.exitProgram();
        storage.writeToFile(tasklist.getList());
    }
    /***
     * function to set if this command will end the program
     */
    @Override
    public boolean Exit(){return true;}

}
