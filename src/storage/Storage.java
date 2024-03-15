package src.storage;
import src.task.Deadline;
import src.task.Event;
import src.task.Task;
import src.task.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static ArrayList<Task> todoList = new ArrayList<>();

    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static void writeToFile() {
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

        java.nio.file.Path path = Paths.get(filePath);
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

}
