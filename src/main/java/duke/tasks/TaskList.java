package duke.tasks;

import duke.exception.DukeException;
import duke.parser.TimeDate;

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
            System.out.println(i+1 + "--" + task.toDisplay() + "\n" );
        }
        System.out.println("\n*******************Task List******************\n");
    }

    /**
     * Print out the task that falls on the user defined date
     */
    public void checkDate(String date) throws DukeException {
        System.out.println("*******************Task List on " + date + " ******************");
        System.out.println("******[Index]-- [Task Type] [Status] [Task description]******\n");
        for(int i = 0; i < this.tasklist.size(); i++){
            Task task = this.tasklist.get(i);
            if (TimeDate.isTaskOnDate(task, date)){
                System.out.println(i+1 + "--" + task.toDisplay() + "\n" );
            }
        }
        System.out.println("\n*******************Task List******************\n");
    }

    /**
     * Print out the tasks that contain user input keyword
     */
    public void findTask(String keyword) {
        System.out.println("*******************Task List ******************");
        System.out.println("******[Index]-- [Task Type] [Status] [Task description]******\n");
        for(int i = 0; i < this.tasklist.size(); i++){
            Task task = this.tasklist.get(i);
            if (task.taskDescription().contains(keyword)){
                System.out.println(i+1 + "--" + task.toDisplay() + "\n" );
            }
        }
        System.out.println("\n*******************Task List******************\n");
    }

    /**
     * Detect duplicate task. Return true if task is already in task list
     * Improvement -> Same task description but different task type is accepted
     */
    public boolean detectDuplicate(Task inputTask) {
        String inputTaskDescription = inputTask.taskDescription();
		for (Task task : this.tasklist) {
            boolean sameTaskDescription = task.taskDescription().equals(inputTaskDescription);
            boolean sameTaskType = task.classType().equals(inputTask.classType());
			if (sameTaskDescription && sameTaskType ) {
				return true;
			}
		}
        return false;
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
    public void insertTask(Task task) throws DukeException {
        if(detectDuplicate(task)){
            throw new DukeException("[Duplicated task] Task is already entered into task list!");
        }
        this.tasklist.add(task);
        System.out.println("Added task: " + task.taskDescription() + " to the task list.\n\n");
        this.printTaskList();
    }

    /**
     * Update task status in array
     * @param index of task object to be modified
     * @param status - True = done, False = not done
     * Error handling
     * (1) Check if command have 2 parameters
     * (2) Check if 1st parameter is a valid index in task list
     *               -Check if parameter is an integer
     *               -Check if that integer is a valid index
     * (3) Check if 2nd parameter is a boolean
     */
    public void updateTaskStatus(String input) throws DukeException {
        DukeException.checkNumParameters(input,2);
        String[] inputSplit = input.split("_");

        int maxIndex = this.tasklist.size() -1;
        int index = DukeException.checkIndex(inputSplit[0], maxIndex);
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
        int index = DukeException.checkIndex(input, maxIndex);
        System.out.println("Deleting task: " + this.tasklist.get(index).taskDescription());
        this.tasklist.remove(index);
        System.out.println("Remaining task in the list: " + this.tasklist.size() + "\n");
    }

}
