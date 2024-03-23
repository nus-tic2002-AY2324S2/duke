
public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }

    public String getErrorMessage() {
        return ("error get message" + getMessage());
    }

    public static void getError(DukeException exception) {
        System.out.println(exception.getErrorMessage());
    }
    public static DukeException invalidTaskNumber() {
        return new DukeException("Task number is wrong");
    }

    public static DukeException expectIntbutInputString() {
        return new DukeException("Expected number ");
    }
}

