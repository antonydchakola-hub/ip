package vector.command;

import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;
    
    /**
     * Constructs an UnmarkCommand to unmark the task at the specified index.
     * 
     * @param index The zero-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }
    
    /**
     * Executes the command, marking the task as not done, saving to storage, and displaying a success message.
     * 
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving updates.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.get(index).unmarkAsDone();
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage("  " + tasks.get(index).toString());
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("That task number does not exist in your list.");
        }
    }
}
