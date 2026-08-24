public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.remove(index);
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage("  " + removedTask.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("That task number does not exist in your list.");
        }
    }
}
