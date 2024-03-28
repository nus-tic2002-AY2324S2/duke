import java.time.LocalDate;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        // Compare based on task type
        int typeComparison = task1.type.compareTo(task2.type);
        if (typeComparison != 0) {
            return typeComparison;
        }

        // If tasks have the same type, compare based on deadlines
        if (task1 instanceof Deadline && task2 instanceof Deadline) {
            LocalDate dueDate1 = ((Deadline) task1).by.toLocalDate();
            LocalDate dueDate2 = ((Deadline) task2).by.toLocalDate();
            return dueDate1.compareTo(dueDate2);
        }

        if (task1 instanceof Event && task2 instanceof Event) {
            LocalDate dueDate1 = ((Event) task1).startTime.toLocalDate();
            LocalDate dueDate2 = ((Event) task2).startTime.toLocalDate();
            return dueDate1.compareTo(dueDate2);
        }

        // If tasks have different types, no need to compare deadlines
        return 0;
    }
}