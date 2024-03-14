import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void loadTasksFromFile(String filePath, TaskList taskList) {
        File file = new File(filePath);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTaskFromString(line);
                    taskList.add(task);


                }
            } catch (IOException e) {
                System.out.println("Error reading the file");
            }
        }

    }


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