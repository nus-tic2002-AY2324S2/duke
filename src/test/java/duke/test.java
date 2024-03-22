package src.test;

public class test {

}

/*
public static String checkUserInput(String userInput) {
        String[] wordList = userInput.split(" ");
        try {
            if (wordList[0].equalsIgnoreCase("todo")) {
                if(wordList.length == 1){
                    throw new DukeException("Please give your task a name");
                }else{
                    return null;// No error
                }
            }
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
                                continue;
                            }else{
                                throw new DukeException("Can you tell me about the start date of this event?");
                            }
                        }
                        else if(wordList[i].equalsIgnoreCase("/to")){
                            if(i+1 < wordList.length && !wordList[i+1].equalsIgnoreCase("/from")){
                                toChecker = true;
                                continue;
                            }else{
                                throw new DukeException("Can you tell me about the end date of this event?");
                            }
                        }
                    }
                    //Handle error
                    if(fromChecker == false || toChecker == false){
                        throw new DukeException("Your event format seems wrong, please try following pattern:\n" +
                                "event + event Name + /from + Date + /to + Date");
                    }else{
                        return null;
                    }
                }

            }
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
                        return null;// No error
                    }
                }

            }
            else if(wordList[0].equalsIgnoreCase("mark") ||
                    wordList[0].equalsIgnoreCase("unmark")){
                if(wordList.length == 1){
                    throw new DukeException("Please tell me which task you would like to mark/unmark");
                }else{
                    String checkNum = wordList[1];
                    if(isInteger(checkNum)){
                        return null;// No error
                    }else{
                        throw new DukeException("Please tell me which number of task you would like to mark/unmark");
                    }
                }
            }
            else if(wordList[0].equalsIgnoreCase("delete")){
                if(wordList.length == 1){
                    throw new DukeException("Please tell me which task you would like to delete");
                }else{
                    String checkNum = wordList[1];
                    if(isInteger(checkNum)){
                        return null;// No error
                    }else{
                        throw new DukeException("Please tell me which number of task you would like to delete");
                    }
                }
            }
            else if(wordList[0].equalsIgnoreCase("list") ||
                    wordList[0].equalsIgnoreCase("bye")  ||
                    wordList[0].equalsIgnoreCase("quit")  ){// Single command no need to check
                return null;
            }
            else {
                format = false;
                throw new DukeException("I don't get it, I prepared following functions for you.");
            }
        } catch (DukeException e) {
            return e.getMessage();// Return the error message
        }
    }
 */




/*
public void run1() {
        String userInput = new String();
        Scanner in = new Scanner(System.in);
        ui.welcomeMenu();
        ui.listMenu(todoList);
        System.out.println("How can I help you today?");
        //Start running
        while (true) {
            in = new Scanner(System.in);
            userInput = in.nextLine();
            String[] wordList = userInput.split(" ");
            String taskName = new String();
            String by = new String();
            String from = new String();
            format = true;

            //Check error function
            String errorMessage = checkUserInput(userInput);
            if (errorMessage != null) {
                System.out.println(errorMessage);
                if (format == false) {
                    ui.helpMenu();
                }
                continue; // Continue the loop if there's an error
            }

            //Proceed to main function if input no error
            //Todo function
            if (wordList[0].equalsIgnoreCase("todo")) {
                taskName = storage.combineArray(wordList);
                Todo newTask = new Todo(taskName);
                todoList.add(newTask);
                newTask.setEventType('T');

                System.out.println("Alright, added " + taskName + " into todo list");
                System.out.println("   " + newTask.toString());
                System.out.println("You have " + todoList.size() + " things now in your todo list");

            }
            //List function
            else if (wordList[0].equalsIgnoreCase("list")) {
                ui.listMenu(todoList);
            }
            //Mark function
            else if (wordList[0].equalsIgnoreCase("mark") || wordList[0].equalsIgnoreCase("unmark")) {
                int itemNum = Integer.parseInt(wordList[1]);
                boolean status = false;
                if (wordList[0].equalsIgnoreCase("mark")) {
                    status = true;
                }
                if (todoList.size() == 0) {
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//        You have nothing to mark        //");
                    System.out.println("////////////////////////////////////////////");
                } else {
                    if (itemNum >= 1 && itemNum <= todoList.size()) {
                        if (todoList.get(itemNum - 1).getStatus() == status && todoList.get(itemNum - 1).getStatus() == true) {
                            System.out.println("The task " + itemNum + " is already marked as done");
                        } else if (todoList.get(itemNum - 1).getStatus() == status && todoList.get(itemNum - 1).getStatus() == false) {
                            System.out.println("The task " + itemNum + " is already marked as not done");
                        } else {
                            if (status == true) {
                                System.out.println("Okay, I've marked task " + itemNum + " as done");
                            } else {
                                System.out.println("Okay, I've marked task " + itemNum + " as not done yet");
                            }
                            todoList.get(itemNum - 1).setStatus(status);
                            System.out.println("   " + "[" + todoList.get(itemNum - 1).getStatusIcon() + "] " + todoList.get(itemNum - 1).getTaskName());

                        }
                    } else {
                        System.out.println("No such task in your list :(");
                    }

                }
            }
            //Event function
            else if (wordList[0].equalsIgnoreCase("event")) {
                String stage = "name";
                //Seprate the input and record the eventName, EventFrom, EventTo
                boolean fromChecker = false;
                boolean toChecker = false;
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
                Event newEvent = new Event(taskName, from, by);
                todoList.add(newEvent);
                newEvent.setEventType('E');
                System.out.println("Alright, added " + taskName + " into todo list");
                System.out.println("   " + newEvent.toString());
                System.out.println("You have " + todoList.size() + " things now in your todo list");

            }
            //Deadline function
            else if (wordList[0].equalsIgnoreCase("deadline")) {
                //Seprate the input and record the deadlineName, deadlineBy
                boolean byChecker = false;
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
                Deadline newDeadline = new Deadline(taskName, by);
                todoList.add(newDeadline);
                newDeadline.setEventType('D');
                System.out.println("Alright, added " + taskName + " into todo list");
                System.out.println("   " + newDeadline.toString());
                System.out.println("You have " + todoList.size() + " things now in your todo list");
            }
            //Delete function
            else if (wordList[0].equalsIgnoreCase("delete")) {
                int itemNum = Integer.parseInt(wordList[1]);
                if (todoList.size() == 0) {
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//       You have nothing to delete       //");
                    System.out.println("////////////////////////////////////////////");
                } else {
                    if (itemNum >= 1 && itemNum <= todoList.size()) {
                        System.out.println("Okay, I've remove following task:");
                        System.out.println("   " + todoList.get(itemNum - 1).toString());
                        todoList.remove(itemNum - 1);
                        System.out.println("You have " + todoList.size() + " things now in your todo list");
                    } else {
                        System.out.println("No such task in your list :(");
                    }
                }
            }
            //Exit program
            else if (userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("quit")) {
                System.out.println("Bye! See you next time!");
                storage.writeToFile(todoList);
                break;
            } else {break;}//Code should never reach here.
        }
    }
 */



/*
public void run1() {
        String userInput = new String();
        Scanner in = new Scanner(System.in);
        ui.welcomeMenu();
        ui.listMenu(todoList);
        System.out.println("How can I help you today?");
        //Start running
        while (true) {
            in = new Scanner(System.in);
            userInput = in.nextLine();
            String[] wordList = userInput.split(" ");
            String taskName = new String();
            String by = new String();
            String from = new String();
            format = true;

            //Check error function
            String errorMessage = checkUserInput(userInput);
            if (errorMessage != null) {
                System.out.println(errorMessage);
                if (format == false) {
                    ui.helpMenu();
                }
                continue; // Continue the loop if there's an error
            }

            //Proceed to main function if input no error
            //Todo function
            if (wordList[0].equalsIgnoreCase("todo")) {
                taskName = storage.combineArray(wordList);
                Todo newTask = new Todo(taskName);
                todoList.add(newTask);
                newTask.setEventType('T');

                System.out.println("Alright, added " + taskName + " into todo list");
                System.out.println("   " + newTask.toString());
                System.out.println("You have " + todoList.size() + " things now in your todo list");

            }
            //List function
            else if (wordList[0].equalsIgnoreCase("list")) {
                ui.listMenu(todoList);
            }
            //Mark function
            else if (wordList[0].equalsIgnoreCase("mark") || wordList[0].equalsIgnoreCase("unmark")) {
                int itemNum = Integer.parseInt(wordList[1]);
                boolean status = false;
                if (wordList[0].equalsIgnoreCase("mark")) {
                    status = true;
                }
                if (todoList.size() == 0) {
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//        You have nothing to mark        //");
                    System.out.println("////////////////////////////////////////////");
                } else {
                    if (itemNum >= 1 && itemNum <= todoList.size()) {
                        if (todoList.get(itemNum - 1).getStatus() == status && todoList.get(itemNum - 1).getStatus() == true) {
                            System.out.println("The task " + itemNum + " is already marked as done");
                        } else if (todoList.get(itemNum - 1).getStatus() == status && todoList.get(itemNum - 1).getStatus() == false) {
                            System.out.println("The task " + itemNum + " is already marked as not done");
                        } else {
                            if (status == true) {
                                System.out.println("Okay, I've marked task " + itemNum + " as done");
                            } else {
                                System.out.println("Okay, I've marked task " + itemNum + " as not done yet");
                            }
                            todoList.get(itemNum - 1).setStatus(status);
                            System.out.println("   " + "[" + todoList.get(itemNum - 1).getStatusIcon() + "] " + todoList.get(itemNum - 1).getTaskName());

                        }
                    } else {
                        System.out.println("No such task in your list :(");
                    }

                }
            }
            //Event function
            else if (wordList[0].equalsIgnoreCase("event")) {
                String stage = "name";
                //Seprate the input and record the eventName, EventFrom, EventTo
                boolean fromChecker = false;
                boolean toChecker = false;
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
                Event newEvent = new Event(taskName, from, by);
                todoList.add(newEvent);
                newEvent.setEventType('E');
                System.out.println("Alright, added " + taskName + " into todo list");
                System.out.println("   " + newEvent.toString());
                System.out.println("You have " + todoList.size() + " things now in your todo list");

            }
            //Deadline function
            else if (wordList[0].equalsIgnoreCase("deadline")) {
                //Seprate the input and record the deadlineName, deadlineBy
                boolean byChecker = false;
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
                Deadline newDeadline = new Deadline(taskName, by);
                todoList.add(newDeadline);
                newDeadline.setEventType('D');
                System.out.println("Alright, added " + taskName + " into todo list");
                System.out.println("   " + newDeadline.toString());
                System.out.println("You have " + todoList.size() + " things now in your todo list");
            }
            //Delete function
            else if (wordList[0].equalsIgnoreCase("delete")) {
                int itemNum = Integer.parseInt(wordList[1]);
                if (todoList.size() == 0) {
                    System.out.println("////////////////////////////////////////////");
                    System.out.println("//       You have nothing to delete       //");
                    System.out.println("////////////////////////////////////////////");
                } else {
                    if (itemNum >= 1 && itemNum <= todoList.size()) {
                        System.out.println("Okay, I've remove following task:");
                        System.out.println("   " + todoList.get(itemNum - 1).toString());
                        todoList.remove(itemNum - 1);
                        System.out.println("You have " + todoList.size() + " things now in your todo list");
                    } else {
                        System.out.println("No such task in your list :(");
                    }
                }
            }
            //Exit program
            else if (userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("quit")) {
                System.out.println("Bye! See you next time!");
                storage.writeToFile(todoList);
                break;
            } else {break;}//Code should never reach here.
        }
    }
 */
