# Vector Text UI Test Plan

- Setup command: `javac -d _temp/ui-test-classes src/main/java/Task.java src/main/java/Todo.java src/main/java/Deadline.java src/main/java/Event.java src/main/java/Vector.java`
- Program command: `java -cp _temp/ui-test-classes Vector`
- Timeout seconds: `10`

The runner starts a fresh Vector process for each case. Output is compared
exactly after normalising line endings and ignoring one final line ending.

## Test case 1: Start and exit
- Aim: Verify that Vector displays its greeting and exits cleanly when the user enters `bye`.

### Inputs
```text
bye
```

### Expected output
```text
    ____________________________________________________________
 __     _______ ____ _____ ___  ____
 \ \   / / ____/ ___|_   _/  _ \|  _ \
  \ \ / /|  _| | |     | || | | | |_) |
   \ V / | |___| |___  | || |_| |  _ <
    \_/  |______\____| |_| \___/|_| \_\
     Hello! I'm Vector
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 2: Add a task and list it
- Aim: Verify that a task command is added and `list` displays the task as incomplete.

### Inputs
```text
read book
list
bye
```

### Expected output
```text
    ____________________________________________________________
 __     _______ ____ _____ ___  ____
 \ \   / / ____/ ___|_   _/  _ \|  _ \
  \ \ / /|  _| | |     | || | | | |_) |
   \ V / | |___| |___  | || |_| |  _ <
    \_/  |______\____| |_| \___/|_| \_\
     Hello! I'm Vector
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     added: read book
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[ ] read book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 3: Mark and unmark a task
- Aim: Verify that `mark` changes the task status to done and `unmark` changes it back to not done.

### Inputs
```text
submit report
mark 1
unmark 1
list
bye
```

### Expected output
```text
    ____________________________________________________________
 __     _______ ____ _____ ___  ____
 \ \   / / ____/ ___|_   _/  _ \|  _ \
  \ \ / /|  _| | |     | || | | | |_) |
   \ V / | |___| |___  | || |_| |  _ <
    \_/  |______\____| |_| \___/|_| \_\
     Hello! I'm Vector
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     added: submit report
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
       [X] submit report
    ____________________________________________________________
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [ ] submit report
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[ ] submit report
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 4: Add and list a todo
- Aim: Verify that the `todo` command creates a todo task and displays its type and incomplete status.

### Inputs
```text
todo read book
list
bye
```

### Expected output
```text
    ____________________________________________________________
 __     _______ ____ _____ ___  ____
 \ \   / / ____/ ___|_   _/  _ \|  _ \
  \ \ / /|  _| | |     | || | | | |_) |
   \ V / | |___| |___  | || |_| |  _ <
    \_/  |______\____| |_| \___/|_| \_\
     Hello! I'm Vector
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 5: Add and list a deadline
- Aim: Verify that the `deadline` command records and displays the deadline time.

### Inputs
```text
deadline submit report /by Friday
list
bye
```

### Expected output
```text
    ____________________________________________________________
 __     _______ ____ _____ ___  ____
 \ \   / / ____/ ___|_   _/  _ \|  _ \
  \ \ / /|  _| | |     | || | | | |_) |
   \ V / | |___| |___  | || |_| |  _ <
    \_/  |______\____| |_| \___/|_| \_\
     Hello! I'm Vector
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] submit report (by: Friday)
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[D][ ] submit report (by: Friday)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 6: Add and list an event
- Aim: Verify that the `event` command records and displays the event time range.

### Inputs
```text
event team meeting /from Monday /to Tuesday
list
bye
```

### Expected output
```text
    ____________________________________________________________
 __     _______ ____ _____ ___  ____
 \ \   / / ____/ ___|_   _/  _ \|  _ \
  \ \ / /|  _| | |     | || | | | |_) |
   \ V / | |___| |___  | || |_| |  _ <
    \_/  |______\____| |_| \___/|_| \_\
     Hello! I'm Vector
     What can I do for you?
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] team meeting (from: Monday to: Tuesday)
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[E][ ] team meeting (from: Monday to: Tuesday)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```
