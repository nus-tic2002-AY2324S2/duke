<<<<<<< Updated upstream
public class ErrorHandling {

=======
/**
 * Utility class for handling errors and performing validation checks in the Duke application.
 */
public class ErrorHandling {

    /**
     * Checks if a task with the specified description already exists in the task list.
     *
     * @param desc     The description of the task to be checked for duplication.
     * @param taskList The task list to search for duplicate descriptions.
     * @return true if a task with the same description exists in the task list, false otherwise.
     */
>>>>>>> Stashed changes
    public static boolean checkDuplicate(String desc, TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task != null && task.description.equals(desc)) {
<<<<<<< Updated upstream
//                System.out.println("This task's description already exists in the list");
=======
>>>>>>> Stashed changes
                return true;
            }
        }
        return false;
    }

<<<<<<< Updated upstream
    public static void outOfRange() {
        System.out.println("Index of out range");
    }

=======
    /**
     * Displays a message indicating that the index is out of range.
     */
    public static void outOfRange() {
        System.out.println("Index out of range");
    }

    /**
     * Displays a message indicating that the input has incorrect syntax.
     */
>>>>>>> Stashed changes
    public static void wrongSyntax() {
        System.out.println("Input has wrong syntax, please try again.");
    }
}