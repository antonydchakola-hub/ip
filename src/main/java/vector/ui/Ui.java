package vector.ui;

import java.util.Scanner;
import vector.VectorException;

/**
 * Handles interactions with the user, including reading input and displaying messages.
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and banner.
     */
    public void showWelcome() {
        String banner = " __     _______ ____ _____ ___  ____\n"
                + " \\ \\   / / ____/ ___|_   _/  _ \\|  _ \\\n"
                + "  \\ \\ / /|  _| | |     | || | | | |_) |\n"
                + "   \\ V / | |___| |___  | || |_| |  _ <\n"
                + "    \\_/  |______\\____| |_| \\___/|_| \\_\\\n";
        showLine();
        System.out.print(banner);
        System.out.println("     Hello! I'm Vector");
        System.out.println("     What can I do for you?");
        showLine();
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays a standard message to the user, properly indented.
     * 
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println("     " + message);
    }

    /**
     * Displays an error message to the user, properly indented and prefixed.
     * 
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("     OOPS!!! " + message);
    }

    /**
     * Reads the next command input from the user.
     * 
     * @return The user's input string.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }
}
