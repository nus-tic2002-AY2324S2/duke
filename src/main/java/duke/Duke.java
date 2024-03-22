package duke;

import duke.command.Parser;
import duke.ui.Ui;
import duke.task.TaskList;

public class Duke {
    public static void main(String[] args){
        TaskList.readTasksFromFile();
        Ui.greetUser();
        Parser.runDuke();
        Ui.sayGoodbye();
    }
}
