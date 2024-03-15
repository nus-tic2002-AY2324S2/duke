package src.commands;
import src.storage.Storage;
import src.task.TaskList;
import src.ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand(String commandType) {
        super(commandType);
    }
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {

    }
    public boolean Exit(){return false;}

}
