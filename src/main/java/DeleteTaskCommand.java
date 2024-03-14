public class DeleteTaskCommand implements Command {
    private final int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }


    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task deletedTask = taskList.remove(index - 1);
            ui.showTaskDeleted(deletedTask, taskList.size());
            storage.saveTasks(taskList);
        }

        catch (IndexOutOfBoundsException e) {
            ErrorHandling.outOfRange();
        }


    }
}