import java.util.ArrayList;
import java.util.List;

public class TaskList {
    final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);

    }

    public Task remove(int index) {
        tasks.remove(index);

        return null;
    }
    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public static String toFileStrings(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D|" + deadline.description + "|" + deadline.isDone + "|" + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E|" + event.description + "|" + event.isDone + "|" + event.startTime + "|" + event.endTime;
        } else if (task instanceof Todo) {
            Todo todo = (Todo) task;
            return "T|" + todo.description + "|" + todo.isDone;
        } else {
            return "";
        }
    }
}