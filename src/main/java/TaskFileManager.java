import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskFileManager {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            Path filePath = Paths.get(FILE_PATH);
            createDirectoriesIfNeeded(filePath);
            List<String> lines = new ArrayList<>();
            for (Task task : taskList) {
                lines.add(taskToFileString(task));
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            Path filePath = Paths.get(FILE_PATH);
            createDirectoriesIfNeeded(filePath);
            createFileIfNeeded(filePath);
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task task = fileStringToTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
        return loadedTasks;
    }

    private static void createDirectoriesIfNeeded(Path filePath) {
        Path directoryPath = filePath.getParent();
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                System.err.println("Error creating directory: " + e.getMessage());
            }
        }
    }

    private static void createFileIfNeeded(Path filePath) {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }
    private static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();

        if (task instanceof ToDo) {
            sb.append("T | ");
        } else if (task instanceof Deadline) {
            sb.append("D | ");
        } else if (task instanceof Event) {
            sb.append("E | ");
        } else {
            return "";
        }

        sb.append(task.isDone() ? "1" : "0").append(" | ").append(task.getDescription());

        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            sb.append(" | ").append(deadlineTask.getBy());
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            sb.append(" | ").append(eventTask.getFrom()).append(" - ").append(eventTask.getTo());
        }

        return sb.toString();
    }
    private static Task fileStringToTask(String fileString) {
        String[] fields = fileString.split(" \\| ");
        if (fields.length < 3) {
            System.err.println("Invalid task format: " + fileString);
            return null;
        }

        char taskType = fields[0].charAt(0);
        boolean isDone = fields[1].equals("1");
        String description = fields[2];

        switch (taskType) {
            case 'T':
                ToDo todo = new ToDo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case 'D':
                if (fields.length < 4) {
                    System.err.println("Invalid deadline format: " + fileString);
                    return null;
                }
                String by = fields[3];
                Deadline deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case 'E':
                if (fields.length < 4) {
                    System.err.println("Invalid event format: " + fileString);
                    return null;
                }
                String[] fromTo = fields[3].split(" - ");
                if (fromTo.length != 2) {
                    System.err.println("Invalid event format: " + fileString);
                    return null;
                }
                String from = fromTo[0];
                String to = fromTo[1];
                Event event = new Event(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                System.err.println("Unknown task type: " + taskType);
                return null;
        }
    }
}
