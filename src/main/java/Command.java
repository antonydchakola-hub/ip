public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws VectorException;
    public boolean isExit() {
        return false;
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMessage("Bye. Hope to see you again soon!");
        ui.showLine();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.get(i).toString());
        }
        ui.showLine();
    }
}

class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.remove(index);
        ui.showLine();
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + removedTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks.getTasks());
    }
}

class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(index).markAsDone();
        ui.showLine();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("  " + tasks.get(index).toString());
        ui.showLine();
        storage.save(tasks.getTasks());
    }
}

class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(index).unmarkAsDone();
        ui.showLine();
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage("  " + tasks.get(index).toString());
        ui.showLine();
        storage.save(tasks.getTasks());
    }
}

class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks.getTasks());
    }
}

class ScheduleCommand extends Command {
    private java.time.LocalDate date;
    public ScheduleCommand(java.time.LocalDate date) {
        this.date = date;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
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
        ui.showLine();
    }
}
