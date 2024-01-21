import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void printUnderScoreLine() {
        int underScoreLineLength = 50;
        char[] underScoreLine = new char[underScoreLineLength];
        Arrays.fill(underScoreLine, '_');
        System.out.println(underScoreLine);
    }
    public static void printBye() {
        printUnderScoreLine();
        System.out.println("Bye. Hope to see you again meow!");
        printUnderScoreLine();
    }
    public static void echoInput() {
        System.out.println("Echo, meow?");
        printUnderScoreLine();
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String temp = line.toUpperCase();
            if (temp.equals("BYE")) {
                printBye();
                break;
            }else if (temp.equals("BACK")) {
                options();
                break;
            }
            printUnderScoreLine();
            System.out.println(line);
            printUnderScoreLine();
        }
    }
    public static void addInput() {
        ArrayList<String> input = new ArrayList<>();
        while (true) {
            System.out.println("Add any fish?");
            printUnderScoreLine();
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String temp = line.toUpperCase();
            if (line.isEmpty()) {
                System.out.println("Meow?");
            } else if (temp.equals("LIST")) {
                ArrayList<String> inputTemp = new ArrayList<>(input);
                int j = 1;
                while (!inputTemp.isEmpty()) {
                    String output = inputTemp.get(0);
                    System.out.println(j + ". " + output);
                    j++;
                    inputTemp.remove(0);
                }
                printUnderScoreLine();
            } else if (temp.equals("BYE")) {
                printBye();
                break;
            } else if (temp.equals("BACK")) {
                options();
                break;
            }else {
                printUnderScoreLine();
                if (input.contains(line)) {
                    System.out.println("Duplicated!");
                } else {
                    input.add(line);
                    System.out.println("Added : " + line);
                    printUnderScoreLine();
                }
            }
        }
    }
    public static void options() {
        System.out.println("What can i do for you , meow?");
        printUnderScoreLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();


        printUnderScoreLine();
        switch (line.toUpperCase()) {
            case "ADD":
                addInput();
                break;
            case "ECHO":
                echoInput();
                break;
            case "BYE":
                printBye();
                break;
            default:
                System.out.println("Meow?");
                options();
                break;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printUnderScoreLine();
        String botName = "KunKun";
        System.out.println("My name is " + botName);

        options();

    }

}
