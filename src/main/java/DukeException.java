
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public String getErrorMessage() {
        return ("error get message" + getMessage());
    }
}