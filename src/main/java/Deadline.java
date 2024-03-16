public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
//int byIndex = input.indexOf("/by");
//                    String desc = input.substring(9, byIndex).trim();
//                    String by = input.substring(byIndex + 3).trim();
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
