public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
    public String getErrorMessage() {
        return "    Error: " + getMessage();
    }
    public static void handleGracefulError(DukeException exception) {
        System.out.println(exception.getErrorMessage());
    }
    public static DukeException invalidTaskNumber() {
        return new DukeException("Specify a valid task number.");
    }

    public static DukeException invalidToDoFormat() {
        return new DukeException("Enter the ToDo format as follows: todo <description>");
    }

    public static DukeException invalidDeadlineFormat() {
        return new DukeException("Enter the Deadline format as follows: deadline <description> /by <date>");
    }

    public static DukeException invalidEventFormat() {
        return new DukeException("Enter the Event format as follows: event <description> /from <date> /to <date>");
    }
}