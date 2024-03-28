<<<<<<< Updated upstream
import java.util.Arrays;
import java.util.stream.Collectors;

public class Parser {
=======
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input and task data in the Duke application.
 */
public class Parser {
    /**
     * Parses user input and returns the corresponding command.
     *
     * @param input The user input string to be parsed.
     * @return A Command object representing the parsed command.
     */
>>>>>>> Stashed changes
    public static Command parse(String input) {
        String[] tokens = input.split(" ", 2);
        String commandWord = tokens[0].toLowerCase();
        String argument = tokens.length > 1 ? tokens[1] : null;
<<<<<<< Updated upstream
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
=======
        try {
            switch (commandWord) {
                case "find":
                    try {
                        LocalDate date = parseDate(tokens[1]);
                        return new FindCommand(date);
                    }
                    catch (DateTimeParseException e1) {
                        return new FindCommand(argument);
                    }
                    catch (ArrayIndexOutOfBoundsException e2) {
                        System.out.println("Please provide a valid search parameter.");
                    }
                case "list":
                    return new ListCommand();
                case "todo":
                    return new AddTaskCommand(new Todo(argument));
                case "deadline":
                    String[] deadlineArgs = input.split(" /by ", 2);
                    try {
                        LocalDate by = parseDate(deadlineArgs[1].trim());
                        return new AddTaskCommand(new Deadline(deadlineArgs[0].replace("deadline ", ""), by.atStartOfDay()));
                    } catch (DateTimeParseException e1) {
                        try {
                            LocalDateTime by = parseDateTime(deadlineArgs[1].trim());
                            return new AddTaskCommand(new Deadline(deadlineArgs[0].replace("deadline ", ""), by));
                        } catch (DateTimeParseException e2) {
                            System.out.println("Invalid date syntax.");
                        }
                    }
                case "event":
                    String[] eventArgs = input.split("\\s+/from\\s+|\\s+/to\\s+");
                    try {
                        LocalDate from = parseDate(eventArgs[1].trim());
                        LocalDate to = parseDate(eventArgs[2].trim());
                        return new AddTaskCommand(new Event(eventArgs[0].replace("event ", ""), from.atStartOfDay(), to.atStartOfDay()));
                    } catch (DateTimeParseException e1) {
                        try {
                            LocalDateTime from = parseDateTime(eventArgs[1].trim());
                            LocalDateTime to = parseDateTime(eventArgs[2].trim());
                            return new AddTaskCommand(new Event(eventArgs[0].replace("event ", ""), from, to));
                        } catch (DateTimeParseException e2) {
                            System.out.println("Invalid date syntax.");
                        }
                    }
                case "mark":
                    return new MarkAsDoneCommand(Integer.parseInt(argument));
                case "delete":
                    return new DeleteTaskCommand(Integer.parseInt(argument));
                default:
                    return new InvalidCommand();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ErrorHandling.wrongSyntax();
            return new InvalidCommand();
        }
    }

    /**
     * Parses a task data string and returns the corresponding Task object.
     *
     * @param line The task data string to be parsed.
     * @return A Task object representing the parsed task data.
     */
>>>>>>> Stashed changes
    public static Task parseTaskFromString(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        String description = parts[1].trim();
<<<<<<< Updated upstream
//        boolean isDone = Boolean.parseBoolean(parts[2].trim());

=======
>>>>>>> Stashed changes
        switch (taskType) {
            case "T":
                return new Todo(description);
            case "D":
<<<<<<< Updated upstream
                return new Deadline(description, parts[3].trim());
            case "E":
                return new Event(description, parts[3].trim(), parts[4].trim());
=======
                try {
                    LocalDate by = parseAsLocalDate(parts[3].trim());
                    return new Deadline(description, by.atStartOfDay());
                } catch (DateTimeParseException e1) {
                    try {
                        LocalDateTime by = parseAsLocalDateTime(parts[3].trim());
                        return new Deadline(description, by);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Invalid date syntax");
                    }
                }
            case "E":
                try {
                    LocalDate from = parseAsLocalDate(parts[3].trim());
                    LocalDate to = parseAsLocalDate(parts[4].trim());
                    return new Event(description, from.atStartOfDay(), to.atStartOfDay());
                } catch (DateTimeParseException e1) {
                    try {
                        LocalDateTime from = parseAsLocalDateTime(parts[3].trim());
                        LocalDateTime to = parseAsLocalDateTime(parts[4].trim());
                        return new Event(description, from, to);
                    } catch (DateTimeParseException e2) {
                        System.out.println("Invalid date syntax");
                    }
                }
>>>>>>> Stashed changes
            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }
<<<<<<< Updated upstream
=======

    /**
     * Parses a date string into a LocalDateTime object.
     *
     * @param date The date string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws DateTimeParseException If the date string cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String date) throws DateTimeParseException {
        List<String> dateTimePatterns = new ArrayList<>(List.of(
                "M/d/yyyy HHmm",
                "MM/dd/yyyy HHmm",
                "yyyy-MM-dd HHmm",
                "HHmm dd/MM/yyyy",
                "dd/MM/yyyy HHmm"
        ));
        try {
            for (String pattern : dateTimePatterns) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                return dateTime;
            }
        } catch (DateTimeParseException e) {
        }
        throw new DateTimeParseException("Unable to parse the date string", date, 0);
    }

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param date The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the date string cannot be parsed.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        List<String> datePatterns = List.of(
                "MMM/dd/yyyy",
                "MMM dd yyyy",
                "dd MMM yyyy",
                "yyyy MM dd",
                "yyyy/MM/dd",
                "yyyy-MM-dd",
                "dd/MM/yyyy"
        );
        for (String pattern : datePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
                LocalDate parsedDate = LocalDate.parse(date, formatter);
                return parsedDate;
            } catch (DateTimeParseException e) {
            }
        }
        throw new DateTimeParseException("Unable to parse the date string", date, 0);
    }

    /**
     * Parses a string into a LocalDate object using ISO_LOCAL_DATE format.
     *
     * @param inputString The string to be parsed.
     * @return A LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the string cannot be parsed.
     */
    public static LocalDate parseAsLocalDate(String inputString) throws DateTimeParseException {
        return LocalDate.parse(inputString, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Parses a string into a LocalDateTime object using ISO_LOCAL_DATE_TIME format.
     *
     * @param inputString The string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws DateTimeParseException If the string cannot be parsed.
     */
    public static LocalDateTime parseAsLocalDateTime(String inputString) throws DateTimeParseException {
        return LocalDateTime.parse(inputString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
>>>>>>> Stashed changes
}