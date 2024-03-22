import src.storage.Storage;
import src.task.*;
import src.ui.Ui;
import src.commands.*;
import src.main.java.Parser;
import src.main.java.DukeException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    private TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    /***
     * Function to initiate the UI functions, storage and task list
     * @param filePath indicate the storage file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        taskList.setList(storage.readFromFile());
    }

    /***
     * Function to run the Duke program
     */
    public void run() throws DukeException {
        ui.welcomeMenu();
        ui.listMenu(taskList.getList());
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(taskList, ui, storage);
            isExit = c.Exit();
        }
    }
    public static void main(String[] args) throws DukeException {
        new Duke("./data/task.txt").run();
    }
}
