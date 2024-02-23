import java.util.Hashtable;

public class ListAction extends Action{

    public ListAction(Hashtable<String,String> fields){
        super.setFields(fields);
    }
    @Override
    public void execute(){
        System.out.println("listing tasks");
        int taskNum = 0;
        for(Task t : BigChungus.tasks){
            taskNum++;
            System.out.printf("%d. %s%n", taskNum, t.toString());
        }
    }
}
