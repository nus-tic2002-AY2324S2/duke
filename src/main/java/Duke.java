import java.lang.System;
import java.util.Scanner;
import java.util.NoSuchElementException;


public class Duke {
    private String chatBotName = "Jenkins";
    public static String userInput = "";

    public Duke(){
        String chatBotName = "Jenkins";
        chatBotGreetings();
        listenForInput();
    }

    public String getChatBotName(){
        return this.chatBotName;
    }

    public void setChatBotName(String userInput){
        this.chatBotName = userInput;
    }

    public void chatBotGreetings(){
        System.out.println(getChatBotName() + ": Hello! you may call me " + getChatBotName() + ". What can I do for you?");
    }

    public void stopProgram(){
        System.out.print(getChatBotName() + ": Bye. Hope to see you again soon!\n");
    }

    //Top cases are corner cases. Bottom for future
    public void chatBotFunctions(String userInput){
        //case 1: stop program
        if (userInput.equals(getChatBotName() + ": bye")){
            stopProgram();
            return;
        }

        //case 2: change chat-bot name
        else if (userInput.equals("change bot name")){
            System.out.println(getChatBotName() + ": Sure! Please key in my new name");
            Scanner sc = new Scanner(System.in); //open scanner!
            userInput = sc.nextLine();

            String name = userInput.trim();
            setChatBotName(name);

            System.out.println(getChatBotName() + ": Right away!");
            chatBotGreetings();
            return;
        }

//        //case: future implementations
//        else if (true){
//            //future implementations do something
//        }

        //Last case: echos user
        else {
            echoUserInput(userInput);
            return;
        }

    }

    public void listenForInput() {
        drawLine();
        Scanner sc = new Scanner(System.in);

        userInput  = sc.nextLine();

         if (userInput.isEmpty()) {
             System.out.println("Sorry, I did not receive any commands");
             System.out.println("Please type something to let me understand");
             listenForInput();
        }
         else {
             chatBotFunctions(userInput);
         }
    }


    public void echoUserInput(String s){
       System.out.println(s);
       listenForInput();
    }

    public void drawLine() {
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        Duke Jenkins = new Duke();
        Jenkins.listenForInput();
    }

}