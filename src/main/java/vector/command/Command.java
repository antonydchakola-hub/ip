package vector.command;

import vector.VectorException;
import vector.storage.Storage;
import vector.task.TaskList;
import vector.ui.Ui;

/**
 * Represents an executable command in the Vector application.
 * Subclasses should implement the execute method to define specific command behavior.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving updates.
     * @throws VectorException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws VectorException;

    /**
     * Determines whether this command should exit the application.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
