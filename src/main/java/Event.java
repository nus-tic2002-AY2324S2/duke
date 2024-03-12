package src.main.java;
public class Event extends Task {
    protected String from;
    protected String by;
    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
        this.type = 'E';
    }
    public String getFrom() {
        return from;
    }
    public String getBy() {
        return by;
    }
    public void setFrom(String From){from = From;}
    public void setBy(String By){by = By;}

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: "+ by +" )";
    }
}
