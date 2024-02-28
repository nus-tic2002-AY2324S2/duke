package duke;

import duke.gui.Gui;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.exception.DukeException;
import duke.storage.Storage;

public class Duke {
    private static Gui gui;
    private static TaskList tasklist;
    private static Task task;

    private static Storage storage;

    /**
     * Constructor
     * Initialise gui and tasklist object
     */
    public Duke() {
        gui = new Gui();
        tasklist = new TaskList();
    }

    /**
     * Start the program
     */
    public void programExecution() throws DukeException {
        gui.printCommands();

        String userinput = gui.userInput().trim(); //Takes in user input
        String command;
        String input = "";

        /*
        Split the user input into the various sub parts (commands, inputs)
        Check for exception (1) BYE/LIST should not contain other inputs
                            (2) Other commands must contain inputs after :
         */

        if (userinput.contains(":")){
            String[] userInputArray = userinput.split(":");
            if (userInputArray.length <= 1){
                throw new DukeException("[SYNTAX ERROR] Please input task instructions after ':'\n");
            }
            command = userinput.split(":")[0].trim();
            input = userinput.split(":")[1].trim();
        }else{
            command = userinput.trim();
        }

        System.out.println("Command is:" + command); //Prints out user input

        /*
        Switch case to execute the various commands
         */
        switch(command) {
            case "DELETE":
                tasklist.deleteTask(input);
                Storage.save(tasklist);
                break;
            case "EVENT":
                Event event = new Event(input);
                tasklist.insertTask(event);
                Storage.save(tasklist);
                break;
            case "DEADLINE":
                Deadline deadline = new Deadline(input);
                tasklist.insertTask(deadline);
                Storage.save(tasklist);
                break;
            case "STATUS":
                tasklist.updateTask(input);
                Storage.save(tasklist);
                break;
            case "TODO":
                ToDo todo = new ToDo(input);
                tasklist.insertTask(todo);
                Storage.save(tasklist);
                break;
            case "LIST":
                tasklist.printTaskList();
                break;
            case "BYE":
                gui.bye();
                break;
            default:
                throw new DukeException("Invalid Command! Please try again!");
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke program = new Duke();
        gui.welcome();
        tasklist = Storage.load();
        while(true){
            try {
                program.programExecution();
            }catch (DukeException error){
                System.out.println(error);
            }
        }

    }
}
