import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greetUser();
        runDuke();
        sayGoodbye();
    }
    public static void greetUser() {
        System.out.println("Greetings, mortal! I am Balrog, the fiery demon.");
        System.out.println("What foolish command do you wish to utter?");
        printHorizontalLine();
    }
    public static void runDuke() {
        String userInput;
        Scanner in = new Scanner(System.in);

        do {
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                continue;
            }
            echoUserInput(userInput);
            printHorizontalLine();
        } while (!userInput.equalsIgnoreCase("bye"));

    }
    public static void sayGoodbye() {
        System.out.println("    Flee, mortal! Until our paths cross again!");
        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        System.out.println("--------------------------------");
    }
    public static void echoUserInput(String input) {
        System.out.println("    " + input);
    }
}
