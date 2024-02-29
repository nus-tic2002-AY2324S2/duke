package duke.exception;

public class DukeException  extends Exception{
    public DukeException(String error){
        super(error);
    }

    /**
     * Helper method to check if string input is number
     * isInteger() method will check input if it is an Integer
     * Reference: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
     */

    public static boolean isInteger(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Helper method to check if string input is boolean
     * isBoolean() method will check input if it is a boolean
    */

    public static boolean isBoolean(String input) throws DukeException {
        input = input.toLowerCase();
        if (input.contains("false") || input.contains("true")){
            return input.equals("true");
        }else{
            throw new DukeException("Enter either true or false values. True - Task completed.");
        }
    }

    /**
     * Helper method to check if the number of parameters in the command is correct
     */
    public static void checkNumParameters(String input, int num) throws DukeException {
        if (input.split("_").length != num){
            throw new DukeException(String.format("Command expects " + String.valueOf(num) + " parameters"));
        }
    }

    /**
     * Helper method to check if the index to be updated is valid
     */
    public static int getIndex(String inputSplit, int maxIndex) throws DukeException {
        int index;

        if (!DukeException.isInteger(inputSplit.trim())){
            throw new DukeException("Parameter must be an integer!");
        }

        index = Integer.parseInt(inputSplit.trim()) - 1;

        if (maxIndex < 0){
            throw new DukeException("Error! There are no task in the list");
        }
        if (index > maxIndex){
            throw new DukeException("Index not found! Choose a valid index!");
        }
        return index;
    }
}
