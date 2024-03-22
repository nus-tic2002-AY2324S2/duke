package duke.task;
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public static Event createEventFromCommand(String command) {
        String prefix = "event";
        String fromKeyword = "/from";
        String toKeyword = "/to";

        int prefixIndex = command.toLowerCase().indexOf(prefix);
        int fromIndex = command.indexOf(fromKeyword);
        int toIndex = command.indexOf(toKeyword);

        if (prefixIndex != -1 && fromIndex != -1 && toIndex != -1) {
            String eventDescription = command.substring(prefixIndex + prefix.length(), fromIndex).trim();
            String from = command.substring(fromIndex + fromKeyword.length(), toIndex).trim();
            String to = command.substring(toIndex + toKeyword.length()).trim();

            if (!eventDescription.isEmpty() && !from.isEmpty() && !to.isEmpty()) {
                return new Event(eventDescription, from, to);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}