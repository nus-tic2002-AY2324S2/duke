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
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.insertTask(task);
    }
    public boolean Exit(){return false;}
}
