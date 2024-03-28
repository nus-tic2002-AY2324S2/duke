package duke;
import commands.*;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Todo;
import ui.Ui;

import java.time.LocalDateTime;

import static duke.DukeException.isInteger;


public class Parser extends DateTime {
    /***
     * Function to check the user input and make sure the input is a valid command
     * @param userInput: String get from user
     */
    public static Command parse(String userInput) {
        String[] wordList = userInput.split(" ");
        String taskName;
        String by;
        String from;
        boolean format = true;

        try {
            //Todo checker
            if (wordList[0].equalsIgnoreCase("todo")) {
                if(wordList.length == 1){
                    throw new DukeException("Please give your task a name");
                }else{
                    taskName = Storage.combineArray(wordList);
                    Todo task = new Todo(taskName);
                    return new AddCommand("todo",task);
                }
            }
            //Event checker
            else if (wordList[0].equalsIgnoreCase("event")) {
                StringBuilder taskNameBuilder = new StringBuilder();
                StringBuilder fromBuilder = new StringBuilder();
                StringBuilder byBuilder = new StringBuilder();
                if(wordList.length == 1){
                    throw new DukeException("Please give your event a name");
                }else{
                    boolean fromChecker = false;
                    boolean toChecker = false;
                    //Check From stage and to stage
                    for (int i = 0; i < wordList.length; i++) {
                        if(wordList[1].equalsIgnoreCase("/from") ||
                                wordList[1].equalsIgnoreCase("/to")){
                            throw new DukeException("Please give your event a name");
                        }
                        else if(wordList[i].equalsIgnoreCase("/from")){
                            if(i+1 < wordList.length && !wordList[i+1].equalsIgnoreCase("/to")){
                                fromChecker = true;
                            }else{
                                throw new DukeException("Can you tell me about the start date of this event?");
                            }
                        }
                        else if(wordList[i].equalsIgnoreCase("/to")){
                            if(i+1 < wordList.length && !wordList[i+1].equalsIgnoreCase("/from")){
                                toChecker = true;
                            }else{
                                throw new DukeException("Can you tell me about the end date of this event?");
                            }
                        }
                    }
                    //Handle error
                    if(!fromChecker || !toChecker){
                        throw new DukeException("Your event format seems wrong, please try following pattern:\n" +
                                "event + event Name + /from + Date + /to + Date");
                    }else{//If no error
                        String currentStage = "name";
                        for (String item : wordList) {
                            switch (item.toLowerCase()) {
                                case "event":
                                    continue; // Skip "event" keyword
                                case "/from":
                                    currentStage = "from"; // Switch to "from" stage
                                    continue;
                                case "/to":
                                    currentStage = "to"; // Switch to "to" stage
                                    continue;
                            }
                            // Append item to the corresponding stage
                            switch (currentStage) {
                                case "name":
                                    taskNameBuilder.append(item).append(" ");
                                    break;
                                case "from":
                                    fromBuilder.append(item).append(" ");
                                    break;
                                case "to":
                                    byBuilder.append(item).append(" ");
                                    break;
                            }
                        }
                        taskName = taskNameBuilder.toString();
                        from =fromBuilder.toString();
                        by = byBuilder.toString();
                        if(!isDateValid(by)){
                            throw new DukeException("Your event already ended");
                        }else if(isEventValid(from, by)){
                            throw new DukeException("The end date of your event is earlier than the starting date.");
                        }else{
                            Event task = new Event(taskName, dateString(from), dateString(by));
                            return new AddCommand("event",task);
                        }

                    }
                }

            }
            //deadline
            else if (wordList[0].equalsIgnoreCase("deadline")) {
                StringBuilder taskNameBuilder = new StringBuilder();
                StringBuilder byBuilder = new StringBuilder();
                if(wordList.length == 1){
                    throw new DukeException("Please give your deadline a name");
                }else{
                    boolean byChecker = false;
                    //Check From stage and to stage
                    for (int i = 0; i < wordList.length; i++) {
                        if(wordList[1].equalsIgnoreCase("/by")){
                            throw new DukeException("Please give your deadline a name");
                        }
                        else if(wordList[i].equalsIgnoreCase("/by")){
                            if(i+1 < wordList.length){
                                byChecker = true;
                                break;
                            }else{
                                throw new DukeException("Can you tell me the due date?");
                            }
                        }
                    }
                    //Handle error
                    if(!byChecker){
                        throw new DukeException("Your deadline format seems wrong, please try following pattern:\n" +
                                "deadline + deadline Name + /by + Date");
                    }else{
                        byChecker = false;
                        for (String item : wordList) {
                            if (item.equalsIgnoreCase("deadline")) {
                                continue;
                            } else if (item.equalsIgnoreCase("/by")) {
                                byChecker = true;
                                continue;
                            }
                            if (!byChecker) {
                                taskNameBuilder.append(item).append(" ");
                            } else {
                                byBuilder.append(item).append(" ");
                            }
                        }
                        taskName = taskNameBuilder.toString();
                        by = byBuilder.toString();
                        if(isDateValid(by)){
                            Deadline task = new Deadline(taskName, dateString(by));
                            return new AddCommand("deadline",task);
                        }else{
                            throw new DukeException("Your Deadline Date is not a valid date or earlier than today");
                        }


                    }
                }

            }
            //mark
            else if(wordList[0].equalsIgnoreCase("mark") ||
                    wordList[0].equalsIgnoreCase("unmark")){

                if(wordList.length == 1){
                    throw new DukeException("Please tell me which task you would like to mark/unmark");
                }else{
                    String checkNum = wordList[1];
                    if(isInteger(checkNum)){
                        return new MarkCommand(wordList[0], checkNum);
                    }else{
                        throw new DukeException("Please tell me which number of task you would like to mark/unmark");
                    }
                }
            }
            //delete
            else if(wordList[0].equalsIgnoreCase("delete")){
                if(wordList.length == 1){
                    throw new DukeException("Please tell me which task you would like to delete");
                }else{
                    String checkNum = wordList[1];
                    if(isInteger(checkNum)){
                        return new DeleteCommand("delete", checkNum);
                    }else{
                        throw new DukeException("Please tell me which number of task you would like to delete");
                    }
                }
            }
            //find
            else if(wordList[0].equalsIgnoreCase("find")){
                if(wordList.length == 1){
                    throw new DukeException("Please tell me the keywords of the task you would like to find");
                }else{
                    String keyWord = Storage.combineArray(wordList);
                    return new FindCommand("find", keyWord);
                }
            }
            //date
            else if(wordList[0].equalsIgnoreCase("date")){
                if(wordList.length == 1){
                    throw new DukeException("Please tell me a specific date you would like to search");
                }else if(wordList.length == 2 || wordList.length == 3) {
                    String dateString = Storage.combineArray(wordList);
                    assert dateString != null;
                    LocalDateTime date = DateTime.checkDate(dateString);
                    return new DateCommand("find", date);
                }else{
                    throw new DukeException("Your date format seems wrong. please try following pattern " +
                            "date + yyyy-MM-dd or date + yyyy-MM-dd HHmm");
                }

            }
            //quit
            else if(wordList[0].equalsIgnoreCase("bye")  ||
                    wordList[0].equalsIgnoreCase("quit")  ){// Single command no need to check
                return new ExitCommand("exit");
            }
            //list
            else if(wordList[0].equalsIgnoreCase("list")){
                return new ListCommand("list");
            }
            else {
                format = false;
                throw new DukeException("I don't get it, I prepared following functions for you.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());// Print the error message
            if(!format){
                new Ui().helpMenu();
            }
            return new InvalidCommand("Error");
        }
    }

}
