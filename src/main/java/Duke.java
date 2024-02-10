import src.main.java.Task;

import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static Task[] todoList = new Task[0];

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
    public static void welcomeMenu(){
        System.out.println("////////////////////////////////////////////");
        System.out.println("//  Here are the things need to follow up //");
        System.out.println("////////////////////////////////////////////");
        for(int i = 0; i < todoList.length; i++){
            String status = todoList[i].getStatusIcon();
            Character type = todoList[i].getEventType();
            System.out.println("["+ type +"]"+ "["+ status +"]" +" "+ (i+1) +"."+ todoList[i].getTaskName());
        }
        System.out.println("                                            ");
        System.out.println("============================================");
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

        if(todoList.length == 0){
            System.out.println("////////////////////////////////////////////");
            System.out.println("//     You have nothing need to be done   //");
            System.out.println("////////////////////////////////////////////");
        }else{
            welcomeMenu();
        }
        System.out.println("How can I help you today?");

        //user list [event name] [event type] [status] [event start date] [event end date]
        //String todoListp[][][][][] = new String[100][100][100][100][100];
        //String todo[] = new String[0];

        //Start running
        while(true){
            in = new Scanner(System.in);
            userInput = in.nextLine();
            String[] wordList = userInput.split(" ");
            String taskinfo = combineArray(wordList);
            Task newTask = new Task(taskinfo);
            //test output
            //System.out.println(userInput);

            //Todo function
            if(wordList[0].equalsIgnoreCase("todo")){
                if(wordList.length == 1){
                    System.out.println("Please give your task a name");
                    continue;
                }
                todoList = add(todoList, newTask);
                newTask.setEventType('T');
                System.out.println("Alright, added "+ taskinfo +" into todo list");
                System.out.println("   [T]"+ "["+ newTask.getStatusIcon() +"] " + newTask.getTaskName());
                System.out.println("You have "+ todoList.length +" things in your todo list");

            }
            //List function
            else if(wordList[0].equalsIgnoreCase("list")){
                if(todoList.length == 0){
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//     You have nothing need to be done   //");
                    System.out.println("////////////////////////////////////////////");
                }else{
                    welcomeMenu();
                }
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
                if(wordList.length == 1){
                    System.out.println("Please give your event a name");
                    continue;
                }
                todoList = add(todoList, newTask);
                newTask.setEventType('E');

            }
            //Deadline function
            else if(wordList[0].equalsIgnoreCase("deadline")){
                if(wordList.length == 1){
                    System.out.println("Please give your deadline a name");
                    continue;
                }
                todoList = add(todoList, newTask);
                newTask.setEventType('D');
            }
            //Exit program
            else if(userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("quit") ){
                System.out.println("See you next time!");
                break;
            }
            else {
                System.out.println("I don't get it, I prepared following functions for you.");
                helpMenu();
            }
        }




    }
}
