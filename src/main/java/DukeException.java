package src.main.java;
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
    public static boolean isInteger(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}

