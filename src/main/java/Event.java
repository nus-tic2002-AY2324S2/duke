public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String input) throws DukeException {
        super(input.split("-")[0].trim());
        if (input.split("-").length != 3){
            throw new DukeException("[SYNTAX ERROR] Event command expects 3 parameters delimited by '-'");
        }
        String start = input.split("-")[1].trim();
        String end = input.split("-")[2].trim();

        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + " )";
    }
}
