package duke;

import duke.gui.Gui;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.exception.DukeException;

public class Duke {
    private static Gui gui;
    private static TaskList tasklist;
    private static Task task;

    /**
     * Constructor
     * Initialise gui and tasklist object
     */
    public Duke(){
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
            if (userinput.contains("BYE") || userinput.contains("LIST")){
                throw new DukeException("[SYNTAX ERROR] Input command has no ':'");
            }
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
                break;
            case "EVENT":
                Event event = new Event(input);
                tasklist.insertTask(event);
                break;
            case "DEADLINE":
                Deadline deadline = new Deadline(input);
                tasklist.insertTask(deadline);
                break;
            case "STATUS":
                tasklist.updateTask(input);
                break;
            case "TODO":
                ToDo todo = new ToDo(input);
                tasklist.insertTask(todo);
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

    public static void main(String[] args) {
        Duke program = new Duke();
        gui.welcome();
        while(true){
            try {
                program.programExecution();
            }catch (DukeException error){
                System.out.println(error);
            }
        }

    }
}
