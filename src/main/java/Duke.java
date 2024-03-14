import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Duke {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        Storage.loadTasksFromFile(filePath, taskList);
    }

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
        }

//        storage.saveTasks(taskList);
        ui.showGoodbyeMessage();
    }



    public static void main(String[] args) {
        new Duke("C:\\Users\\Admin\\Documents\\GitHub\\duke\\src\\main\\java\\data\\tasks.txt").run();
    }
}