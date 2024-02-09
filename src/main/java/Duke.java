public class Duke {
    private static Gui gui;
    private static TaskList tasklist;
    private static Task task;
    private static Deadline deadline;

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
    public void programExecution(){
        gui.printCommands();

        String userinput = gui.userInput().trim(); //Takes in user input
        String command;
        String instruction = "";

        if (userinput.contains(":")){
            command = userinput.split(":")[0].trim();
            instruction = userinput.split(":")[1].trim();
        }else{
            command = userinput.trim();
        }

        System.out.println("Command is:" + command); //Prints out user input

        switch(command) {
            case "DEADLINE":
                String description = instruction.split("-")[0].trim();
                String by = instruction.split("-")[1].trim();
                deadline = new Deadline(description,by);
                tasklist.insertTask(deadline);
                break;
            case "STATUS":
                int index = Integer.parseInt(instruction.split("-")[0].trim()) - 1;
                boolean status = Boolean.parseBoolean(instruction.split("-")[1].toLowerCase());
                tasklist.updateTask(index, status);
                break;
            case "ADD":
                task = new Task(instruction);
                tasklist.insertTask(task);
                break;
            case "LIST":
                tasklist.printTaskList();
                break;
            case "BYE":
                gui.bye();
                break;
            default:
                System.out.println("Invalid Command! Please try again!");
        }
    }

    public static void main(String[] args) {
        Duke program = new Duke();
        gui.welcome();
        while(true){
            program.programExecution();
        }

    }
}
