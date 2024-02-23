import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Action {
    private Hashtable<String,String> fields;
    public abstract void execute();

    public Hashtable<String, String> getFields() {
        return fields;
    }

    public void setFields(Hashtable<String, String> fields) {
        this.fields = fields;
    }
}
