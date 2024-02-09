import java.util.Scanner;

/**
 * This class is to provide "GUI" like features
 */
public class Gui {
    private static Scanner usrinput;

    /**
     * Constructor that will initialize a scanner object
     */
    public Gui(){
        usrinput = new Scanner(System.in);
    }

    /**
     * This method will
     * (1) Print initial message
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome! I'm Sid \nWhat can I do for you?\n");
    }

    /**
     * (1) Provide a list of commands for user to input
     */
    public void printCommands(){
        System.out.println("These are the possible commands you can input:\n[COMMAND SYNTAX] | EXAMPLE | Description");
        System.out.println("******Commands List******\n");
        System.out.println("[ADD:<Input Task>] | ADD:read a book | Add task to the list");
        System.out.println("[DEADLINE:<Input Task>-<Deadline>] | DEADLINE:return a book-Sunday | Add deadline to list");
        System.out.println("[LIST] | LIST | Print out task list");
        System.out.println("[STATUS:<Index>-<true/false>] | STATUS:2-false | Indicate if task is done (true/false)");
        System.out.println("[BYE] | BYE | To exit =(");
        System.out.println("******Commands List******\n");
        System.out.println("Please input commands:");
    }

    /**
     * This method will wait for user input
     * @return a string that contains user input
     */
    public String userInput(){
        return usrinput.nextLine().toUpperCase();
    }

    /**
     * This method will
     * (1) Print an exit message
     * (2) End the program
     */
    public void bye() {
        System.out.println("Farewell!! Till we meet again.............");
        System.exit(0);
    }
}
