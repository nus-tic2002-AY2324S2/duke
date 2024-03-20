package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {
    private static Ui ui;
    private static TaskList tasklist;

    /**
     * Constructor
     * Initialise gui and task list object
     */
    public Duke() {
        ui = new Ui();
        tasklist = new TaskList();
    }

    /**
     * Start the chat bot application
     */
    public void run() throws DukeException {

        boolean isExit = false;
        ui.welcome();
        tasklist = Storage.load();

        while(!isExit){
            try {
                ui.printCommands();

                Command c = Parser.parser(ui.userInput().trim()); //Takes in user input
                c.execute(tasklist);
                isExit = c.isExit();
            }catch (DukeException error){
                System.out.println(error);
            }
        }
    }
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
