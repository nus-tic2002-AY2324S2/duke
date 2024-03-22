package src.task;
import src.main.java.DukeException;
import src.storage.Storage;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(){this.taskList = new ArrayList<>();}

    /***
     * Insert function to add task into task list
     * @param task the task object needs to insert
     */
    public void insertTask(Task task){
        taskList.add(task);
        System.out.println("Alright, added " + task.getTaskName() + " into todo list");
        System.out.println("   " + task);
        System.out.println("You have " + taskList.size() + " things now in your todo list");

    }

    /***
     * Delete function to remove a task from task list
     * @param inputIndex task object needs to be deleted
     */
    public void deleteTask(String inputIndex){
        int index = Integer.parseInt(inputIndex.trim());
        if (taskList.isEmpty()) {
            System.out.println("////////////////////////////////////////////");
            System.out.println("//       You have nothing to delete       //");
            System.out.println("////////////////////////////////////////////");
        }else if(index < 1 || index > taskList.size()){
            System.out.println("No such task in your list :(");
        }else{
            System.out.println("Okay, I've remove following task:");
            System.out.println("   " + taskList.get(index - 1).toString());
            taskList.remove(index - 1);
            System.out.println("You have " + taskList.size() + " things now in your todo list");
        }
    }

    /***
     * Function to mark/unmark function
     * @param value: mark or unmark a task
     * @param inputIndex: indicate which task's status needs to be changed
     */
    public void updateStatus(String value, String inputIndex) {
        int index = Integer.parseInt(inputIndex.trim());
        if (taskList.isEmpty()) {
            System.out.println("////////////////////////////////////////////");
            System.out.println("//        You have nothing to mark        //");
            System.out.println("////////////////////////////////////////////");
        }else if(index < 1 || index > taskList.size()){
            System.out.println("No such task in your list :(");
        }else{
            boolean status = value.equalsIgnoreCase("mark");
            if (taskList.get(index - 1).getStatus() == status && status) {
                System.out.println("The task " + inputIndex + " is already marked as done");
            } else if (taskList.get(index - 1).getStatus() == status && !status) {
                System.out.println("The task " + inputIndex + " is already marked as not done");
            } else {
                if (status) {
                    System.out.println("Okay, I've marked task " + inputIndex + " as done");
                } else {
                    System.out.println("Okay, I've marked task " + inputIndex + " as not done yet");
                }
                taskList.get(index - 1).setStatus(status);
                System.out.println("   " + "[" + taskList.get(index - 1).getStatusIcon() + "] " + taskList.get(index - 1).getTaskName());
            }
        }
    }

    /***
     * Function to find out a task contains certain keyword
     * @param keyword: keyword in the task name
     */
    public void findTask(String keyword){
    }

    /***
     * Function to return the task list
     */
    public ArrayList<Task> getList(){return taskList;}

    /***
     * Setter function to set the task list
     * @param taskList: the task list needs to be set
     */
    public void setList(ArrayList<Task> taskList){ this.taskList = taskList;}
}
