package vector.task;

import vector.VectorException;
import vector.task.*;
import vector.ui.*;
import vector.storage.*;
import vector.parser.*;
import vector.command.*;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    
    /**
     * Constructs a Todo task.
     * 
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat();
    }
}
