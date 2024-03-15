package src.commands;

import src.storage.Storage;
import src.task.TaskList;
import src.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String commandType) {
        super(commandType);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Exit();
        ui.exitProgram();
        storage.writeToFile(tasklist.getList());
    }
    @Override
    public boolean Exit(){return true;}

}
