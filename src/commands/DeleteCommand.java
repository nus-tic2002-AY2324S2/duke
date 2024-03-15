package src.commands;
import src.storage.Storage;
import src.task.TaskList;
import src.ui.Ui;

public class DeleteCommand extends Command {
    private final String index;
    public DeleteCommand(String commandType, String index) {
        super(commandType);
        this.index = index;
    }
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage){
        tasklist.deleteTask(index);
    }
    public boolean Exit(){return false;}

}
