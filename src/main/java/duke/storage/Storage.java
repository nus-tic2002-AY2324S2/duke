package duke.storage;

import duke.exception.DukeException;
import duke.parser.TimeDate;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String SEPARATOR = File.separator;
    private static final String DB_PATH = System.getProperty("user.dir") + SEPARATOR + "database";
    private static final String DATABASE_TXT = "database.txt";
    private static final File dbDir = new File(DB_PATH);
    private static final File dbFilePath = new File(DB_PATH + SEPARATOR + DATABASE_TXT);

    public Storage(){

    }

    /**
     * Load the task list from the database file
     */
    public static TaskList load() throws DukeException{
        TaskList tasklist = new TaskList();
        initialise();

        try{
            Scanner scan = new Scanner(dbFilePath);
            while (scan.hasNext()){
                String taskLine = scan.nextLine();
                String [] taskElement = taskLine.split("\\|");
                String taskType = taskElement[1].trim();
                String taskStatus = taskElement[3].trim();
                String taskDescription = taskElement[4].trim();


                switch(taskType){
                    case "T":
                        System.out.println("Loading ... ");
                        System.out.println("Todo " + taskStatus + " " + taskDescription);
                        ToDo toTask = new ToDo(taskDescription);
                        if (taskStatus.contains("Completed")){
                            toTask.markAsDone();
                        } else if (!taskStatus.contains("Progress")) {
                            throw new DukeException("Task Status is invalid. Only accept Task Completed " +
                                    "or Task in Progress");
                        }
                        tasklist.insertTask(toTask);
                        break;

                    case "E":
                        String space = " ";
                        String [] durationSplit = taskElement[5].trim().split(" ");
                        String start = durationSplit[1].trim() + space + durationSplit[2].trim();
                        String end = durationSplit[4].trim() + space + durationSplit[5].trim();

                        System.out.println("Loading ... ");
                        System.out.println("Event " + taskStatus + " " + taskDescription
                                + " Start: " + TimeDate.displayFormat(start) + " End: " + TimeDate.displayFormat(end));

                        Event eventTask = new Event(taskDescription+"_"+start+"_"+end);

                        if (taskStatus.contains("Completed")){
                            eventTask.markAsDone();
                        } else if (!taskStatus.contains("Progress")) {
                            throw new DukeException("Task Status is invalid. Only accept Task Completed " +
                                    "or Task in Progress");
                        }

                        tasklist.insertTask(eventTask);
                        break;

                    case "D":
                        String [] deadlineSplit = taskElement[5].trim().split("=");
                        String by = deadlineSplit[1].trim();

                        System.out.println("Loading ... ");
                        System.out.println("Deadlline " + taskStatus + " " + taskDescription
                                + " by: " + TimeDate.displayFormat(by));

                        Deadline deadlineTask = new Deadline(taskDescription+"_"+by);

                        if (taskStatus.contains("Completed")){
                            deadlineTask.markAsDone();
                        } else if (!taskStatus.contains("Progress")) {
                            throw new DukeException("Task Status is invalid. Only accept Task Completed " +
                                    "or Task in Progress");
                        }

                        tasklist.insertTask(deadlineTask);
                        break;

                    default:
                        throw new DukeException("Task type is invalid. Only accept 'T', 'D', 'E'");
                }
                System.out.println("END OF LINE");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tasklist;

    }

    /**
     * Iterate through the task list and write the task into a database
     */
    public static void save(TaskList list) throws DukeException{
        initialise();
        try{
            FileWriter file = new FileWriter(dbFilePath);
            for(int i = 0; i < list.getTaskListSize(); i++){
                System.out.println("writing to db: " + list.getTask(i));
                file.write(list.getTask(i).toString());
                file.write(System.lineSeparator());
            }
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check the file path and if the database file exist
     * If they do not exist, this method will create them
     */
    public static void initialise() throws DukeException{
        if (!dbDir.exists()){
            dbDir.mkdirs();
            System.out.println("Database directory don't exist. Creating directory: " + dbDir);
        }

        if (!dbFilePath.exists()){
            try{
                dbFilePath.createNewFile();
                System.out.println("Database file does not exist. Created DB file at : " + dbFilePath);
            } catch (IOException error){
                throw new RuntimeException(error);
            }
        }
    }

}

