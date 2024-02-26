public class Event extends Task{
    protected String start;
    protected String end;

//    public Event(String description, String start, String end) {
//        super(description);
//        this.start = start;
//        this.end = end;
//    }

    public Event(String description, String start, String end) {
        super(description);
        this.start = "now";
        this.end = "forever";
    }

    public Event(String description) {
        super(description);
        this.start = "now";
        this.end = "forever";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + " )";
    }
}

