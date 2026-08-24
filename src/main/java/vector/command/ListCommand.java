package vector.command;

import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.get(i).toString());
        }
    }
}
