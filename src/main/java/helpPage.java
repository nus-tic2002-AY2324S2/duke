public class helpPage {

    public static void show() {
        System.out.println("Welcome to Duke!");
        System.out.println("Duke is a simple task management application.");
        System.out.println("Here are the available commands:");
        System.out.println("- help: shows a help page, the one you're seeing now!");
        System.out.println("- todo <description>: Add a new todo task");
        System.out.println("- event <description> /from <startDateTime> /to <endDateTime>: Add a new event task");
        System.out.println("- deadline <description> /by <dueDateTime>: Add a new deadline task");
        System.out.println("- list: List all tasks");
        System.out.println("- find <description> or <date>: find tasks that contains the description given or tasks that occurs in range of date given");
        System.out.println("- mark <taskNumber>: Mark a task as done");
        System.out.println("- delete <taskNumber>: Delete a task");
        System.out.println("- bye: Exit the application");
    }
}
