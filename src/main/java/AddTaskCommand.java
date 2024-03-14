public class AddTaskCommand implements Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }


    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!ErrorHandling.checkDuplicate(task.description, taskList)) {


        taskList.add(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveTasks(taskList);

    }
        else {
        ui.showDuplicateMessage();
        }
    }
}