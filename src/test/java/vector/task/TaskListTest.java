package vector.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void findTasks_matchingKeyword_returnsMatchingTasks() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("read book"));
        taskList.add(new Todo("return book"));
        taskList.add(new Todo("buy groceries"));

        ArrayList<Task> result = taskList.findTasks("book");
        assertEquals(2, result.size());
        assertEquals("read book", result.get(0).getDescription());
        assertEquals("return book", result.get(1).getDescription());
    }

    @Test
    public void findTasks_noMatchingKeyword_returnsEmptyList() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("read book"));
        taskList.add(new Todo("return book"));

        ArrayList<Task> result = taskList.findTasks("grocery");
        assertEquals(0, result.size());
    }
}
