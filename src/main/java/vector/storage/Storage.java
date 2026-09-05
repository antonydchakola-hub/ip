package vector.storage;

import java.util.ArrayList;

import vector.task.Deadline;
import vector.task.Event;
import vector.task.Task;
import vector.task.Todo;

/**
 * Handles loading tasks from and saving tasks to the data file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        java.io.File file = new java.io.File(this.filePath);
        if (!file.exists()) {
            return tasks;
        }
        try {
            java.util.Scanner sc = new java.util.Scanner(file);
            while (sc.hasNextLine()) {
                try {
                    String line = sc.nextLine();
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) {
                        continue;
                    }
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String desc = parts[2];

                    Task task = null;
                    if (type.equals("T")) {
                        task = new Todo(desc);
                    } else if (type.equals("D") && parts.length >= 4) {
                        task = new Deadline(desc, parts[3]);
                    } else if (type.equals("E") && parts.length >= 5) {
                        task = new Event(desc, parts[3], parts[4]);
                    }

                    if (task != null) {
                        if (isDone) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping corrupted data line: " + e.getMessage());
                }
            }
            sc.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Data file not found.");
        }
        return tasks;
    }

    /**
     * Saves the current tasks to the data file.
     *
     * @param tasks The ArrayList of tasks to save.
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "Task list to save should not be null";
        try {
            java.io.File file = new java.io.File(this.filePath);
            java.io.File dir = file.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }
            java.io.FileWriter fw = new java.io.FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println("Something went wrong saving tasks: " + e.getMessage());
        }
    }
}
