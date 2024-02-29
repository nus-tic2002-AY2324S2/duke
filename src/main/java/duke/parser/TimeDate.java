package duke.parser;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class TimeDate {

    private static final String DATETIMEFORMAT = "yyyy-MM-dd HHmm";
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



    public static void main(String[] args) throws DukeException {
        String date = "2019-12-01 20:00";

    }
}
