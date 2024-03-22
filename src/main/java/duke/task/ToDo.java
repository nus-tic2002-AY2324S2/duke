package duke.task;
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo createToDoFromCommand(String command) {
        String prefix = "todo";
        int prefixIndex = command.toLowerCase().indexOf(prefix);
        if (prefixIndex != -1) {
            String todoDescription = command.substring(prefixIndex + prefix.length()).trim();

            if (!todoDescription.isEmpty()) {
                return new ToDo(todoDescription);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
