package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class Command {

    private final String commandType;
    private final String commandInput;
    private boolean exit;

    public Command(String commandType, String commandInput) throws DukeException {
        this.commandType = commandType;
        this.commandInput = commandInput;
        this.exit = false;
    }


    /**
     * Based on the command given, method will execute a series of operations
     */
    public void execute(TaskList tasklist) throws DukeException {
        switch(commandType) {
            case "CHECK":
                tasklist.check(commandInput);
                break;
            case "DELETE":
                tasklist.deleteTask(commandInput);
                Storage.save(tasklist);
                break;
            case "EVENT":
                Event event = new Event(commandInput);
                tasklist.insertTask(event);
                Storage.save(tasklist);
                break;
            case "DEADLINE":
                Deadline deadline = new Deadline(commandInput);
                tasklist.insertTask(deadline);
                Storage.save(tasklist);
                break;
            case "STATUS":
                tasklist.updateTaskStatus(commandInput);
                Storage.save(tasklist);
                break;
            case "TODO":
                ToDo todo = new ToDo(commandInput);
                tasklist.insertTask(todo);
                Storage.save(tasklist);
                break;
            case "LIST":
                tasklist.printTaskList();
                break;
            case "BYE":
                this.exit = true;
                break;
            default:
                throw new DukeException("Invalid Command! Please try again!");
        }
    }

    /**
     * Check if the command is an exit command. Print bye message if it is exit command
     */
    public boolean isExit(){
        if (this.exit){
            Ui.bye();
        }
        return this.exit;
    }


}
