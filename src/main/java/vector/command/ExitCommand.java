package vector.command;

import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command, displaying a goodbye message.
     * 
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving updates.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }
    
    /**
     * Determines whether this command should exit the application.
     * 
     * @return true, as this is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
