public class MarkCommand extends Command {
    private int index;
    
    public MarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.get(index).markAsDone();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("  " + tasks.get(index).toString());
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("That task number does not exist in your list.");
        }
    }
}
