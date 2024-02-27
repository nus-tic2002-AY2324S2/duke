import java.lang.System;
import java.util.Scanner;


public class Duke{
    private static Task task;

    private String chatBotName = "Jenkins";
    public static String userInput = "";
    public static Boolean chatBotOnline = true;
    public static byte blankUserInput = 0;



    public Duke(){
        chatBotGreetings();
        task = new Task();
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

        if (userInput.equalsIgnoreCase("bye")) {
            stopProgram();
            return;
        }

        if (userInput.equalsIgnoreCase("change bot name")){
            System.out.println(getChatBotName() + ": Sure! Please key in my new name");
            Scanner sc = new Scanner(System.in); //open scanner!
            userInput = sc.nextLine();

            String name = userInput.trim();
            setChatBotName(name);

            System.out.println(getChatBotName() + ": Right away!");
            chatBotGreetings();

        }

        //obsolete test case
        ToDo todo;
        if (userInput.equals("a")){
            System.out.println(Task.getTaskSize());
        }

        else if (userInput.contains("mark ") || userInput.contains("unmark ")) {
            task.markAsDone(userInput);
        }

        //buggy. fake default constructor. Need to resolve user input logic
        else if (userInput.contains("todo ")){
            todo = new ToDo(userInput);
            task.createTask(todo);
            echoUserInput(userInput);
        }

        //buggy. fake default constructor. Need to resolve user input logic
        else if (userInput.contains("event ")){
            Event event = new Event(userInput);
            task.createTask(event);
            echoUserInput(userInput);
        }

        else if (userInput.contains("deadline ")){
//            String[] parts = userInput.split(" ");
//            String taskName = parts[1].trim();
//            String taskDeadline = parts[2].trim();

            //Deadline d = new Deadline(taskName, taskDeadline);
            String f = userInput.trim();

            Deadline d = new Deadline(userInput);
            task.createTask(d);
            echoUserInput(f);
        }

        //        //case: future implementations
        //        else if (true){
        //            //future implementations do something
        //        }

        else if (userInput.equalsIgnoreCase("list")){
            task.printWordDiary();
        }

        //
        else { //normal task


            Task t = new Task(userInput);
            task.createTask(t);
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
                String cleanText = userInput.trim();
                scanKeyword(cleanText);
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