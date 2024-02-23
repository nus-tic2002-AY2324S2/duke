import java.util.Hashtable;

public class Todo extends Task {
    public Todo(Hashtable<String,String> fields){
        super(fields.get("desc"));
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
