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

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        tasklist.updateStatus(getCommandType(), index);
    }
    public boolean Exit(){return false;}
}
