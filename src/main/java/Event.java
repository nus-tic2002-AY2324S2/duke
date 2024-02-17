package src.main.java;
public class Event extends Task {
    String from;
    String by;
    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }
    public String getFrom() {
        return from;
    }
    public String getBy() {
        return by;
    }
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: "+ by +")";
    }
}
