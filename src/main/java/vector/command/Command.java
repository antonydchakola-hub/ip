package vector.command;

import vector.VectorException;
import vector.task.TaskList;
import vector.ui.Ui;
import vector.storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws VectorException;
    
    public boolean isExit() {
        return false;
    }
}
