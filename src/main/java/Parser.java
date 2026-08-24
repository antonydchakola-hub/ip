public class Parser {
    public static Command parse(String fullCommand) throws VectorException {
        String[] parts = fullCommand.split(" ", 2);
        String action = parts[0].trim().toLowerCase();
        
        switch (action) {
            case "bye":
                if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                    throw new VectorException("The bye command does not take any arguments.");
                }
                return new ExitCommand();
            case "list":
                if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                    throw new VectorException("The list command does not take any arguments.");
                }
                return new ListCommand();
            case "delete":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new VectorException("Please specify which task you want to delete. For example: delete 1");
                }
                return new DeleteCommand(Integer.parseInt(parts[1].trim()) - 1);
            case "mark":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new VectorException("Please specify which task you want to mark. For example: mark 1");
                }
                return new MarkCommand(Integer.parseInt(parts[1].trim()) - 1);
            case "unmark":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new VectorException("Please specify which task you want to unmark. For example: unmark 1");
                }
                return new UnmarkCommand(Integer.parseInt(parts[1].trim()) - 1);
            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new VectorException("A todo task must have a description. Please try again.");
                }
                return new AddCommand(new Todo(parts[1].trim()));
            case "deadline":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new VectorException("A deadline task requires a description and a /by date. Please try again.");
                }
                String[] deadlineParts = parts[1].split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new VectorException("The deadline format is incorrect. Use: deadline <task> /by <date/time>");
                }
                return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            case "event":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new VectorException("An event task requires a description, a /from time, and a /to time.");
                }
                String[] eventParts = parts[1].split(" /from ");
                if (eventParts.length < 2) {
                    throw new VectorException("The event format is incomplete. Ensure you have a /from time and a /to time.");
                }
                String desc = eventParts[0].trim();
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2 || desc.isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                     throw new VectorException("The event format is incorrect. Use: event <task> /from <start> /to <end>");
                }
                return new AddCommand(new Event(desc, timeParts[0].trim(), timeParts[1].trim()));
            case "schedule":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new VectorException("Please specify a date to check the schedule. For example: schedule 2019-12-02");
                }
                java.time.LocalDate searchDate = DateTimeParser.parseDate(parts[1].trim());
                return new ScheduleCommand(searchDate);
            default:
                throw new VectorException("I don't recognize that command. Valid commands are: todo, deadline, event, list, mark, unmark, delete, schedule, bye.");
        }
    }
}
