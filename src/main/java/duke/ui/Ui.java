package duke.ui;

import java.util.Scanner;

/**
 * This class is to provide "GUI" like features
 */
public class Ui {
    private static Scanner usrinput;

    /**
     * Constructor that will initialize a scanner object
     */
    public Ui(){
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
        System.out.println("These are the possible commands you can input:\n" +
                "[COMMAND SYNTAX] | EXAMPLE | Description\n");
        System.out.println("******Commands List******");
        System.out.println("[TODO:<Input Task>] | TODO:read a book | Task without date/time");
        System.out.println("[DEADLINE:<Input Task>_<Deadline>] | DEADLINE:return a book_22-01-01 2200 " +
                "| Task to be completed by");
        System.out.println("[Event:<Input Task>_<Start Time>_<End Time] | " +
                "Event:project meeting_2022-01-01 2200 _ 2022-02-01 2300 | Task with a duration");
        System.out.println("[LIST] | LIST | Print out task list");
        System.out.println("[DELETE:<Index>] | DELETE:2 | DELETE TASK 2");
        System.out.println("[STATUS:<Index>_<true/false>] | STATUS:2_false | Indicate if task is done (true/false)");
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
    public static void bye() {
        System.out.println("Farewell!! Till we meet again.............");
    }
}
