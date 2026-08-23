/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    
    protected String by;

    /**
     * Constructs a Deadline task.
     * 
     * @param description The description of the deadline.
     * @param by The date/time by which the task needs to be done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + by;
    }
}
