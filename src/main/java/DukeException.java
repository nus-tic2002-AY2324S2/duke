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

    public static int checkTaskIndex(String userInput, int maxIndex) throws DukeException {
        int index = Integer.parseInt(userInput.trim()) - 1;
        if (maxIndex < 0){
            throw new DukeException("Error! There are no task in the list");
        }
        if (index > maxIndex){
            throw new DukeException("Index not found! Choose a valid index!");
        }
        return index;
    }

}

