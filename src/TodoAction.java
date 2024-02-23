import java.util.Hashtable;

public class TodoAction extends Action{

    public TodoAction(Hashtable<String,String> fields){
        this.setFields(fields);
    }
    public void execute(){
        Todo todo = new Todo(this.getFields());
        BigChungus.tasks.add(todo);
        System.out.printf("added Todo: %s%n", todo.getDescription());
    }
}
