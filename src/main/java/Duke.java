import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;





public class Duke {

    public static void main(String[] args) {
        //Generated via https://patorjk.com/
        String logo =  "_______          __     _____ __________        __\n" +
                "\\      \\   _____/  |_  /  _  \\______   \\ _____/  |_ \n" +
                "/   |   \\ /  _ \\   __\\/  /_\\  \\|    |  _//  _ \\   __\\ \n" +
                "/    |    (  <_> )  | /    |    \\   |   (  <_> )  |  \n" +
                "\\____|__  /\\____/|__| \\____|__  /______  /\\____/|__|  \n";

        String greeting = "Hello! I'm NotABot \n" +
                        "What can I do for you?";
        String ending = "Bye. Hope to see you again soon";
        String userInput;
        String taskNo = null;
        //String[] arrayInput = new String[100];
        //Task[ ] arrayInput = new Task[100];
        ArrayList <Task> arrayInput = new ArrayList<Task>();


        int arrayIndex = 0 ;
        boolean validWord = true;
        boolean validInput =true;

        Scanner scGreeting = new Scanner(System.in);

        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);




        while(true)
        {
            userInput = scGreeting.nextLine();
            //splits into and descriptions when user input
            validInput = true;
            if(userInput.length() > 2)
            {
                String[] temp = userInput.split(" ",2);
                try
                {
                    if(userInput.startsWith(" "))
                    // checks for space starting and input must be at least 3
                    {
                        validWord = false;
                        throw new DukeException("Invalid input");
                    }
                }
                catch (DukeException e){
                    System.err.println("Invalid input , No starting with space");
                    validInput =false;
                }

                switch (temp[0].toLowerCase()){
                    case "mark":// need check if valid task no
                        userInput= temp[0];
                        taskNo = temp[1];
                        break;
                    case "unmark": // need check if valid task no
                        userInput= temp[0];
                        taskNo = temp[1];
                        break;
                    case "todo":
                        if (temp.length == 1) {
                            try{
                                throw new DukeException("Todo Description is empty.Please fix");
                            }catch (DukeException e){
                                validInput =false;
                                System.err.println(e.getMessage());
                            }
                        }
                        else
                        {
                            userInput= temp[0];
                            taskNo = temp[1];
                        }
                        break;
                    case "deadline": // check for valid date
                        // deadline return book /by Sunday syntax
                        userInput= temp[0];
                        taskNo = temp[1];
                        break;
                    case "event": // check for valid time/date
                        // event project meeting /from Mon 2pm /to 4pm  syntax
                        userInput = temp[0];
                        if(temp.length > 1)
                        {
                            taskNo = temp[1];
                        }
                        else
                        {
                            try{
                                throw new DukeException("Event Description is empty.Please fix");
                            } catch (DukeException e) {
                                validInput =false;
                                System.err.println(e.getMessage());
                            }

                        }

                        break;
                    case "list":
                        break;
                    case "delete": // need check if within list
                        if(taskNo.isEmpty())
                        {
                            try{
                                throw new DukeException("Invalid Delete");
                            }catch (DukeException e){
                                validInput =false ;
                                System.err.println(e.getMessage());
                            }
                        }
                        else {
                            userInput= temp[0];
                            taskNo = temp[1];
                        }

                        break;
                    default:
                        try{
                            throw new DukeException("Not part of my commands.Please input a valid one");
                        }catch (DukeException e){
                            validInput =false;
                            System.err.println(e.getMessage());
                        }
                        break;
                }
            }
            ///ends here
            while(validInput){
                validInput =false;
                System.out.println(userInput);
                if(userInput.equals("bye"))
                {
                    System.out.println(ending);
                    System.exit(0);
                }
                else if(userInput.equals("list"))
                {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < arrayInput.size(); i++) {
                        System.out.printf("%d. %s%n", i + 1, arrayInput.get(i).toString());
                    }
                }
                else if((userInput.equals("mark")) || userInput.equals(("unmark")))
                {
                    int number = Integer.parseInt(taskNo);
                    if(userInput.equals("mark"))
                    {
                        arrayInput.get(number -1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                    }
                    else if(userInput.equals("unmark"))
                    {
                        arrayInput.get(number -1).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println(arrayInput.get(number -1).toString());
                }
                else if (userInput.equals("todo"))
                {
                    System.out.println("Got it. I've added this task: " );
                    arrayInput.add(new ToDo(taskNo));
                    System.out.println(arrayInput.getLast());
                    arrayIndex++;
                    System.out.println("Now you have " + arrayIndex + " tasks in the list");
                }
                else if (userInput.equals("deadline"))
                {
                    String [] splitBy = taskNo.split(" /by " , 2);
                    System.out.println("Got it. I've added this task: " );

                    arrayInput.add(new Deadline(splitBy[0],splitBy[1]));
                    System.out.println(arrayInput.getLast());
                    arrayIndex++;
                    System.out.println("Now you have " + arrayIndex + " tasks in the list");

                }
                else if(userInput.equals("event"))
                {
                    String start;
                    String end;
                    System.out.println("Got it. I've added this task: " );
                    //description of event split from date/time
                    String [] splitEvent = taskNo.split(" /from " , 2);
                    //checks if there is start&end date/time
                    String [] splitStartEnd = splitEvent[1].split(" /to ", 2);
                    if(splitStartEnd.length > 1){
                        start = splitStartEnd[0];
                        end = splitStartEnd[1];
                    }
                    else
                    {
                        start = splitStartEnd[0];
                        end = " ";
                    }
                    arrayInput.add(new Event(splitEvent[0],start,end));
                    System.out.println(arrayInput.getLast());
                    arrayIndex++;
                    System.out.println("Now you have " + arrayIndex + " tasks in the list");
                }
                else if(userInput.equals("delete"))
                {
                    int tempDeleteNo = Integer.parseInt(taskNo);
                    System.out.println("Noted. Task has been removed");
                    System.out.println(arrayInput.size());
                    System.out.println(arrayInput.get(tempDeleteNo - 1));
                    arrayInput.remove(tempDeleteNo - 1);
                    arrayIndex -= 1;
                }
                else if(!validWord)
                {
                    validInput =false;
                    System.out.println("Please input in the correct syntax");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~\n");
                }
                else {
                    arrayInput.add(new Task(userInput));
                    arrayIndex++;
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~\n" + "added: " + userInput);
                }

                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~\n");
                }
            }


    }
}

