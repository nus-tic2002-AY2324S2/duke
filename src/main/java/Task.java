import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {

    private static List<Task> tasksStorage = new ArrayList<>();

    private String description;
    private boolean isDone;

    private static int taskSize = 0;

    //Constructors
    public Task(){
    }

    public Task(String dukeUserInput) {
        this.description = dukeUserInput;
        this.isDone = false;
    }


    public static void createTask(String s){
        Task t = new Task(s);
        tasksStorage.add(t);
        taskSize++;
    }


    //Methods to filter Information
    public String getStatusIcon() {
        return (getIsDone() ? "X" : " "); // mark done task with X
    }

    public static int getTaskSize(){
        return taskSize;
    }

    public static void printWordDiary(){
        if (taskSize == 0){
            System.out.println("List is empty!");
            return;
        }

        for (Task task : tasksStorage) {
            int runningNo = 1;
            System.out.print(runningNo + ".");
            System.out.print("[" + task.getStatusIcon() + "] ");
            System.out.println(task.getTaskDescription());
        }
    }


    //Retrieve String description
    public String getTaskDescription(){
        return description;
    }


    //Retrieve & Update Boolean isDone
    public boolean getIsDone(){
        return isDone;
    }
    public void markAsDone(String s){

        // Define a regular expression pattern to match numbers
        Pattern pattern = Pattern.compile("\\d+");

        // Create a Matcher object to find matches of the pattern in the input string
        Matcher matcher = pattern.matcher(s);

        // Check if any matches are found
        if (matcher.find()) {
            // Extract the matched number
            String numberStr = matcher.group();
            int index = Integer.parseInt(numberStr);
            index--;
            System.out.println("Searching for task " + index + "...");

            // Check if the index is valid
            if (index >= 0 && index < tasksStorage.size()) {
                // Toggle the isDone flag of the specified task
                Task task = tasksStorage.get(index);
                task.isDone = !task.isDone;
                System.out.println("Task " + index + " marked as " + (task.isDone ? "done!" : "undone!"));


                System.out.print(++index + ".");
                System.out.print("[" + task.getStatusIcon() + "] ");
                System.out.println(task.getTaskDescription());

            } else {
                System.out.println("We don't have that task yet!");
            }
        } else {
            System.out.println("I found nothing to mark :(");
        }


    }








}
