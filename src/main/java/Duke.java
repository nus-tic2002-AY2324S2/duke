import java.lang.System;
import java.util.Scanner;

public class Duke {
    private String chatBotName = "Jenkins";

    public Duke(){
        String chatBotName = "Jenkins";
        System.out.println("Hello! you may call me " + getChatBotName());
        System.out.println("What can I do for you?");
        drawLine();
        listenForInput();
    }

    public String getChatBotName(){
        return this.chatBotName;
    }

    public void stopProgram(){
        System.out.print(getChatBotName() + ": Bye. Hope to see you again soon!\n");
        drawLine();
    }

    public void listenForInput(){
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        drawLine();

        if (userInput.equals("bye")){
            stopProgram();
            return;
        }

        response(userInput);
        sc.close();
    }

    public void response(String s){ //echos
       System.out.println(s);
        drawLine();
       listenForInput();
    }

    public void drawLine(){
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke Jenkins = new Duke();
    }
}
