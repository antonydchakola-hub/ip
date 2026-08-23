import java.util.Scanner;
import java.util.ArrayList;

public class Vector {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--clear-data")) {
            java.io.File file = new java.io.File("./data/vector.txt");
            if (file.exists()) {
                file.delete();
            }
        }
        
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
        ArrayList<Task> tasks = loadTasks();
        
        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            try {
                String[] inputParts = input.split(" ", 2);
                Command command = Command.fromString(inputParts[0]);

                switch (command) {
                    case BYE:
                        if (!input.equals("bye")) {
                            throw new VectorException("I don't recognize that command. Valid commands are: todo, deadline, event, list, mark, unmark, delete, bye.");
                        }
                        System.out.println(line);
                        System.out.println("     Bye. Hope to see you again soon!");
                        System.out.println(line);
                        isRunning = false;
                        break;
                    case LIST:
                        if (!input.equals("list")) {
                            throw new VectorException("I don't recognize that command. Valid commands are: todo, deadline, event, list, mark, unmark, delete, bye.");
                        }
                        System.out.println(line);
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("     " + (i + 1) + "." + tasks.get(i).toString());
                        }
                        System.out.println(line);
                        break;
                    case DELETE:
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new VectorException("Please specify which task you want to delete. For example: delete 1");
                        }
                        int deleteIndex = Integer.parseInt(inputParts[1].trim()) - 1;
                        Task removedTask = tasks.remove(deleteIndex);
                        System.out.println(line);
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + removedTask.toString());
                        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                        saveTasks(tasks);
                        break;
                    case MARK:
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new VectorException("Please specify which task you want to mark. For example: mark 1");
                        }
                        int markIndex = Integer.parseInt(inputParts[1].trim()) - 1;
                        tasks.get(markIndex).markAsDone();
                        System.out.println(line);
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + tasks.get(markIndex).toString());
                        System.out.println(line);
                        saveTasks(tasks);
                        break;
                    case UNMARK:
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new VectorException("Please specify which task you want to unmark. For example: unmark 1");
                        }
                        int unmarkIndex = Integer.parseInt(inputParts[1].trim()) - 1;
                        tasks.get(unmarkIndex).unmarkAsDone();
                        System.out.println(line);
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("       " + tasks.get(unmarkIndex).toString());
                        System.out.println(line);
                        saveTasks(tasks);
                        break;
                    case TODO:
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new VectorException("A todo task must have a description. Please try again.");
                        }
                        tasks.add(new Todo(inputParts[1].trim()));
                        System.out.println(line);
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks.get(tasks.size() - 1).toString());
                        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                        saveTasks(tasks);
                        break;
                    case DEADLINE:
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new VectorException("A deadline task requires a description and a /by date. Please try again.");
                        }
                        String[] deadlineParts = inputParts[1].split(" /by ");
                        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                            throw new VectorException("The deadline format is incorrect. Use: deadline <task> /by <date/time>");
                        }
                        tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                        System.out.println(line);
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks.get(tasks.size() - 1).toString());
                        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                        saveTasks(tasks);
                        break;
                    case EVENT:
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new VectorException("An event task requires a description, a /from time, and a /to time.");
                        }
                        String[] eventParts = inputParts[1].split(" /from ");
                        if (eventParts.length < 2) {
                            throw new VectorException("The event format is incomplete. Ensure you have a /from time and a /to time.");
                        }
                        String desc = eventParts[0].trim();
                        String[] timeParts = eventParts[1].split(" /to ");
                        if (timeParts.length < 2 || desc.isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                             throw new VectorException("The event format is incorrect. Use: event <task> /from <start> /to <end>");
                        }
                        tasks.add(new Event(desc, timeParts[0].trim(), timeParts[1].trim()));
                        System.out.println(line);
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks.get(tasks.size() - 1).toString());
                        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                        saveTasks(tasks);
                        break;
                    case UNKNOWN:
                    default:
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

    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            java.io.File dir = new java.io.File("./data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            java.io.FileWriter fw = new java.io.FileWriter("./data/vector.txt");
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println("Something went wrong saving tasks: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        java.io.File file = new java.io.File("./data/vector.txt");
        if (!file.exists()) {
            return tasks;
        }
        try {
            java.util.Scanner sc = new java.util.Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue;
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
            }
            sc.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Data file not found.");
        }
        return tasks;
    }
}
