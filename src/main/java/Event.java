/**
 * Represents an Event task.
 */
public class Event extends Task {
    
    protected String from;
    protected String to;

    /**
     * Constructs an Event task.
     * 
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + " | " + from + " | " + to;
    }
}
