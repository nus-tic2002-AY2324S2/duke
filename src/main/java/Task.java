package src.main.java;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Character type = 'T';

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public boolean getStatus(){
        return isDone;
    }
    public String getTaskName() {
        return description;
    }
    public Character getEventType(){
        return type;
    }
    public void setEventType(Character type){
        this.type = type;
    }
    public void setStatus(boolean status) {
        isDone = status;
    }

    public String toString() {
        return "["+ type +"]["+ getStatusIcon() +"]" + description;
    }
    public String getFrom(){return "";}
    public String getBy(){return "";}
}