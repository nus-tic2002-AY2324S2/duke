import java.util.Hashtable;

public class Task implements Printable {
    private boolean isDone;
    private String description;

    public Task(String desc) {
        description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String done = " ";
        if (this.getDone()) {
            done = "X";
        }
        return String.format("[%s] %s", done, this.description);
    }
}