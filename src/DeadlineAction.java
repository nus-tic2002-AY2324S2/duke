import java.util.Hashtable;

public class DeadlineAction extends Action {

    public DeadlineAction(Hashtable<String, String> fields) {
        super.setFields(fields);
    }

    @Override
    public void execute() {
        Deadline deadline = new Deadline(this.getFields());
        BigChungus.tasks.add(deadline);
        System.out.println("added deadline: " + deadline.toString());
    }
}
