public class Duke {
    private static Gui gui;
    private static TaskList tasklist;
    private static Task task;
    private static Deadline deadline;
    private static ToDo todo;
    private static Event event;

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

        switch(command) {

            case "EVENT":
                event = new Event(input);
                tasklist.insertTask(event);
                break;
            case "DEADLINE":
                deadline = new Deadline(input);
                tasklist.insertTask(deadline);
                break;
            case "STATUS":
                int index = Integer.parseInt(input.split("-")[0].trim()) - 1;
                boolean status = Boolean.parseBoolean(input.split("-")[1].toLowerCase());
                tasklist.updateTask(index, status);
                break;
            case "TODO":
                todo = new ToDo(input);
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
