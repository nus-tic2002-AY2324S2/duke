import src.main.java.Deadline;
import src.main.java.Event;
import src.main.java.Task;
import src.main.java.DukeException;

import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static Task[] todoList = new Task[0];
    private static boolean format = true;

    public static void helpMenu(){
        System.out.println("**********************************************");
        System.out.println("*        Please use following commands       *");
        System.out.println("**********************************************");
        System.out.println("* 1. todo itemName                           *");
        System.out.println("* 2. event eventName /by Date                *");
        System.out.println("* 3. deadline eventName /from Date /to Date  *");
        System.out.println("* 4. mark/unmark itemNumber                  *");
        System.out.println("* 5. list                                    *");
        System.out.println("* 6. quit/bye             (To exit program)  *");
        System.out.println("*                                            *");
        System.out.println("**********************************************");
    }
    public static void listMenu(){
        if(todoList.length == 0){
            System.out.println("////////////////////////////////////////////");
            System.out.println("//     You have nothing need to be done   //");
            System.out.println("////////////////////////////////////////////");
        }else{
            System.out.println("////////////////////////////////////////////");
            System.out.println("//  Here are the things need to follow up //");
            System.out.println("////////////////////////////////////////////");
            for(int i = 0; i < todoList.length; i++){
                String status = todoList[i].getStatusIcon();
                Character type = todoList[i].getEventType();
                String from = todoList[i].getFrom();
                String by = todoList[i].getBy();
                if(type.equals('T')){
                    System.out.println("["+ type +"]"+ "["+ status +"]" +" "+ (i+1) +"."+ todoList[i].getTaskName());
                }else if(type.equals('E')){
                    System.out.println("["+ type +"]"+ "["+ status +"]" +" "+ (i+1) +"."+ todoList[i].getTaskName() +
                            " ( From: " + from + " To: " + by + ")");
                }else if(type.equals('D')){
                    System.out.println("["+ type +"]"+ "["+ status +"]" +" "+ (i+1) +"."+ todoList[i].getTaskName() +
                            " ( By: " + by + ")");
                }
            }
            System.out.println("                                            ");
            System.out.println("============================================");
        }

    }
    //Error handler
    public static String checkUserInput(String userInput) {
        String[] wordList = userInput.split(" ");
        try {
            if (wordList[0].equalsIgnoreCase("todo")) {
                if(wordList.length == 1){
                    throw new DukeException("Please give your task a name");
                }else{
                    return null;// No error
                }
            }
            else if (wordList[0].equalsIgnoreCase("event")) {
                if(wordList.length == 1){
                    throw new DukeException("Please give your event a name");
                }else{
                    boolean fromChecker = false;
                    boolean toChecker = false;
                    //Check From stage and to stage
                    for (int i = 0; i < wordList.length; i++) {
                        if(wordList[1].equalsIgnoreCase("/from") ||
                                wordList[1].equalsIgnoreCase("/to")){
                            throw new DukeException("Please give your event a name");
                        }
                        else if(wordList[i].equalsIgnoreCase("/from")){
                            if(i+1 < wordList.length && !wordList[i+1].equalsIgnoreCase("/to")){
                                fromChecker = true;
                                continue;
                            }else{
                                throw new DukeException("Can you tell me about the start date of this event?");
                            }
                        }
                        else if(wordList[i].equalsIgnoreCase("/to")){
                            if(i+1 < wordList.length && !wordList[i+1].equalsIgnoreCase("/from")){
                                toChecker = true;
                                continue;
                            }else{
                                throw new DukeException("Can you tell me about the end date of this event?");
                            }
                        }
                    }
                    //Handle error
                    if(fromChecker == false || toChecker == false){
                        throw new DukeException("Your event format seems wrong, please try following pattern:\n" +
                                "event + event Name + /from + Date + /to + Date");
                    }else{
                        return null;
                    }
                }

            }
            else if (wordList[0].equalsIgnoreCase("deadline")) {
                if(wordList.length == 1){
                    throw new DukeException("Please give your deadline a name");
                }else{

                    boolean byChecker = false;
                    //Check From stage and to stage
                    for (int i = 0; i < wordList.length; i++) {
                        if(wordList[1].equalsIgnoreCase("/by")){
                            throw new DukeException("Please give your deadline a name");
                        }
                        else if(wordList[i].equalsIgnoreCase("/by")){
                            if(i+1 < wordList.length){
                                byChecker = true;
                                break;
                            }else{
                                throw new DukeException("Can you tell me the due date?");
                            }
                        }
                    }

                    //Handle error
                    if(byChecker == false){
                        throw new DukeException("Your deadline format seems wrong, please try following pattern:\n" +
                                "deadline + deadline Name + /by + Date");
                    }else{
                        return null;// No error
                    }
                }

            }
            else if(wordList[0].equalsIgnoreCase("mark") ||
                    wordList[0].equalsIgnoreCase("unmark")){
                if(wordList.length == 1){
                    throw new DukeException("Please tell me which task you would like to mark/unmark");
                }else{
                    return null;// No error
                }
            }
            else if(wordList[0].equalsIgnoreCase("delete")){
                if(wordList.length == 1){
                    throw new DukeException("Please tell me which task you would like to delete");
                }else{
                    return null;// No error
                }
            }
            else if(wordList[0].equalsIgnoreCase("list") ||
                    wordList[0].equalsIgnoreCase("bye")  ||
                    wordList[0].equalsIgnoreCase("quit")  ){// Single command no need to check
                return null;
            }
            else {
                format = false;
                throw new DukeException("I don't get it, I prepared following functions for you.");
            }
        } catch (DukeException e) {
            return e.getMessage();// Return the error message
        }
    }
    //Add string array function
    public static Task[] add(Task[] array, Task element) {
        Task[] result = new Task[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        result[array.length] = element;
        return result;
    }
    //Combine array function
    public static String combineArray(String[] array) {
        if (array.length > 1) {
            String result = array[1];
            result += " ";
            for (int i = 2; i < array.length; i++) {
                result += array[i];
                if(i < array.length){
                    result += " ";
                }
            }
            return result;
        } else {
            return "";
        }
    }



    public static void main(String[] args) {
        String userInput = new String();
        Scanner in = new Scanner(System.in);
        String logo = "         ____        _        \n"
                + "        |  _ \\ _   _| | _____ \n"
                + "        | | | | | | | |/ / _ \\\n"
                + "        | |_| | |_| |   <  __/\n"
                + "        |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "             Hi! I'm Neuro.\n        Glad to see you again:)");
        //Press to continue
        System.out.println("********** Press to continue ************");
        in.nextLine();
        listMenu();
        System.out.println("How can I help you today?");

        //user list [event name] [event type] [status] [event start date] [event end date]
        //String todoListp[][][][][] = new String[100][100][100][100][100];
        //String todo[] = new String[0];

        //Start running
        while(true){
            in = new Scanner(System.in);
            userInput = in.nextLine();
            String[] wordList = userInput.split(" ");
            String taskName = new String();
            String by = new String();
            String from = new String();
            format = true;
            //Check error function
            String errorMessage = checkUserInput(userInput);
            if (errorMessage != null) {
                System.out.println(errorMessage);
                if(format == false){
                    helpMenu();
                }
                continue; // Continue the loop if there's an error
            }
            //test output
            //System.out.println(userInput);

            //Proceed to main function if input no error
            //Todo function
            if(wordList[0].equalsIgnoreCase("todo")){
                taskName = combineArray(wordList);
                Task newTask = new Task(taskName);
                todoList = add(todoList, newTask);
                newTask.setEventType('T');

                System.out.println("Alright, added "+ taskName +" into todo list");
                System.out.println("   "+ newTask.toString());
                System.out.println("You have "+ todoList.length +" things in your todo list");

            }
            //List function
            else if(wordList[0].equalsIgnoreCase("list")){
                listMenu();
            }
            //Mark function
            else if(wordList[0].equalsIgnoreCase("mark") || wordList[0].equalsIgnoreCase("unmark")){
                int itemNum = Integer.parseInt(wordList[1]);
                boolean status = false;
                if(wordList[0].equalsIgnoreCase("mark")){
                    status = true;
                }
                if(todoList.length == 0){
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//        You have nothing to mark        //");
                    System.out.println("////////////////////////////////////////////");
                }else{
                    if(itemNum >= 1 && itemNum <= todoList.length){
                        if(todoList[itemNum-1].getStatus() == status && todoList[itemNum-1].getStatus() == true){
                            System.out.println("The task "+ itemNum +" is already marked as done");
                        }else if(todoList[itemNum-1].getStatus() == status && todoList[itemNum-1].getStatus() == false){
                            System.out.println("The task "+ itemNum +" is already marked as not done");
                        }else{
                            if(status == true){
                                System.out.println("Okay, I've marked task "+ itemNum + " as done");
                            }else{
                                System.out.println("Okay, I've marked task "+ itemNum + " as not done yet");
                            }
                            todoList[itemNum-1].setStatus(status);
                            System.out.println("   "+ "["+ todoList[itemNum-1].getStatusIcon() +"] " + todoList[itemNum-1].getTaskName());

                        }
                    }else{
                        System.out.println("No such task in your list :(");
                    }

                }
            }
            //Event function
            else if(wordList[0].equalsIgnoreCase("event")){
                String stage = "name";
                //Seprate the input and record the eventName, EventFrom, EventTo
                boolean fromChecker = false;
                boolean toChecker = false;
                for(String item:wordList){
                    if(item.equalsIgnoreCase("event")){
                        continue;
                    }
                    else if(item.equalsIgnoreCase("/from")){
                        stage = "from";
                        fromChecker = true;
                        continue;
                    }else if(item.equalsIgnoreCase("/to")){
                        stage = "to";
                        toChecker = true;
                        continue;
                    }
                    if(stage.equalsIgnoreCase("name")){
                        taskName += item;
                        taskName += " ";
                    }else if(stage.equalsIgnoreCase("from")){
                        from += item;
                        from +=" ";

                    }else if (stage.equalsIgnoreCase("to")){
                        by += item;
                        by += " ";
                    }
                }
                Event newEvent = new Event(taskName,from,by);
                todoList = add(todoList, newEvent);
                newEvent.setEventType('E');
                System.out.println("Alright, added "+ taskName +" into todo list");
                System.out.println("   "+ newEvent.toString());
                System.out.println("You have "+ todoList.length +" things in your todo list");

            }
            //Deadline function
            else if(wordList[0].equalsIgnoreCase("deadline")){
                //Seprate the input and record the deadlineName, deadlineBy
                boolean byChecker = false;
                for(String item:wordList){
                    if(item.equalsIgnoreCase("deadline")){
                        continue;
                    }
                    else if(item.equalsIgnoreCase("/by")){
                        byChecker = true;
                        continue;
                    }
                    if(!byChecker){
                        taskName += item;
                        taskName += " ";
                    }else{
                        by += item;
                        by += " ";
                    }
                }
                Deadline newDeadline = new Deadline(taskName,by);
                todoList = add(todoList, newDeadline);
                newDeadline.setEventType('D');
                System.out.println("Alright, added "+ taskName +" into todo list");
                System.out.println("   "+ newDeadline.toString());
                System.out.println("You have "+ todoList.length +" things in your todo list");
            }
            //Exit program
            else if(userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("quit") ){
                System.out.println("Bye! See you next time!");
                break;
            }else{
                break;//Code should never reach here.
            }
        }




    }
}
