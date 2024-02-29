package duke.tasks;

import duke.exception.DukeException;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor with exception handling
     */
    public Task(String description) throws DukeException {
        if (description.isEmpty()){
            throw new DukeException("Please input task!");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Check if task is completed
     * @return string "Task Completed" if true else string "Task in Progress"
     */
    public String getStatusIcon() {
        return (isDone ? "Task Completed" : "Task in Progress");
    }

    /**
     * Update the task description
     * @param string description of task
     */
    public void updateTaskDescription(String task) throws DukeException{
        if (task.isEmpty()){
            throw new DukeException("Description of task cannot be empty.Please input task!\n");
        }
        this.description = task;
        System.out.println("Updated task description: " + task);
    }
    /**
     * Return a string format
     */
    public String toString(){
        return " |" + getStatusIcon() + "| " + this.description;
    }

    public String toDisplay(){
        return " |" + getStatusIcon() + "| " + this.description;
    }

    /**
     * Mark task as done
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Mark task as not done
     */
    public void markAsNotDone(){
        this.isDone = false;
    }

    /**
     * Return status of task
     */
    public boolean statusCheck(){
        return isDone;
    }

    /**
     * Return description of task
     */
    public String taskDescription(){
        return description;
    }

}
