import src.storage.Storage;
import src.task.Deadline;
import src.task.Event;
import src.task.Task;
import src.task.Todo;
import src.main.java.DukeException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {
    private static ArrayList<Task> todoList = new ArrayList<>();
    private static boolean format = true;

    private static void writeToFile(String filePath) {
        try {
            // Create the directory if it doesn't exist
            File directory = new File("./data/");
            if (!directory.exists()) {
                directory.mkdirs();  // Creates parent directories as needed
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Task task : todoList) {
                    writer.write(task.format());
                    writer.newLine(); // Add a new line after each task description
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static ArrayList<Task> readFromFile(String filePath) {
        ArrayList<Task> readDataList = new ArrayList<>();

        Path path = Paths.get(filePath);
        // Check if the file exists before attempting to read
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return readDataList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String type = new String();
            String taskName = new String();
            String by = new String();
            String from = new String();
            boolean status = false;
            while ((line = reader.readLine()) != null) {
                String[] txtList = line.split("\\|");
                //Analyis txt file data
                for(String item : txtList){
                    if(item.equalsIgnoreCase("T") ||
                            item.equalsIgnoreCase("E")||
                            item.equalsIgnoreCase("D")){
                        type = item;
                    }else if(item.equalsIgnoreCase("true")){
                        status = true;
                    }else if(item.equalsIgnoreCase("false")){
                        status = false;
                    }else{
                        String[] itemlist = item.split(" ");
                        if(itemlist[0].equalsIgnoreCase("Name:")){
                            taskName = combineArray(itemlist);
                        }else if (itemlist[0].equalsIgnoreCase("from:")){
                            from = combineArray(itemlist);
                        }else if (itemlist[0].equalsIgnoreCase("by:")){
                            by = combineArray(itemlist);
                        }
                    }
                }
                //Install data
                if(type.equalsIgnoreCase("T")){
                    Todo task = new Todo(taskName);
                    task.setStatus(status);
                    readDataList.add(task);
                }else if(type.equalsIgnoreCase("E")){
                    Event task = new Event(taskName, from, by);
                    task.setStatus(status);
                    readDataList.add(task);
                }else if(type.equalsIgnoreCase("D")){
                    Deadline task = new Deadline(taskName, by);
                    task.setStatus(status);
                    readDataList.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readDataList;
    }
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

        if(todoList.size() == 0){
            System.out.println("////////////////////////////////////////////");
            System.out.println("//     You have nothing need to be done   //");
            System.out.println("////////////////////////////////////////////");
        }else{
            System.out.println("////////////////////////////////////////////");
            System.out.println("//  Here are the things need to follow up //");
            System.out.println("////////////////////////////////////////////");
            for(int i = 0; i < todoList.size(); i++){
                String status = todoList.get(i).getStatusIcon();
                Character type = todoList.get(i).getEventType();
                String from = todoList.get(i).getFrom();
                String by = todoList.get(i).getBy();
                if(type.equals('T')){
                    System.out.println((i+1)+"." +"["+ type +"]"+ "["+ status +"]" +" "+ todoList.get(i).getTaskName());
                }else if(type.equals('E')){
                    System.out.println((i+1)+"." + "["+ type +"]"+ "["+ status +"]" +" "+ todoList.get(i).getTaskName() +
                            " (From: " + from + " To: " + by + ")");
                }else if(type.equals('D')){
                    System.out.println((i+1)+"." + "["+ type +"]"+ "["+ status +"]" +" "+ todoList.get(i).getTaskName() +
                            " (By: " + by + ")");
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
                    String checkNum = wordList[1];
                    if(isInteger(checkNum)){
                        return null;// No error
                    }else{
                        throw new DukeException("Please tell me which number of task you would like to mark/unmark");
                    }
                }
            }
            else if(wordList[0].equalsIgnoreCase("delete")){
                if(wordList.length == 1){
                    throw new DukeException("Please tell me which task you would like to delete");
                }else{
                    String checkNum = wordList[1];
                    if(isInteger(checkNum)){
                        return null;// No error
                    }else{
                        throw new DukeException("Please tell me which number of task you would like to delete");
                    }
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
    //Check integer
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
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

        //Read file
        String filePath = "./data/task.txt";
        Storage storage = new Storage(filePath);
        // Read data from the file
        todoList = readFromFile(filePath);

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
                Todo newTask = new Todo(taskName);
                todoList.add(newTask);
                newTask.setEventType('T');

                System.out.println("Alright, added "+ taskName +" into todo list");
                System.out.println("   "+ newTask.toString());
                System.out.println("You have "+ todoList.size() +" things now in your todo list");

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
                if(todoList.size() == 0){
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//        You have nothing to mark        //");
                    System.out.println("////////////////////////////////////////////");
                }else{
                    if(itemNum >= 1 && itemNum <= todoList.size()){
                        if(todoList.get(itemNum-1).getStatus() == status && todoList.get(itemNum-1).getStatus() == true){
                            System.out.println("The task "+ itemNum +" is already marked as done");
                        }else if(todoList.get(itemNum-1).getStatus() == status && todoList.get(itemNum-1).getStatus() == false){
                            System.out.println("The task "+ itemNum +" is already marked as not done");
                        }else{
                            if(status == true){
                                System.out.println("Okay, I've marked task "+ itemNum + " as done");
                            }else{
                                System.out.println("Okay, I've marked task "+ itemNum + " as not done yet");
                            }
                            todoList.get(itemNum-1).setStatus(status);
                            System.out.println("   "+ "["+ todoList.get(itemNum-1).getStatusIcon() +"] " + todoList.get(itemNum-1).getTaskName());

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
                todoList.add(newEvent);
                newEvent.setEventType('E');
                System.out.println("Alright, added "+ taskName +" into todo list");
                System.out.println("   "+ newEvent.toString());
                System.out.println("You have "+ todoList.size() +" things now in your todo list");

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
                todoList.add(newDeadline);
                newDeadline.setEventType('D');
                System.out.println("Alright, added "+ taskName +" into todo list");
                System.out.println("   "+ newDeadline.toString());
                System.out.println("You have "+ todoList.size() +" things now in your todo list");
            }
            //Delete function
            else if(wordList[0].equalsIgnoreCase("delete")){
                int itemNum = Integer.parseInt(wordList[1]);
                if(todoList.size() == 0){
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//       You have nothing to delete       //");
                    System.out.println("////////////////////////////////////////////");
                }else{
                    if(itemNum >= 1 && itemNum <= todoList.size()){
                        System.out.println("Okay, I've remove following task:");
                        System.out.println("   "+ todoList.get(itemNum-1).toString());
                        todoList.remove(itemNum-1);
                        System.out.println("You have "+ todoList.size() +" things now in your todo list");
                    }else{
                        System.out.println("No such task in your list :(");
                    }
                }
            }
            //Exit program
            else if(userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("quit") ){
                System.out.println("Bye! See you next time!");
                writeToFile(filePath);
                break;
            }else{
                break;//Code should never reach here.
            }
        }
    }
}
