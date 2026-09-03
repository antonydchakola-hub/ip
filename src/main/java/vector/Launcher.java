package vector;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main method to start the Launcher and launch JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
