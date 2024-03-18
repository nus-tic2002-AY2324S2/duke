public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
    public String getErrorMessage() {
        return "    Arise, ERROR!: " + getMessage();
    }
    public static void handleGracefulError(DukeException exception) {
        System.out.println(exception.getErrorMessage());
    }
    public static DukeException invalidTaskNumber() {
        return new DukeException("Foolish mortal! Specify a valid task number.");
    }

    public static DukeException invalidToDoFormat() {
        return new DukeException("Aha! Enter the ToDo format as follows: todo <description>");
    }

    public static DukeException invalidDeadlineFormat() {
        return new DukeException("Pathetic creature! Enter the Deadline format as follows: deadline <description> /by <date>");
    }

    public static DukeException invalidEventFormat() {
        return new DukeException("Worthless Being! Enter the Event format as follows: event <description> /from <date> /to <date>");
    }
}