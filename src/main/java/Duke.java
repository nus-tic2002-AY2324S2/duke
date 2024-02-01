public class Duke {
    private static Gui gui;
    private static TaskList tasklist;

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
            case "ADD":
                tasklist.insertTask(instruction);
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
