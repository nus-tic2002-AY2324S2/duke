import java.util.Hashtable;

public class DeleteAction extends Action{

    public DeleteAction(Hashtable<String,String> fields){
        super.setFields(fields);
    }
    public void execute() {
        int index = Integer.parseInt(this.getFields().get("num"));
        Task task = BigChungus.tasks.remove(index - 1);
        task.setDone(false);
        System.out.printf("task %d deleted: %s%n", index, task.toString());
    }
}
