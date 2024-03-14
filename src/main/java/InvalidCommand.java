public class InvalidCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.showInvalidCommandMessage();
    }
}