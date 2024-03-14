import java.util.Arrays;
import java.util.stream.Collectors;

public class Parser {
    public static Command parse(String input) {
        String[] tokens = input.split(" ", 2);
        String commandWord = tokens[0].toLowerCase();
        String argument = tokens.length > 1 ? tokens[1] : null;
            try {
                switch (commandWord) {

                    case "list":
                        return new ListCommand();
                    case "todo":
                        return new AddTaskCommand(new Todo(argument));
                    case "deadline":
                        String[] deadlineArgs = input.split(" /by ", 2);
                        return new AddTaskCommand(new Deadline(deadlineArgs[0].replace("deadline ", ""), deadlineArgs[1]));
                    case "event":
                        String[] eventArgs = input.split("\\s+/from\\s+|\\s+/to\\s+");
                        return new AddTaskCommand(new Event(eventArgs[0].replace("event ", ""), eventArgs[1], eventArgs[2]));
                    case "mark":
                        return new MarkAsDoneCommand(Integer.parseInt(argument));
                    case "delete":
                        return new DeleteTaskCommand(Integer.parseInt(argument));
                    default:
                        return new InvalidCommand();
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {

               ErrorHandling.wrongSyntax();
               return new InvalidCommand();


            }



    }
    public static Task parseTaskFromString(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        String description = parts[1].trim();
//        boolean isDone = Boolean.parseBoolean(parts[2].trim());

        switch (taskType) {
            case "T":
                return new Todo(description);
            case "D":
                return new Deadline(description, parts[3].trim());
            case "E":
                return new Event(description, parts[3].trim(), parts[4].trim());
            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }
}