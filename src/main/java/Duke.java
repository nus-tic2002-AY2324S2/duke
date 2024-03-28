import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

<<<<<<< Updated upstream
public class Duke {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

=======
/**
 * Duke is a simple task management application that allows users to keep track of tasks.
 * Users can add tasks, mark tasks as done, delete tasks, and view their task list.
 */
public class Duke {
    final Ui ui;
    final TaskList taskList;
    final Storage storage;

    /**
     * Constructs a Duke object.
     * Initializes the user interface, task list, and storage.
     * Loads tasks from the specified file path.
     *
     * @param filePath The file path to load tasks from.
     */
>>>>>>> Stashed changes
    public Duke(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        Storage.loadTasksFromFile(filePath, taskList);
    }

<<<<<<< Updated upstream
    public void run() {
        ui.showWelcomeMessage();


        String input;

        while (true) {
            input = ui.getUserInput();
            if (input.equals("bye")) {
                break;
            }
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
=======
    /**
     * Starts the Duke application.
     * Displays a welcome message and interacts with the user until the user types "bye".
     * Displays a help page when user types "help".
     */
    public void run() {
        ui.showWelcomeMessage();

        String input;


        while (true) {
            input = ui.getUserInput().toLowerCase();

            if (input.equals("bye")) {
                break;
            }
            if (input.equals("help")) {
                 helpPage.show();
            }
            else {
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
            }
>>>>>>> Stashed changes
        }

//        storage.saveTasks(taskList);
        ui.showGoodbyeMessage();
    }

<<<<<<< Updated upstream


=======
    /**
     * Main method to start the Duke application.
     * Creates a Duke object and runs the application.
     */
>>>>>>> Stashed changes
    public static void main(String[] args) {
        new Duke("C:\\Users\\Admin\\Documents\\GitHub\\duke\\src\\main\\java\\data\\tasks.txt").run();
    }
}