package duke.parser;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TimeDateTest {

    /**
     * Test methods for various date time user input
     */
    @Test
    public void dateTimeInputTest() {

        try{
            //Testing if the wrong year format is used
            TimeDate.valiDate("22-22-01", true);
        }catch (DukeException e){
            assertEquals("Wrong Date format! Use yyyy-MM-dd", e.getMessage());
        }

        try{
            //Testing if the wrong year format is used
            TimeDate.valiDate("22-22-01");
        }catch (DukeException e){
            assertEquals("Wrong Date Time format! Use yyyy-MM-dd HHmm ", e.getMessage());
        }

    }

    /**
     * Test  method to check dates in both event and deadline class
     */
    @Test
    public void eventOnDateTest() throws DukeException {
        Deadline deadlineObject = new Deadline("Task_2023-01-01 0000");
        Event eventObject = new Event("Task_2023-01-01 0000 _ 2023-01-30 0000");
        //Deadline fall on user input date return true
        assertTrue(TimeDate.isTaskOnDate(deadlineObject, "2023-01-01"));
        //Event date fall within user input date return true
        assertTrue(TimeDate.isTaskOnDate(eventObject, "2023-01-02"));
        //Event date fall outside user input date return false
        assertFalse(TimeDate.isTaskOnDate(eventObject, "2023-05-02"));
    }

}
