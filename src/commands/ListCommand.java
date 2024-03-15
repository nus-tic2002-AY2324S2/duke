package src.commands;

import src.storage.Storage;
import src.task.TaskList;
import src.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String commandType) {
        super(commandType);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.listMenu(tasklist.getList());
    }
    public boolean Exit(){return false;}
}
