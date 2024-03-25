import java.util.ArrayList;
import java.util.List;

<<<<<<< Updated upstream
public class TaskList {
    final List<Task> tasks;

=======
/**
 * Represents a list of tasks in the Duke application.
 */
public class TaskList {
    final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
>>>>>>> Stashed changes
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

<<<<<<< Updated upstream
    public void add(Task task) {
        tasks.add(task);

    }

    public Task remove(int index) {
        tasks.remove(index);

        return null;
    }
=======
    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
>>>>>>> Stashed changes
    public int size() {
        return tasks.size();
    }

<<<<<<< Updated upstream
=======
    /**
     * Returns the task at the specified index from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
>>>>>>> Stashed changes
    public Task get(int index) {
        return tasks.get(index);
    }

<<<<<<< Updated upstream
    public static String toFileStrings(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D|" + deadline.description + "|" + deadline.isDone + "|" + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E|" + event.description + "|" + event.isDone + "|" + event.startTime + "|" + event.endTime;
        } else if (task instanceof Todo) {
            Todo todo = (Todo) task;
=======
    /**
     * Converts a Task object to a string representation suitable for writing to a file.
     *
     * @param task The task to be converted.
     * @return A string representation of the task for writing to a file.
     */
    public static String toFileStrings(Task task) {
        if (task instanceof Deadline deadline) {
            return "D|" + deadline.description + "|" + deadline.isDone + "|" + deadline.by;
        } else if (task instanceof Event event) {
            return "E|" + event.description + "|" + event.isDone + "|" + event.startTime + "|" + event.endTime;
        } else if (task instanceof Todo todo) {
>>>>>>> Stashed changes
            return "T|" + todo.description + "|" + todo.isDone;
        } else {
            return "";
        }
    }
}