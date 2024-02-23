import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionCreator {
    /*
    public static Action createAction(String input) throws
            BigChungusException.InvalidActionException
            , BigChungusException.InvalidDeadlineSyntaxException
            , BigChungusException.InvalidTodoSyntaxException
            , BigChungusException.InvalidEventSyntaxException
            , BigChungusException.InvalidMarkUnmarkSyntaxException
            , BigChungusException.InvalidListSyntaxException
            , BigChungusException.InvalidMarkUnmarkNumberException {
        List<String> tokens = new ArrayList<String>(Arrays.asList(input.split(" ")));
        String action = tokens.get(0);
        SyntaxChecker syntaxChecker = new SyntaxChecker(input);
        if (action.equals("list")) {
            syntaxChecker.checkListSyntax();
            return new ListAction(input);
        }

        else if (action.equals("deadline")) {
            syntaxChecker.checkDeadlineSyntax();
            return new DeadlineAction(input);
        }
        else if(action.equals("todo")){
            syntaxChecker.checkTodoSyntax();
            return new TodoAction(input);
        }
        else if(action.equals("event")){
            syntaxChecker.checkEventSyntax();
            return new EventAction(input);
        }
        else if(action.equals("mark")){
            syntaxChecker.checkMarkSyntax();
            return new MarkAction(input);
        }
        else if(action.equals("unmark")){
            syntaxChecker.checkMarkSyntax();
            return new UnmarkAction(input);
        }
        throw new BigChungusException.InvalidActionException();
    }
    */
}
