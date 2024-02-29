package duke.tasks;

import duke.exception.DukeException;
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor with exception handling to ensure user input 2 parameters for this command
     */
    public Deadline(String input) throws DukeException {
        super(input.split("_")[0].trim());
        DukeException.checkNumParameters(input,2);

        this.by = input.split("_")[1].trim();
    }

    @Override
    public String toString() {
        return "|D|" + super.toString() + " |by: " + by + "|";
    }
}
