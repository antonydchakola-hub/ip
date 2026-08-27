package vector.task;

import java.util.ArrayList;
import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

/**
 * Represents a list of tasks and provides operations to modify or retrieve them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     * 
     * @param tasks An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     * 
     * @param index The zero-based index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     * 
     * @param index The zero-based index of the task to retrieve.
     * @return The task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the current number of tasks in the list.
     * 
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     * 
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
