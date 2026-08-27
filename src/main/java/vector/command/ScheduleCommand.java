package vector.command;

import vector.VectorException;
import vector.task.Task;
import vector.task.TaskList;
import vector.ui.Ui;
import vector.storage.Storage;
import vector.command.Command;

public class ScheduleCommand extends Command {
    private java.time.LocalDate date;
    
    public ScheduleCommand(java.time.LocalDate date) {
        this.date = date;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks occurring on " + date + ":");
        int matchCount = 1;
        for (Task t : tasks.getTasks()) {
            if (t.occursOn(date)) {
                ui.showMessage(matchCount + "." + t.toString());
                matchCount++;
            }
        }
        if (matchCount == 1) {
            ui.showMessage("(No tasks found)");
        }
    }
}
