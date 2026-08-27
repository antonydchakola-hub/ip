package vector.task;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     * 
     * @param tasks The initial list of tasks.
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
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task from the list at the specified index.
     * 
     * @param index The index of the task to retrieve.
     * @return The retrieved task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     * 
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the underlying ArrayList of tasks.
     * 
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks whose description contains the keyword.
     * 
     * @param keyword The keyword to search for.
     * @return An ArrayList of matching tasks.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
