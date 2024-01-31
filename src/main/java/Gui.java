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
     * (2) Provide a list of commands for user to input
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome! I'm Sid \nWhat can I do for you?\n");
        System.out.println("These are the possible commands you can input:\n[COMMANDS] ---> Description");
        System.out.println("[LIST] ---> No idea what it does yet");
        System.out.println("[BYE] ---> To exit =(");
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
     * (1) Print a exit message
     * (2) End the program
     */
    public void bye() {
        System.out.println("Farewell!! Till we meet again.............");
        System.exit(0);
    }
}
