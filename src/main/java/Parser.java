package src.main.java;
import src.commands.*;
import src.storage.Storage;
import src.task.Deadline;
import src.task.Event;
import src.task.Todo;
import src.ui.Ui;


import static src.main.java.DukeException.isInteger;


public class Parser {

    private Command command;
    public static Command parse(String userInput) throws DukeException {
        String[] wordList = userInput.split(" ");
        String taskName = "";
        String by = "";
        String from = "";
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
                    }else{//No error
                        String stage = "name";
                        //Separate the input and record the eventName, EventFrom, EventTo
                        fromChecker = false;
                        toChecker = false;
                        for (String item : wordList) {
                            if (item.equalsIgnoreCase("event")) {
                                continue;
                            } else if (item.equalsIgnoreCase("/from")) {
                                stage = "from";
                                fromChecker = true;
                                continue;
                            } else if (item.equalsIgnoreCase("/to")) {
                                stage = "to";
                                toChecker = true;
                                continue;
                            }
                            if (stage.equalsIgnoreCase("name")) {
                                taskName += item;
                                taskName += " ";
                            } else if (stage.equalsIgnoreCase("from")) {
                                from += item;
                                from += " ";

                            } else if (stage.equalsIgnoreCase("to")) {
                                by += item;
                                by += " ";
                            }
                        }
                        Event task = new Event(taskName, from, by);
                        return new AddCommand("event",task);
                    }
                }

            }
            //deadline
            else if (wordList[0].equalsIgnoreCase("deadline")) {
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
                    if(byChecker == false){
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
                                taskName += item;
                                taskName += " ";
                            } else {
                                by += item;
                                by += " ";
                            }
                        }
                        Deadline task = new Deadline(taskName, by);
                        return new AddCommand("deadline",task);
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
            //quit
            else if(wordList[0].equalsIgnoreCase("bye")  ||
                    wordList[0].equalsIgnoreCase("quit")  ){// Single command no need to check
                return new ExitCommand("exit");
            }else if(wordList[0].equalsIgnoreCase("list")){
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
