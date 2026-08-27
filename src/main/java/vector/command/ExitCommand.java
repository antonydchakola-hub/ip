package vector.command;

import vector.VectorException;
import vector.task.TaskList;
import vector.ui.Ui;
import vector.storage.Storage;
import vector.command.Command;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
