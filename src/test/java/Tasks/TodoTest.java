package Tasks;

import org.junit.jupiter.api.Test;

import static Tasks.TaskList.createNewTaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testcreateTodo(){
        createNewTaskList();
        String line = "Todo read book";

        assertEquals("[T][ ] read book",Todo.createTodo(line, false));
    }


}
