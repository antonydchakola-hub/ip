package vector.command;

import vector.VectorException;
import vector.task.TaskList;
import vector.ui.Ui;
import vector.storage.Storage;
import vector.command.Command;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;
    
    /**
     * Constructs a MarkCommand to mark the task at the specified index.
     * 
     * @param index The zero-based index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }
    
    /**
     * Executes the command, marking the task as done, saving to storage, and displaying a success message.
     * 
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving updates.
     */
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
