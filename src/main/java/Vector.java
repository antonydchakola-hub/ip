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
            String input = ui.readCommand();
            if (input == null) break;
            if (input.trim().isEmpty()) continue;
            
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (VectorException e) {
                ui.showLine();
                ui.showError(e.getMessage());
                ui.showLine();
            } catch (NumberFormatException e) {
                ui.showLine();
                ui.showError("The task number provided is invalid.");
                ui.showLine();
            } catch (IndexOutOfBoundsException e) {
                ui.showLine();
                ui.showError("That task number does not exist in your list.");
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
