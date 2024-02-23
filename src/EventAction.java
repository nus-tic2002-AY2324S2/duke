import java.util.Hashtable;
import java.util.List;
public class EventAction extends Action{

    public EventAction(Hashtable<String,String> fields){
        super.setFields(fields);
    }
    public void execute() {
        //event project meeting /sd 11/11/11 /st 11pm /ed 11/12/11 /et 12pm
        Event event = new Event(this.getFields());
        BigChungus.tasks.add(event);
        System.out.println("added event: " + event.toString());
    }
}
