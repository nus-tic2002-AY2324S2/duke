package src.main.java;

public class Deadline extends Task{
    String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return by;
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
