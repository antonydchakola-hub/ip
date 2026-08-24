package vector;

import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

public class Vector {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Vector(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            if (fullCommand == null) break;
            if (fullCommand.trim().isEmpty()) continue;
            
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
