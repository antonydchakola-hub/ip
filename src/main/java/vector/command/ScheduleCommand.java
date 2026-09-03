package vector.command;

import vector.storage.Storage;
import vector.task.Task;
import vector.task.TaskList;
import vector.ui.Ui;

/**
 * Represents a command to list tasks occurring on a specific date.
 */
public class ScheduleCommand extends Command {
    private java.time.LocalDate date;

    /**
     * Constructs a ScheduleCommand to find tasks for the specified date.
     *
     * @param date The date to filter tasks by.
     */
    public ScheduleCommand(java.time.LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command, displaying tasks that occur on the specified date.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving updates.
     */
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
