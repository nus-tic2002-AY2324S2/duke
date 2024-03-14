public class ErrorHandling {

    public static boolean checkDuplicate(String desc, TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task != null && task.description.equals(desc)) {
//                System.out.println("This task's description already exists in the list");
                return true;
            }
        }
        return false;
    }

    public static void outOfRange() {
        System.out.println("Index of out range");
    }

    public static void wrongSyntax() {
        System.out.println("Input has wrong syntax, please try again.");
    }
}