package duke.parser;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    public ParserTest() throws DukeException {
    }

    /**
     * Test for user inputs
     */
    @Test
    public void parserUserInputTest() {

        try{
            //Invalid user input for bye command which will result in exception
            Parser.parser("BYE:");
        }catch (DukeException e){
            assertEquals("[SYNTAX ERROR] 'BYE' and 'LIST' should not be followed with ':' \n", e.getMessage());
        }

        try{
            //No additional instruction given after event command which will result in exception
            Parser.parser("EVENT:");
        }catch (DukeException e){
            assertEquals("[SYNTAX ERROR] Please input task instructions after ':'\n", e.getMessage());
        }
    }
}
