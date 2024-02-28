package duke.parser;

import duke.Command;
import duke.exception.DukeException;

public class Parser {
    
    public Parser(){
        
    }
    
    public static Command parser(String userinput) throws DukeException {
        String command;
        String input = "";
        
        if (userinput.contains(":")){
            String[] userInputArray = userinput.split(":");
            if (userInputArray.length <= 1){
                throw new DukeException("[SYNTAX ERROR] Please input task instructions after ':'\n");
            }
            command = userinput.split(":")[0].trim();
            input = userinput.split(":")[1].trim();
        }else{
            command = userinput.trim();
        }

        System.out.println("Command is:" + command); //Prints out user input
        
        return new Command(command, input);
    }
}
