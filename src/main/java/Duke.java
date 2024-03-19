public class Duke {
    public static void main(String[] args){
        TaskList.readTasksFromFile();
        Ui.greetUser();
        Parser.runDuke();
        Ui.sayGoodbye();
    }
}
