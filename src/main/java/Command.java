/**
 * Represents the different types of commands that can be issued to Vector.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    UNKNOWN("");

    private final String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Converts a string to the corresponding Command enum.
     * Matches exact lowercase strings for case-sensitive parsing.
     * 
     * @param text The command string.
     * @return The corresponding Command enum, or UNKNOWN if no match is found.
     */
    public static Command fromString(String text) {
        for (Command c : Command.values()) {
            if (c.commandString.equalsIgnoreCase(text)) {
                return c;
            }
        }
        return UNKNOWN;
    }
}
