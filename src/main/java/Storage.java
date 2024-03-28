import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {
    private final String filePath;

=======
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles storage operations for tasks in the Duke application.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
>>>>>>> Stashed changes
    public Storage(String filePath) {
        this.filePath = filePath;
    }

<<<<<<< Updated upstream
=======
    /**
     * Loads tasks from a file and adds them to the given task list.
     *
     * @param filePath  The file path to load tasks from.
     * @param taskList  The task list to which loaded tasks will be added.
     */
>>>>>>> Stashed changes
    public static void loadTasksFromFile(String filePath, TaskList taskList) {
        File file = new File(filePath);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTaskFromString(line);
                    taskList.add(task);
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
                }
            } catch (IOException e) {
                System.out.println("Error reading the file");
            }
        }
<<<<<<< Updated upstream

    }


=======
    }

    /**
     * Saves tasks from the given task list to the file.
     *
     * @param taskList The task list containing tasks to be saved.
     */
>>>>>>> Stashed changes
    public void saveTasks(TaskList taskList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList.tasks) {
                String data = TaskList.toFileStrings(task);
                writer.write(data + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file");
        }
    }
}