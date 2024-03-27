package duke.parser;

import duke.Command;
import duke.exception.DukeException;

public class Parser {

	public Parser() {

	}

	/**
	 * Parse user input and break it up and store it in Command object.
	 * Conduct input validation on the user input syntax
	 */
	public static Command parser(String userinput) throws DukeException {
		assert userinput != null : "parser requires a string input";
		String command;
		String input = "";
		boolean BYE_COMMAND = userinput.split(":")[0].trim().equals("BYE");
		boolean LIST_COMMAND = userinput.split(":")[0].trim().equals("LIST");

		if ((userinput.contains(":")) && !BYE_COMMAND && !LIST_COMMAND) {
			String[] userInputArray = userinput.split(":");
			if (userInputArray.length <= 1) {
				throw new DukeException("[SYNTAX ERROR] Please input task instructions after ':'\n");
			}
			command = userinput.split(":")[0].trim();
			input = userinput.split(":")[1].trim();
		} else if (((userinput.contains(":")) && (BYE_COMMAND || LIST_COMMAND))) {
			throw new DukeException("[SYNTAX ERROR] 'BYE' and 'LIST' should not be followed with ':' \n");
		} else {
			command = userinput.trim();
		}

		System.out.println("Command is:" + command); //Prints out command

		return new Command(command, input);
	}
}
