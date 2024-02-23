import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class SyntaxParser {
    private List<String> tokens;

    public SyntaxParser(String input) {
        tokens = new ArrayList<String>(Arrays.asList(input.split(" ")));
    }

    public Hashtable<String, String> Parse(){
        Hashtable<String,String> fields = new Hashtable<>();
        String action = tokens.get(0);
        fields.put("action", action);
        if(action.equals("list")){
            String desc = String.join(" ", tokens.subList(1, tokens.size()));
            fields.put("desc", desc);
        }
        else if(action.equals("unmark") || action.equals("mark") || action.equals("delete")){
            String num = tokens.get(1);
            fields.put("num", num);
        }
        else if(action.equals("todo")){
            String desc = String.join(" ", tokens.subList(1, tokens.size()));
            fields.put("desc", desc);
        }
        else if(action.equals("deadline")){
            int edIndex = tokens.indexOf(SyntaxKeyword.endDateKeyword);
            int etIndex = tokens.indexOf(SyntaxKeyword.endTimeKeyword);
            int firstKeywordIndex = Math.min(edIndex, etIndex);
            String desc = String.join(" ", tokens.subList(1, firstKeywordIndex));
            String ed = tokens.get(edIndex + 1);
            String et = tokens.get(etIndex + 1);
            fields.put("desc", desc);
            fields.put("ed", ed);
            fields.put("et", et);
        }
        else if(action.equals("event")){
            int sdIndex = this.getTokens().indexOf(SyntaxKeyword.startDateKeyword);
            int stIndex = this.getTokens().indexOf(SyntaxKeyword.startTimeKeyword);
            int edIndex = this.getTokens().indexOf(SyntaxKeyword.endDateKeyword);
            int etIndex = this.getTokens().indexOf(SyntaxKeyword.endTimeKeyword);
            int firstKeywordIndex = Math.min(sdIndex, stIndex);
            firstKeywordIndex = Math.min(firstKeywordIndex, edIndex);
            firstKeywordIndex = Math.min(firstKeywordIndex, etIndex);
            String desc = String.join(" ", tokens.subList(1, firstKeywordIndex));
            String sd = tokens.get(sdIndex + 1);
            String st = tokens.get(stIndex + 1);
            String ed = tokens.get(edIndex + 1);
            String et = tokens.get(etIndex + 1);
            fields.put("desc", desc);
            fields.put("sd", sd);
            fields.put("st", st);
            fields.put("ed", ed);
            fields.put("et", et);
        }
        return fields;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
}
