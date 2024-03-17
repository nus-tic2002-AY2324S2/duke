import java.util.List;

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

class DukeExceptionHandler {
    public static void handleExceptions(String userInput, List<Task> taskList) throws DukeException {
        String[] tokenized = userInput.split("\\s+", 2);

        if (tokenized[0].equals("todo")) {
            if (tokenized.length <= 1) {
                throw new DukeException("Please follow the following format: todo <description>");
            }
        }

        else if (tokenized[0].equals("deadline")) {
            if (!userInput.matches("^deadline .+ /by .+$")) {
                throw new DukeException("Please follow the following format: deadline <description> /by <date>");
            }

            String[] deadlineTokens = userInput.split("/by", 2);
            if (deadlineTokens.length <= 1 || deadlineTokens[0].trim().isEmpty()) {
                throw new DukeException("Deadline description cannot be empty. Please follow the format: deadline <description> /by <date>");
            }
        }

        else if (tokenized[0].equals("event")) {
            if (!userInput.matches("^event .+ /from .+ /to .+$")) {
                throw new DukeException("Please follow the following format: event <description> /from <start date> /to <end date>");
            }

            String[] eventTokens = userInput.split("/from", 2);
            String[] fromTokens = eventTokens[1].split("/to", 2);

            if (eventTokens.length <= 1 || eventTokens[0].trim().isEmpty() || fromTokens.length <= 1 || fromTokens[0].trim().isEmpty() || fromTokens[1].trim().isEmpty()) {
                throw new DukeException("Event description, start date, and end date cannot be empty. Please follow the format: event <description> /from <start date> /to <end date>");
            }
        }

        else if (tokenized[0].equals("mark")) {
            if (tokenized.length <= 1) {
                throw new DukeException("Please follow the following format: mark <task number>");
            }

            try {
                int taskNumber = Integer.parseInt(tokenized[1]);
                if (taskNumber < 1 || taskNumber > taskList.size()) {
                    throw new DukeException("Task number is out of range.");
                }
            }

            catch (NumberFormatException e) {
                throw new DukeException("Please enter a number value.");
            }
        }

        else if (tokenized[0].equals("unmark")) {
            if (tokenized.length <= 1) {
                throw new DukeException("Please follow the following format: unmark <task number>");
            }

            try {
                int taskNumber = Integer.parseInt(tokenized[1]);
                if (taskNumber < 1 || taskNumber > taskList.size()) {
                    throw new DukeException("Task number is out of range.");
                }
            }

            catch (NumberFormatException e) {
                throw new DukeException("Please enter a number value.");
            }
        }

        else if (!tokenized[0].equals("list") && !tokenized[0].equals("help") && !userInput.equals("bye")) {
            throw new DukeException("Please enter a valid command.");
        }
    }
}

