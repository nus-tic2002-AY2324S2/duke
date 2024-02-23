import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.lang.Math;

public class SyntaxChecker {
    private List<String> tokens;

    public SyntaxChecker(String input){
        tokens = new ArrayList<String>(Arrays.asList(input.split(" ")));
        //System.out.println(tokens.toString());
    }

    public void checkSyntax() throws BigChungusException.InvalidListSyntaxException
            , BigChungusException.InvalidMarkSyntaxException
            , BigChungusException.InvalidTodoSyntaxException
            , BigChungusException.InvalidDeadlineSyntaxException
            , BigChungusException.InvalidEventSyntaxException
            , BigChungusException.InvalidMarkNumberException
    {
        if(tokens.get(0).equals("list")){
            checkListSyntax();
        }
        else if(tokens.get(0).equals("mark") || tokens.get(0).equals("unmark")){
            checkTaskNumSyntax();
        }
        else if(tokens.get(0).equals("todo")){
            checkTodoSyntax();
        }
        else if(tokens.get(0).equals("deadline")){
            checkDeadlineSyntax();
        }
        else if(tokens.get(0).equals("event")){
            checkEventSyntax();
        }

    }

    private void checkListSyntax() throws BigChungusException.InvalidListSyntaxException {
        if(getTokens().size() != 1 && !getTokens().get(0).equals("list")){
            throw new BigChungusException.InvalidListSyntaxException();
        }
    }
    private void checkTaskNumSyntax() throws BigChungusException.InvalidMarkSyntaxException, BigChungusException.InvalidMarkNumberException {

        if(getTokens().size() != 2){
            throw new BigChungusException.InvalidMarkSyntaxException();
        }
        int num = -1;
        try{
            num = Integer.parseInt(getTokens().get(1));
        }
        catch (NumberFormatException e){
            throw new BigChungusException.InvalidMarkSyntaxException();
        }
        if(num < 1 || num > BigChungus.tasks.size()){
            throw new BigChungusException.InvalidMarkNumberException();
        }
    }
    private void checkTodoSyntax() throws BigChungusException.InvalidTodoSyntaxException {
        if(getTokens().size() < 2){
            throw new BigChungusException.InvalidTodoSyntaxException();
        }
    }

    private void checkDeadlineSyntax() throws BigChungusException.InvalidDeadlineSyntaxException {
        if(!checkSyntaxWithKeyword("deadline")){
            throw new BigChungusException.InvalidDeadlineSyntaxException();
        }
    }

    private void checkEventSyntax() throws BigChungusException.InvalidEventSyntaxException {
        if(!checkSyntaxWithKeyword("event")){
            throw new BigChungusException.InvalidEventSyntaxException();
        }
    }

    private boolean checkSyntaxWithKeyword(String action){
        String[] keywords;
        if(action.equals("deadline")){
            keywords = SyntaxKeyword.getDeadlineKeywords();
        }
        else if(action.equals("event")){
            keywords = SyntaxKeyword.getEventKeywords();
        }
        else{
            return false;
        }
        System.out.println(Arrays.toString(keywords));
        List<Integer> keywordsIndex = new ArrayList<Integer>();
        int maxIndex = 0;
        for(String keyword : keywords) {
            maxIndex = Math.max(maxIndex, getTokens().indexOf(keyword));
            keywordsIndex.add(getTokens().indexOf(keyword));
            if(!getTokens().contains(keyword)){ // keyword missing
                return false;
            }
            if(getTokens().indexOf(keyword) == 1){ // no description
                return false;
            }
        }
        Collections.sort(keywordsIndex);
        for(int i = 0; i < keywordsIndex.size() - 1; i++) {
            int diff = keywordsIndex.get(i + 1) - keywordsIndex.get(i);
            if(diff != 2){
                return false;
            }
        }
        System.out.println("size: " + getTokens().size());
        System.out.println("maxIndex + 1: " + (maxIndex + 1));
        if(getTokens().size() != (maxIndex + 2)){ // check if there's any text after the last keyword
            return false;
        }
        return true;
    }
    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
}
