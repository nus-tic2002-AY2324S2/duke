package duke.tasks;

import duke.exception.DukeException;
public class ToDo extends Task {
    /**
     * Constructor
     */
    public ToDo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "|T|" + super.toString();
    }
}
