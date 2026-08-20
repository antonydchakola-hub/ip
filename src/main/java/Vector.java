import java.util.Scanner;
import java.util.ArrayList;

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
        // ArrayList to store tasks dynamically
        ArrayList<Task> tasks = new ArrayList<>();
        
        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println(line);
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (input.equals("list")) {
                    // Display all accumulated tasks with their completion status
                    System.out.println(line);
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("     " + (i + 1) + "." + tasks.get(i).toString());
                    }
                    System.out.println(line);
                } else if (input.startsWith("delete")) {
                    if (input.length() <= 7) {
                        throw new VectorException("Please specify which task you want to delete. For example: delete 1");
                    }
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    Task removedTask = tasks.remove(taskIndex);
                    System.out.println(line);
                    System.out.println("     Noted. I've removed this task:");
                    System.out.println("       " + removedTask.toString());
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (input.startsWith("mark")) {
                    if (input.length() <= 5) {
                        throw new VectorException("Please specify which task you want to mark. For example: mark 1");
                    }
                    int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                    tasks.get(taskIndex).markAsDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + tasks.get(taskIndex).toString());
                    System.out.println(line);
                } else if (input.startsWith("unmark")) {
                    if (input.length() <= 7) {
                        throw new VectorException("Please specify which task you want to unmark. For example: unmark 1");
                    }
                    int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                    tasks.get(taskIndex).unmarkAsDone();
                    System.out.println(line);
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + tasks.get(taskIndex).toString());
                    System.out.println(line);
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 4 || input.substring(4).trim().isEmpty()) {
                        throw new VectorException("A todo task must have a description. Please try again.");
                    }
                    String description = input.substring(5).trim();
                    tasks.add(new Todo(description));
                    System.out.println(line);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (input.startsWith("deadline")) {
                    if (input.length() <= 8 || input.substring(8).trim().isEmpty()) {
                        throw new VectorException("A deadline task requires a description and a /by date. Please try again.");
                    }
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new VectorException("The deadline format is incorrect. Use: deadline <task> /by <date/time>");
                    }
                    tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                    System.out.println(line);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (input.startsWith("event")) {
                    if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
                        throw new VectorException("An event task requires a description, a /from time, and a /to time.");
                    }
                    String[] parts = input.substring(6).split(" /from ");
                    if (parts.length < 2) {
                        throw new VectorException("The event format is incomplete. Ensure you have a /from time and a /to time.");
                    }
                    String description = parts[0].trim();
                    String[] timeParts = parts[1].split(" /to ");
                    if (timeParts.length < 2 || description.isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                         throw new VectorException("The event format is incorrect. Use: event <task> /from <start> /to <end>");
                    }
                    tasks.add(new Event(description, timeParts[0].trim(), timeParts[1].trim()));
                    System.out.println(line);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else {
                    throw new VectorException("I don't recognize that command. Valid commands are: todo, deadline, event, list, mark, unmark, delete, bye.");
                }
            } catch (VectorException e) {
                System.out.println(line);
                System.out.println("     OOPS!!! " + e.getMessage());
                System.out.println(line);
            } catch (NumberFormatException e) {
                System.out.println(line);
                System.out.println("     OOPS!!! The task number provided is invalid.");
                System.out.println(line);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(line);
                System.out.println("     OOPS!!! That task number does not exist in your list.");
                System.out.println(line);
            }
        }
        scanner.close();
    }
}
