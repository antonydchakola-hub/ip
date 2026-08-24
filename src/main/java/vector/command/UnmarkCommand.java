package vector.command;

import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

public class UnmarkCommand extends Command {
    private int index;
    
    public UnmarkCommand(int index) {
        this.index = index;
    }
    
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
