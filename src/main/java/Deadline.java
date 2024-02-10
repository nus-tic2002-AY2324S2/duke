package src.main.java;

public class Deadline extends Task{
    String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
}
