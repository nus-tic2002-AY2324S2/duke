import java.util.ArrayList;

/**
 * This class manages the list of task
 */
public class TaskList {
    private ArrayList<Task> tasklist; //Create an attribute to hold a list of task

    /**
     * Constructor
     */
    public TaskList(){
        this.tasklist = new ArrayList<Task>();
    }

    /**
     * Iterate through task list and print it out
     */
    public void printTaskList(){
        System.out.println("*******************Task List******************");
        System.out.println("******[Index]-- [Task Type] [Status] [Task description]******\n");
        for(int i = 0; i < this.tasklist.size(); i++){
            Task task = this.tasklist.get(i);
            System.out.println(i+1 + "--" + task.toString() + "\n" );
        }
        System.out.println("\n*******************Task List******************\n");
    }

    /**
     * Insert a task into the task list
     */
    public void insertTask(Task task){
        this.tasklist.add(task);
        System.out.println("Added task: " + task.taskDescription() + " to the task list.\n\n");
        this.printTaskList();
    }

    /**
     * Update task object in array
     * @param index of task object to be modified
     * @param status - True = done, False = not done
     */
    public void updateTask(int index, boolean status){
        if (status){
            this.tasklist.get(index).markAsDone();
        }else{
            this.tasklist.get(index).markAsNotDone();
        }
        this.printTaskList();
    }
}
