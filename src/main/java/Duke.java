import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> userInputHistory = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\n");
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Fujin!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            // read
            String userInput = scanner.nextLine();

            // if list
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                int i = 1;
                for (String historyItem : userInputHistory) {
                    System.out.println(i + ". " + historyItem);
                    i++;
                }
                System.out.println("____________________________________________________________");
            }

            // if bye
            else if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            }

            //else save into history
            else {
                userInputHistory.add(userInput);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
