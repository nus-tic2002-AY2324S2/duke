public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
//event project meeting /from Mon 2pm /to 4pm
    public static Event createEventFromCommand(String command) {
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");

        if (fromIndex != -1 && toIndex != -1) {
            String eventDescription = command.substring(6, fromIndex).trim(); // Assuming "event ".length() is 6
            String from = command.substring(fromIndex + 5, toIndex).trim(); // Assuming "/from".length() is 5
            String to = command.substring(toIndex + 3).trim(); // Assuming "/to".length() is 3

            if (!eventDescription.isEmpty() && !from.isEmpty() && !to.isEmpty()) {
                return new Event(eventDescription, from, to);
            }
        }
        return null;
    }
}