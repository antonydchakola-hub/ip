package vector;

import vector.command.Command;
import vector.parser.Parser;
import vector.storage.Storage;
import vector.task.TaskList;
import vector.ui.Ui;

/**
 * The main entry point for the Vector application.
 * Initializes the UI, Storage, and TaskList, and starts the main application loop.
 */
public class Vector {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Vector application instance.
     *
     * @param filePath The path to the data file where tasks are saved.
     */
    public Vector(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main loop of the Vector application, continuously reading and executing user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            if (fullCommand == null) {
                break;
            }
            if (fullCommand.trim().isEmpty()) {
                continue;
            }

            try {
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (VectorException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("The task number provided is invalid.");
            } catch (IndexOutOfBoundsException e) {
                ui.showError("That task number does not exist in your list.");
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method to start the Vector application.
     *
     * @param args Command-line arguments. Accepts "--clear-data" to clear existing task data.
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--clear-data")) {
            java.io.File file = new java.io.File("./data/vector.txt");
            if (file.exists()) {
                file.delete();
            }
        }
        new Vector("./data/vector.txt").run();
    }
}
