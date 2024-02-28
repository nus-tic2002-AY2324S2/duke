package duke.tasks;

import duke.exception.DukeException;
import java.util.ArrayList;

/**
 * This class manages the list of task
 */
public class TaskList {
    private final ArrayList<Task> tasklist; //Create an attribute to hold a list of task

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
     * @return size of task list
     */
    public int getTaskListSize(){
        return this.tasklist.size();
    }

    /**
     * @return task of specific index from task list
     */
    public Task getTask(int index){
        return this.tasklist.get(index);
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
     * Error handling
     * (1) Check if command have 2 parameters
     * (2) Check if 1st parameter is a valid index in task list
     *               -Check if parameter is an integer
     *               -Check if that integer is a valid index
     * (3) Check if 2nd parameter is a boolean
     */
    public void updateTask(String input) throws DukeException {
        DukeException.checkNumParameters(input,2);
        String[] inputSplit = input.split("-");
        int maxIndex = this.tasklist.size() -1;
        int index = DukeException.getIndex(inputSplit[0], maxIndex);
        boolean status = DukeException.isBoolean(inputSplit[1]);

        if (status){
            this.tasklist.get(index).markAsDone();
        }else{
            this.tasklist.get(index).markAsNotDone();
        }
        this.printTaskList();
    }

    /**
     * Delete task based on index given
     * @param input string
     * @throws DukeException if input is not integer or not in task list
     */
    public void deleteTask(String input) throws DukeException {
        int maxIndex = this.tasklist.size() -1;
        int index = DukeException.getIndex(input, maxIndex);
        System.out.println("Deleting task: " + this.tasklist.get(index).taskDescription());
        this.tasklist.remove(index);
        System.out.println("Remaining task in the list: " + this.tasklist.size() + "\n");
    }

}
