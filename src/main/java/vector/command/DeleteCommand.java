package vector.command;

import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;
    
    /**
     * Constructs a DeleteCommand to remove a task at the specified index.
     * 
     * @param index The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    /**
     * Executes the command, removing the task from the list, saving to storage, and displaying a success message.
     * 
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving updates.
     */
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
