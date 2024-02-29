package duke.tasks;

import duke.exception.DukeException;
public class Event extends Task {

    protected String start;
    protected String end;

    /**
     * Constructor with exception handling to ensure user input 3 parameters for this command
     */
    public Event(String input) throws DukeException {
        super(input.split("_")[0].trim());
        DukeException.checkNumParameters(input,3);
        String start = input.split("_")[1].trim();
        String end = input.split("_")[2].trim();

        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "|E|" + super.toString() + " |from: " + start + " to: " + end + " |";
    }
}
