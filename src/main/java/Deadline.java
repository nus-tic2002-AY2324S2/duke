public class Deadline extends Task {

    protected String by;

    public Deadline(String input) throws DukeException {
        super(input.split("-")[0].trim());
        if (input.split("-").length != 2){
            throw new DukeException("[SYNTAX ERROR] Event command expects 2 parameters delimited by '-'");
        }

        this.by = input.split("-")[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
