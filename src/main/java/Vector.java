import java.util.Scanner;

public class Vector {
    public static void main(String[] args) {
        String banner = " __     _______ ____ _____ ___  ____\n"
                + " \\ \\   / / ____/ ___|_   _/  _ \\|  _ \\\n"
                + "  \\ \\ / /|  _| | |     | || | | | |_) |\n"
                + "   \\ V / | |___| |___  | || |_| |  _ <\n"
                + "    \\_/  |______\\____| |_| \\___/|_| \\_\\\n";
        String line = "    ____________________________________________________________";
        
        System.out.println(line);
        System.out.print(banner);
        System.out.println("     Hello! I'm Vector");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        // Array to store up to 100 tasks entered by the user.
        String[] tasks = new String[100];
        // Parallel array to keep track of task completion status
        boolean[] isDone = new boolean[100];
        int taskCount = 0;
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                // Display all accumulated tasks with their completion status
                System.out.println(line);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    String statusIcon = isDone[i] ? "[X]" : "[ ]";
                    System.out.println("     " + (i + 1) + "." + statusIcon + " " + tasks[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                // Mark a specific task as done
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                isDone[taskIndex] = true;
                System.out.println(line);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [X] " + tasks[taskIndex]);
                System.out.println(line);
            } else if (input.startsWith("unmark ")) {
                // Unmark a specific task
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                isDone[taskIndex] = false;
                System.out.println(line);
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       [ ] " + tasks[taskIndex]);
                System.out.println(line);
            } else {
                // Add the new task to the arrays
                tasks[taskCount] = input;
                isDone[taskCount] = false;
                taskCount++;
                
                System.out.println(line);
                System.out.println("     added: " + input);
                System.out.println(line);
            }
        }
        scanner.close();
    }
}
