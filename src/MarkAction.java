import java.util.Hashtable;

public class MarkAction extends Action {

    public MarkAction(Hashtable<String,String> fields){
        super.setFields(fields);
    }

    public void execute(){
        int index = Integer.parseInt(this.getFields().get("num"));
        Task task = BigChungus.tasks.get(index - 1);
        task.setDone(true);
        System.out.printf("task %o done: %s%n", index, task.toString());
    }

}
