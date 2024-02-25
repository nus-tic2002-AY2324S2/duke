public class BigChungusException extends Exception {

    String base = "action invalid. usage example:";

    public static class InvalidActionSyntaxException extends Exception{
        private static String computeActionExceptionMessage(String action){
            String base = "action invalid. usage example:";
            String usage = "";
            String fnl = String.format("%s %s %s", action, base, usage);
            if(action.equals("event")){
                usage = "event [desc] /sd [date] /ed [date] /st [time] /et [time]";
                fnl = String.format("%s %s %s", action, base, usage);
            }
            if(action.equals("deadline")){
                usage = "deadline [desc] /ed [date] /et [time]";
                fnl = String.format("%s %s %s", action, base, usage);
            }
            if(action.equals("todo")) {
                usage = "todo [desc]";
                fnl = String.format("%s %s %s", action, base, usage);

            }
            if(action.equals("mark") || action.equals("unmark")) {
                usage = "[mark | unmark] [num]";
                fnl = String.format("%s %s %s", action, base, usage);
            }
            return fnl;
        }
        public InvalidActionSyntaxException(String action) {
            super(computeActionExceptionMessage(action));
        }
    }

    public static class InvalidEventSyntaxException extends Exception{
        public InvalidEventSyntaxException(){
            super("event syntax invalid. usage example: event <desc> /sd <date> /st <time> /ed <time> /et <time>");
        }
    }

    public static class InvalidDeadlineSyntaxException extends Exception{
        public InvalidDeadlineSyntaxException(){
            super("deadline syntax invalid. usage example: deadline <desc> /ed <date> /et <time>");
        }
    }

    public static class InvalidTodoSyntaxException extends Exception{
        public InvalidTodoSyntaxException(){
            super("todo syntax invalid. usage example: todo <desc>");
        }
    }

    public static class InvalidMarkSyntaxException extends Exception{
        public InvalidMarkSyntaxException(){
            super("mark/unmark syntax invalid. usage example: <mark | unmark> <num>");
        }
    }
    public static class InvalidActionException extends Exception {
        public InvalidActionException() {
            super("action keyword not found. available actions: <list | mark | ummark | todo | deadline | event | delete >");
        }
    }

    public static class InvalidListSyntaxException extends Exception{
        public InvalidListSyntaxException(){
            super("list syntax invalid. usage example: list");
        }
    }

    public static class InvalidMarkNumberException extends Exception{
        public InvalidMarkNumberException(){
            super("Mark/Unmark number entered is invalid. Ensure number entered is in the numbered list");
        }
    }
}




