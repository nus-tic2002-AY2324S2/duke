import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetUser = "Hello! I'm jelemiiBot\n"
                + "What can I do for you?\n";
        System.out.println(greetUser);

        String line = "";
        String bye = "bye";
        Scanner in = new Scanner(System.in);

        do {
            line = in.nextLine();
            System.out.println(line);
        } while (!line.equals(bye));

        String exit = "Bye. Hope to see you again soon!";

        System.out.println(exit);
    }
}
