import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JunitTest {
    @Test
    public void testInitialization() {
        Duke duke = new Duke("tasks.txt");
        assertNotNull(duke);
        assertNotNull(duke.ui);
        assertNotNull(duke.taskList);
        assertNotNull(duke.storage);
    }

//    @Test
//    public void testTaskLoading() {
//        Duke duke = new Duke("test_tasks.txt");
//        assertEquals(3, duke.taskList.size()); // Assuming 3 tasks are loaded from the test_tasks.txt file
//    }
//
//    @Test
//    public void testCommandExecution() {
//        Duke duke = new Duke("test_tasks.txt");
//        duke.run(); // Assuming the test_tasks.txt file contains tasks and ends with "bye"
//        // Validate the state of Duke after executing commands, e.g., taskList size, UI messages
//    }

//    @Test
//    public void testInput() {
//        Ui ui = new Ui();
//
//        String input = ui.getUserInput();
//
//    }

    @Test
    public void testCommandParsing() {
        TaskList taskList = new TaskList();

        Command todoCommand = Parser.parse("todo taskExample");
        assertNotNull(todoCommand);
        assertInstanceOf(AddTaskCommand.class, todoCommand);
        todoCommand.execute(taskList, new Ui(), new Storage(""));
        assertEquals( 1, taskList.size());


        Command eventCommand = Parser.parse("event eventExample /from 12/12/2020 1200 /to 12/12/2020 1600");
        assertNotNull(eventCommand);
        assertInstanceOf(AddTaskCommand.class, eventCommand);
        eventCommand.execute(taskList, new Ui(), new Storage(""));
        assertEquals(2, taskList.size());

        Command deadlineCommand = Parser.parse("deadline deadlineExample /by 12/12/2020 1200");
        assertNotNull(deadlineCommand);
        assertInstanceOf(AddTaskCommand.class, deadlineCommand);
        deadlineCommand.execute(taskList, new Ui(), new Storage(""));
        assertEquals(3,taskList.size());


        Command listCommand = Parser.parse("list");
        assertNotNull(listCommand);
        assertInstanceOf(ListCommand.class, listCommand);

        Command deleteCommand = Parser.parse("delete 3");
        assertNotNull(deleteCommand);
        assertInstanceOf(DeleteTaskCommand.class, deleteCommand);
        deleteCommand.execute(taskList, new Ui(), new Storage(""));
        assertEquals(2,taskList.size());

    }


}
