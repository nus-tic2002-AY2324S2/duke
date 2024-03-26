package Duke.Tasks;

public class Tasks {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Tasks() {
    }

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = " ";
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }
    public void setIsNotDone(boolean done) {
        isDone = done;
    }
    public String getTaskType() {
        return taskType;
    }
    public void setTaskType(String description) {
        this.taskType = taskType;
    }


    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

}