package src.commands;
import src.storage.Storage;
import src.task.TaskList;
import src.ui.Ui;

public abstract class Command {

    private final String commandType;
    public Command(String commandType){
        this.commandType = commandType;
    }
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);
    public abstract boolean Exit();
    public String getCommandType() {
        return commandType;
    }
}
