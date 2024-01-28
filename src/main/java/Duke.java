import java.lang.System;
import java.util.Scanner;
import java.util.NoSuchElementException;


public class Duke {
    private String chatBotName = "Jenkins";
    public static String userInput = "";
    public static Boolean chatBotOnline = true;

    //Overrides echo isBotAlive -> echo user
    public static String[] wordDiary = new String[100];
    public static int listSize = 0;

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
        chatBotOnline = false;
        System.out.print(getChatBotName() + ": Bye. Hope to see you again soon!\n");
    }

    //all the returns are for early termination, so they don't run the other if statements
    public void scanKeyword(String userInput){
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
            return;
        }

        //        //case: future implementations
        //        else if (true){
        //            //future implementations do something
        //        }

        else if (userInput.equals("list")){
            printWordDiary();
            return;
        }

        //
        else {
            echoUserInput(userInput);
        }
        listenForInput(); //important function to keep program alive. After you scan, Listen again
    }

    public void listenForInput() {
        if (chatBotOnline) {
            drawLine();
            Scanner sc = new Scanner(System.in);

            userInput = sc.nextLine();

            if (userInput.isEmpty()) {
                System.out.println("Sorry, I did not receive any commands");
                System.out.println("Please type something to let me understand");
            }

            else {
                scanKeyword(userInput);
            }
        }
    }

    public void storeWord(String s){
        wordDiary[listSize] = s;
        listSize++;
    }

    //For developer internal tests only
    public static void printListSize(){
        System.out.println(listSize);
    }

    public static void printWordDiary(){
        for (int i = 0, j = 1; i<listSize; i++, j++ ){
            System.out.print(j + ". ");
            System.out.println(wordDiary[i]);
        }
    }

    public void echoUserInput(String s){
       storeWord(s);
       System.out.println("added: " + s);
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