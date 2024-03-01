package duke;

import duke.exception.DukeException;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
    TaskList taskList = new TaskList();
    Command byeCommand = new Command("BYE", "");
    Command invalidCommand = new Command("HI", "");

    public CommandTest() throws DukeException {
    }

    @Test
    public void byeCommandTest() throws DukeException {

        byeCommand.execute(taskList);
        // Bye command is executed so method will return true
        assertTrue(byeCommand.isExit());
    }

    @Test
    public void invalidCommandTest() throws DukeException{
        try{
            //Invalid command given an exception will be thrown
            invalidCommand.execute(taskList);
        }catch (DukeException e){
            assertEquals("Invalid Command! Please try again!", e.getMessage());
        }
    }
}
