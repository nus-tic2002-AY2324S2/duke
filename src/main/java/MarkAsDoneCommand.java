public class MarkAsDoneCommand implements Command {
    private final int index;

    public MarkAsDoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        ui.showTaskMarkedAsDone(task);
        storage.saveTasks(taskList);
    }
        catch (IndexOutOfBoundsException e) {
        ErrorHandling.outOfRange();
        }

    }
}