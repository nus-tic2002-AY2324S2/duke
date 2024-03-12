package src.main.java;

public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = 'D';
    }
    public String getBy() {
        return by;
    }
    public void setBy(String By){by = By;}
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + " )";
    }
}
