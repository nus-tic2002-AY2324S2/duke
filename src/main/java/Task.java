public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Task Completed" : "Task in Progress");
    }

    /**
     * Update the task description
     * @param string description of task
     */
    public void updateTaskDescription(String task){
        this.description = task;
        System.out.println("Updated task description: " + task);
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
