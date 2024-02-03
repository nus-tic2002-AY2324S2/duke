import src.main.java.Task;

import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static Task[] todoList = new Task[0];

    public static void testMenu(){
        System.out.println("**********************************************");
        System.out.println("*          How may I help you today?         *");
        System.out.println("*                                            *");
        System.out.println("*  1. Add ToDo List     (Enter 1 or add)     *");
        System.out.println("*  2. Coming Event     (Enter 2 or event)    *");
        System.out.println("*  3. Coming Deadline  (Enter 3 or deadline) *");
        System.out.println("*  0. Exit             (Enter 0 or bye)      *");
        System.out.println("*                                            *");
        System.out.println("**********************************************");
    }
    public static void welcomeMenu(){
        System.out.println("////////////////////////////////////////////");
        System.out.println("//  Here are the things need to follow up //");
        System.out.println("////////////////////////////////////////////");
        for(int i = 0; i < todoList.length; i++){
            String space = todoList[i].getStatusIcon();
            System.out.println("["+ space +"]"+ "["+ space +"]" +" "+ (i+1) +"."+ todoList[i].getTaskName());
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
            System.out.println("//   You have nothing to need to be done  //");
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

            //Add task
            if(userInput.equalsIgnoreCase("add")){
                System.out.println("Tell me what you would like to add?\n");
                while(true){
                    //todolist tasks
                    in = new Scanner(System.in);
                    userInput = in.nextLine();
                    newTask = new Task(userInput);
                    //quit add function
                    if(userInput.equalsIgnoreCase("done")){
                        welcomeMenu();
                        break;
                    }
                    //add item to array
                    todoList = add(todoList, newTask);
                    System.out.println("added: " + userInput + "\nAnything else?");

                    //test output
                    //System.out.println(Arrays.toString(todoList));
                }
                continue;
            }

            //todo function
            if(wordList[0].equalsIgnoreCase("todo")){
                todoList = add(todoList, newTask);
                System.out.println("Alright, added "+ taskinfo +" into todo list");
                System.out.println("   [T]"+ "["+ newTask.getStatusIcon() +"] " + newTask.getTaskName());
                System.out.println("You have "+ todoList.length +" things in your todo list");

            }
            //list function
            else if(wordList[0].equalsIgnoreCase("list")){
                if(todoList.length == 0){
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//   You have nothing to need to be done  //");
                    System.out.println("////////////////////////////////////////////");
                }else{
                    welcomeMenu();
                }
            }


            else if(userInput.equalsIgnoreCase("3") || userInput.equalsIgnoreCase("deadline")){
                //deadline
                while(!userInput.equalsIgnoreCase("back")){
                    //todolist tasks
                    in = new Scanner(System.in);
                    userInput = in.nextLine();
                }
                continue;
            }

            //Exit program
            if(userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("0") ){
                System.out.println("See you next time!");
                break;
            }
        }




    }
}
