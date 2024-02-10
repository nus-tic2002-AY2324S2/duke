import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetUser = "Hello! I'm jelemiiBot\n"
                + "What can I do for you?\n";
        System.out.println(greetUser);

        String input = "";
        String[] inputList = new String[100];
        int index = 0;
        Scanner in = new Scanner(System.in);

        do {
            input = in.nextLine();

            if (input.equals("list")) {
                if(index == 0) {
                    System.out.println("List is empty");
                }
                else {
                    for(int i = 0; i < index; i++) {
                        System.out.println((i+1) + ". " + inputList[i]);
                    }
                }
            }
            else if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            }

            else {
                inputList[index] = input;
                index++;
                System.out.println("added: " + input);
            }

        } while (!input.equals("bye"));

    }
}
