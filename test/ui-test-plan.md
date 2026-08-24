# Vector Text UI Test Plan

- Setup command: `javac -d _temp/ui-test-classes src/main/java/*.java`
- Program command: `java -cp _temp/ui-test-classes Vector --clear-data`
- Timeout seconds: `10`

The runner starts a fresh Vector process for each case. Output is compared
exactly after normalising line endings and ignoring one final line ending.
Positive and negative/edge cases are interleaved so that each state-changing
operation is followed by a check of the resulting task state.

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

## Test case 2: Reject an invalid task index
- Aim: Verify that an out-of-range task index is rejected without creating or corrupting task state.

### Inputs
```text
mark 0
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
     OOPS!!! That task number does not exist in your list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 3: Reject an unknown command without changing state
- Aim: Verify that an unknown command is rejected and does not add an extra task to the list.

### Inputs
```text
todo read book
what is this
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
     OOPS!!! I don't recognize that command. Valid commands are: todo, deadline, event, list, mark, unmark, delete, schedule, bye.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 4: Unmark an incomplete task
- Aim: Verify that an invalid state transition does not mark an incomplete todo as done or change the task count.

### Inputs
```text
todo read book
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
     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [T][ ] read book
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 5: Mark and unmark a task
- Aim: Verify that `mark` changes the task status to done and `unmark` changes it back to not done.

### Inputs
```text
todo submit report
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
     Got it. I've added this task:
       [T][ ] submit report
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][X] submit report
    ____________________________________________________________
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [T][ ] submit report
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] submit report
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 6: Mark an already completed task
- Aim: Verify that repeating `mark` keeps the task done and does not duplicate the task.

### Inputs
```text
todo read book
mark 1
mark 1
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
     Nice! I've marked this task as done:
       [T][X] read book
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][X] read book
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 7: Add and list a todo
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

## Test case 8: Reject a todo without a description
- Aim: Verify that a malformed todo command is rejected and leaves the existing task list unchanged.

### Inputs
```text
todo read book
todo
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
     OOPS!!! A todo task must have a description. Please try again.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 9: Add and list a deadline
- Aim: Verify that the `deadline` command records and displays the deadline time.

### Inputs
```text
deadline submit report /by 2019-10-15 1800
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
       [D][ ] submit report (by: Oct 15 2019, 6:00 pm)
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[D][ ] submit report (by: Oct 15 2019, 6:00 pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 10: Reject a malformed deadline
- Aim: Verify that a deadline without a `/by` separator is rejected and does not alter an existing deadline.

### Inputs
```text
deadline submit report /by 2019-10-15 1800
deadline invalid
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
       [D][ ] submit report (by: Oct 15 2019, 6:00 pm)
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! The deadline format is incorrect. Use: deadline <task> /by <date/time>
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[D][ ] submit report (by: Oct 15 2019, 6:00 pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 11: Add and list an event
- Aim: Verify that the `event` command records and displays the event time range.

### Inputs
```text
event team meeting /from 2019-10-14 /to 2019-10-15 1800
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
       [E][ ] team meeting (from: Oct 14 2019, 12:00 am to: Oct 15 2019, 6:00 pm)
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[E][ ] team meeting (from: Oct 14 2019, 12:00 am to: Oct 15 2019, 6:00 pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 12: Reject an incomplete event
- Aim: Verify that an event without a `/to` time is rejected and does not alter an existing event.

### Inputs
```text
event team meeting /from 2019-10-14 /to 2019-10-15 1800
event planning /from 2019-10-14
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
       [E][ ] team meeting (from: Oct 14 2019, 12:00 am to: Oct 15 2019, 6:00 pm)
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     OOPS!!! The event format is incorrect. Use: event <task> /from <start> /to <end>
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[E][ ] team meeting (from: Oct 14 2019, 12:00 am to: Oct 15 2019, 6:00 pm)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## Test case 13: Delete a task
- Aim: Verify that a task can be deleted successfully and the count updates correctly.

### Inputs
```text
todo read book
todo return book
delete 2
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
     Got it. I've added this task:
       [T][ ] return book
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Noted. I've removed this task:
       [T][ ] return book
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

## Test case 14: Schedule command
- Aim: Verify that schedule finds tasks occurring on a specific date.

### Inputs
```text
deadline assignment /by 2019-12-02 2359
event conference /from 2019-12-01 /to 2019-12-03
schedule 2019-12-02
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
       [D][ ] assignment (by: Dec 2 2019, 11:59 pm)
     Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] conference (from: Dec 1 2019, 12:00 am to: Dec 3 2019, 12:00 am)
     Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks occurring on 2019-12-02:
     1.[D][ ] assignment (by: Dec 2 2019, 11:59 pm)
     2.[E][ ] conference (from: Dec 1 2019, 12:00 am to: Dec 3 2019, 12:00 am)
    ____________________________________________________________
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```
