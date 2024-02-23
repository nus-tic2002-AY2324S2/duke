import java.util.Hashtable;

public class ActionRunner {
    public static void run(Hashtable<String,String> fields) throws BigChungusException.InvalidActionException {
        String action = fields.get("action");
        if (action.equals("list")){
            ListAction act = new ListAction(fields);
            act.execute();
        }
        else if (action.equals("mark")){
            MarkAction act = new MarkAction(fields);
            act.execute();
        }
        else if(action.equals("unmark")){
            UnmarkAction act = new UnmarkAction(fields);
            act.execute();
        }
        else if(action.equals("todo")){
            TodoAction act = new TodoAction(fields);
            act.execute();
        }
        else if(action.equals("deadline")){
            DeadlineAction act = new DeadlineAction(fields);
            act.execute();
        }
        else if(action.equals("event")){
            EventAction act = new EventAction(fields);
            act.execute();
        }
        else if(action.equals("delete")){
            DeleteAction act = new DeleteAction(fields);
            act.execute();
        }
        else{
            throw new BigChungusException.InvalidActionException();
        }
    }
}
