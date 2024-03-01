package duke.tasks;

import duke.exception.DukeException;
import duke.parser.TimeDate;
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor with exception handling to ensure user input 2 parameters for this command
     * Handles date time input validation
     */
    public Deadline(String input) throws DukeException {
        super(input.split("_")[0].trim());
        DukeException.checkNumParameters(input,2);

        this.by = input.split("_")[1].trim();
        this.by = TimeDate.checkFormat(by);
    }

    /**
     * return deadline string
     */
    public String getDeadline(){

        return this.by;
    }

    /**
     * Return a string format to include task type -> deadline
     */
    @Override
    public String toString() {

        return "|D|" + super.toString() + " |by= " + by + "|";
    }

    /**
     * Return string format for display - Change the date format
     */
    @Override
    public String toDisplay() {
        try {
            return "|D|" + super.toString() + " |by= " + TimeDate.displayFormat(by) + "|";
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return class type
     */
    @Override
    public String classType(){
        return "Deadline";
    }
}
