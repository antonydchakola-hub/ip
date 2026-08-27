package vector.command;

import vector.VectorException;
import vector.task.Task;
import vector.task.TaskList;
import vector.ui.Ui;
import vector.storage.Storage;
import vector.command.Command;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {
    private Task task;
    
    /**
     * Constructs an AddCommand to add the specified task.
     * 
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }
    
    /**
     * Executes the command, adding the task to the list, saving to storage, and displaying a success message.
     * 
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving updates.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}
