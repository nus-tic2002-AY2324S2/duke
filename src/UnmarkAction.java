import java.util.Hashtable;

public class UnmarkAction extends Action{

    public UnmarkAction(Hashtable<String,String> fields){
        super.setFields(fields);
    }
    public void execute() {
        int index = Integer.parseInt(this.getFields().get("num"));
        Task task = BigChungus.tasks.get(index - 1);
        task.setDone(false);
        System.out.printf("task %o undone: %s%n", index, task.toString());
    }
}
