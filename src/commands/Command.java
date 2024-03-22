package src.commands;
import src.storage.Storage;
import src.task.TaskList;
import src.ui.Ui;

public abstract class Command {

    private final String commandType;
    public Command(String commandType){
        this.commandType = commandType;
    }

    /***
     * abstract function for Command classes
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);
    /***
     * abstract function for Command classes
     */
    public abstract boolean Exit();

    /***
     * Return a type of command e.g. mark, unmark, deadline, event, etc.
     */
    public String getCommandType() {
        return commandType;
    }
}
