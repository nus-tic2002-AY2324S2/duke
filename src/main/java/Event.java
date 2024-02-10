package src.main.java;
public class Event extends Deadline {
    String from;
    public Event(String description, String by, String from) {
        super(description, by);
        this.from = from;
    }
}
