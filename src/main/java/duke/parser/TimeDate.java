package duke.parser;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeDate {
    //Date and Time format accepted by application
    private static final DateTimeFormatter YYYY_MM_DD_HHMM = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    //Date format that is used by CHECK command
    private static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //Display format for user to view
    private static final DateTimeFormatter MMM_DD_YYYY_HHMM = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

	public TimeDate(){

    }

    /**
     * Validate the input if it is in the current date time format
     */
    public static LocalDateTime valiDate(String datetime) throws DukeException {
        assert datetime != null : "valiDate requires a string as input";
        try{
            return LocalDateTime.parse(datetime, YYYY_MM_DD_HHMM);
        }catch (DateTimeParseException e){
            throw new DukeException("Wrong Date Time format! Use yyyy-MM-dd HHmm ");
        }
    }

    /**
     * Validate the input if it is in the current date format
     */
    public static LocalDate valiDate(String date, boolean x) throws DukeException {
        assert date != null : "valiDate requires a string as input";
        try{
            return LocalDate.parse(date, YYYY_MM_DD);
        }catch (DateTimeParseException e){
            throw new DukeException("Wrong Date format! Use yyyy-MM-dd");
        }
    }

    /**
     * Validate user input and convert date to a string using standard format
	 */
    public static String checkFormat(String datetime) throws DukeException {
        assert datetime != null : "checkFormat requires a string as input";
        return valiDate(datetime).format(YYYY_MM_DD_HHMM);
    }

    /**
     * Validate user input and convert date to a string using display format
     */
    public static String displayFormat(String datetime) throws DukeException {
        assert datetime != null : "displayFormat requires a string as input";
        return valiDate(datetime).format(MMM_DD_YYYY_HHMM);
    }

    /**
     * This method check if the Deadline and Event object has date that falls on or between user input date
     */
    public static boolean isTaskOnDate(Task task, String targetDate) throws DukeException {
        assert task != null : "isTaskOnDate requires a Task object as 1st input";
        assert targetDate != null : "isTaskOnDate requires a string as 2nd input";

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

            boolean startDateEqualUserInputDate = targetDateFormat.isEqual(startFormat);
            boolean endDateEqualUserInputDate = targetDateFormat.isEqual(endFormat);
            boolean userInputDateBetweenStartAndEndDate = targetDateFormat.isAfter(startFormat) &&
                    targetDateFormat.isBefore(endFormat);

            return ( startDateEqualUserInputDate || endDateEqualUserInputDate ) || userInputDateBetweenStartAndEndDate;
        }
        return false;
    }

}
