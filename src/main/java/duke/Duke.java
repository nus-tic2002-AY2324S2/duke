package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.TaskList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        TaskList.readTasksFromFile();
        Ui.greetUser();
        Parser.runDuke();
        Ui.sayGoodbye();
    }
}
