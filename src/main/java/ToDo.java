public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    public static ToDo createToDoFromCommand(String command) {
        String todoDescription = command.substring(5).trim(); // Assuming "todo ".length() is 5

        if (!todoDescription.isEmpty()) {
            return new ToDo(todoDescription);
        }
        return null;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
