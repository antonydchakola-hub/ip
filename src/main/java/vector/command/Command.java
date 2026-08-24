package vector.command;

import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws VectorException;
    
    public boolean isExit() {
        return false;
    }
}
