package duke.parser;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class TimeDate {

    private static final String DATETIMEFORMAT = "yyyy-MM-dd HHmm";
    private static final String DATEFORMAT = "yyyy-MM-dd";
    private static final String DISPLAYDATEFORMAT = "MMM dd yyyy HHmm";

    private static boolean isTime = false;

    public TimeDate(){

    }

    /**
     * Validate the date time string format
     *
     * @param string containing date
     */
    public static LocalDateTime valiDate(String datetime) throws DukeException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATETIMEFORMAT);

        try{
            return LocalDateTime.parse(datetime, dateTimeFormat);
        }catch (DateTimeParseException e){
            throw new DukeException("Wrong Date Time format! Use yyyy-MM-dd HHmm ");
        }
    }

    /**
     * To handle date only format
     * @return Local Date object
     */
    public static LocalDate valiDate(String date, boolean x) throws DukeException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATEFORMAT);

        try{
            return LocalDate.parse(date, dateTimeFormat);
        }catch (DateTimeParseException e){
            throw new DukeException("Wrong Date format! Use yyyy-MM-dd");
        }
    }

    /**
     * Check user input for the correct format and if it is correct it will return the same string
     * @throws DukeException
     */
    public static String checkFormat(String datetime) throws DukeException {
        DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern(DATETIMEFORMAT);
        return valiDate(datetime).format(standardFormat);
    }

    /**
     * Convert  date to a string using display format
     * @return display string object
     */
    public static String displayFormat(String datetime) throws DukeException {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern(DISPLAYDATEFORMAT);
        return valiDate(datetime).format(displayFormat);
    }

    /**
     * This method check if the Deadline and Event object has date that falls on user input date
     * @param task
     * @param targetDate
     * @return boolean
     * @throws DukeException
     */
    public static boolean eventOnDate(Task task, String targetDate) throws DukeException {
        LocalDate targetDateFormat = valiDate(targetDate, true);
        if (task instanceof Deadline){
            String deadline = ((Deadline) task).getDeadline();
            LocalDate deadlineFormat = valiDate(deadline).toLocalDate();
            return deadlineFormat.equals(targetDateFormat);
        } else if (task instanceof Event) {
            String start = ((Event) task).getStart();
            String end = ((Event) task).getEnd();
            LocalDate startFormat = valiDate(start).toLocalDate();
            LocalDate endFormat = valiDate(end).toLocalDate();

            return ( (targetDateFormat.isEqual(startFormat) || (targetDateFormat.isEqual(endFormat))) ||
                    (targetDateFormat.isAfter(startFormat) && targetDateFormat.isBefore(endFormat)));
        }

        return false;
    }

}
