package duke.tasks;

import duke.exception.DukeException;
import duke.parser.TimeDate;

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

        this.start = TimeDate.checkFormat(start);
        this.end = TimeDate.checkFormat(end);
    }

    /**
     * Return a string format to include task type -> event
     */
    @Override
    public String toString() {
        return "|E|" + super.toString() + " |from " + start + " to "
                + end + " |";
    }
    /**
     * Return string format for display - Change the date format
     */
    @Override
    public String toDisplay() {
        try {
            return "|E|" + super.toString() + " |from " + TimeDate.displayFormat(start) + " to "
                    + TimeDate.displayFormat(end) + " |";
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return start time
     */
    public String getStart(){

        return this.start;
    }

    /**
     * Return end time
     */
    public String getEnd(){

        return this.end;
    }
    /**
     * Return class type
     */
    @Override
    public String classType(){
        return "Event";
    }
}
