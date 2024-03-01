package duke.tasks;

import duke.exception.DukeException;
public class ToDo extends Task {
    /**
     * Constructor
     */
    public ToDo(String description) throws DukeException {
        super(description);
    }

    /**
     * Return a string format
     */
    @Override
    public String toString() {

        return "|T|" + super.toString();
    }

    /**
     * Return string format for display
     */
    @Override
    public String toDisplay() {

        return "|T|" + super.toString();
    }
}
