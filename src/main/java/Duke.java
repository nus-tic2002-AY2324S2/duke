import java.lang.System;
import java.util.Scanner;


public class Duke extends Task{
    private String chatBotName = "Jenkins";
    public static String userInput = "";
    public static Boolean chatBotOnline = true;
    public static byte blankUserInput = 0;



    public Duke(){
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
        chatBotOnline = false;
        System.out.print(getChatBotName() + ": Bye. Hope to see you again soon!\n");
    }




//Can't terminate early, else program stops prematurely
    public void scanKeyword(String userInput){
        blankUserInput = 0;

        if (userInput.equals("bye")) {
            stopProgram();
            return;
        }

        if (userInput.equals("change bot name")){
            System.out.println(getChatBotName() + ": Sure! Please key in my new name");
            Scanner sc = new Scanner(System.in); //open scanner!
            userInput = sc.nextLine();

            String name = userInput.trim();
            setChatBotName(name);

            System.out.println(getChatBotName() + ": Right away!");
            chatBotGreetings();

        }

        //obsolete test case
        if (userInput.equals("a")){
            System.out.println(getTaskSize());
        }

        else if (userInput.contains("mark ") || userInput.contains("unmark ")) {
            markAsDone(userInput);
        }


        //        //case: future implementations
        //        else if (true){
        //            //future implementations do something
        //        }

        else if (userInput.equals("list")){
            printWordDiary();
        }

        //
        else {
            createTask(userInput);
            echoUserInput(userInput);
        }

        listenForInput(); //important function to keep program alive. After you scan, Listen again
    }

    public void listenForInput() {

        if (chatBotOnline) {
            drawLine();
            Scanner sc = new Scanner(System.in);

            userInput = sc.nextLine();

            if (userInput.isBlank()) {
                botGetsImpatient(blankUserInput++);
            }

            else{
                scanKeyword(userInput);
            }

        }

        //chatBot Offline, program will return until it closes itself
    }

    public void botGetsImpatient(int blankUserInput){
        final int botMaxPatience = 2; //Feel free to change, I think 2 is good enough
        int botPatience = botMaxPatience - blankUserInput;

        if (botPatience > 1) {
            System.out.println("Sorry, I did not receive any commands");
            System.out.println("I will leave if there's no one around. " + botPatience + " more chance");
            listenForInput();
        }

        else if (botPatience == 1) {
            System.out.println("Last Chance! Please issue a command or I will leave!");
            listenForInput();
        }

        else {
            System.out.println("Looks like no one's here. Good bye");
            stopProgram();
        }
    }


    public void echoUserInput(String s){
        System.out.println("added: " + s);
        listenForInput();
    }

    public void drawLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke Jenkins = new Duke();
    }

}