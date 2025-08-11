1️⃣ Thread Lifecycle
----------------------------------------------------------------------------------------------------------
| State                            | Meaning                                                             |
| -------------------------------- | ------------------------------------------------------------------- |
| **NEW**                          | Thread object created but **not started** (`new Thread()`).         |
| **RUNNABLE**                     | Eligible to run, waiting for CPU scheduling.                        |
| **RUNNING**                      | Actually executing on the CPU (only one thread per core at a time). |
| **WAITING** / **TIMED\_WAITING** | Temporarily not running (waiting for notify/sleep/join).            |
| **TERMINATED**                   | Finished execution (cannot be restarted).                           |
----------------------------------------------------------------------------------------------------------
Thread t = new Thread(() -> System.out.println("Hello"));
System.out.println(t.getState()); // NEW
t.start();
System.out.println(t.getState()); // RUNNABLE or RUNNING

2️⃣ Runnable vs Callable
---------------------------------------------------------------------------------
| Feature      | Runnable                        | Callable                     |
| ------------ | ------------------------------- | ---------------------------- |
| Return value | No (`void run()`)               | Yes (`V call()`)             |
| Exception    | Cannot throw checked exceptions | Can throw checked exceptions |
| Usage        | Simple tasks without result     | Tasks with a result          |
---------------------------------------------------------------------------------

Examples :
// Runnable
Runnable task1 = () -> System.out.println("No return");
new Thread(task1).start();

// Callable
Callable<Integer> task2 = () -> 42;
ExecutorService ex = Executors.newSingleThreadExecutor();
Future<Integer> f = ex.submit(task2);
System.out.println(f.get()); // 42
ex.shutdown();

> join() → Wait for another thread to finish.
> yield() → Give chance to others now.
> sleep() → Pause yourself for a time.

3️⃣ join()
Purpose: Make the calling thread wait until the target thread finishes.
Thread t1 = new Thread(() -> {
    for (int i = 0; i < 3; i++) System.out.println("T1: " + i);
});
t1.start();
t1.join(); // Main waits for t1
System.out.println("Main continues");

4️⃣ yield()
Purpose: Suggest to the scheduler to give other threads a chance.
Note: It’s just a hint, may not work always.
Thread t = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println(Thread.currentThread().getName());
        Thread.yield();
    }
});
t.start();

5️⃣ sleep()
Purpose: Pause current thread for given time (in ms). Keeps the lock if it already holds one.
Example:
System.out.println("Start");
Thread.sleep(1000);
System.out.println("1 second later");

6️⃣ Race Condition
A race condition happens when multiple threads are trying to access and modify a shared resource (like a variable) at the same time.
This leads to unpredictable and incorrect results because a multi-step operation (like counter++)
can be interrupted by another thread, causing an update to be lost.

-------------------------------------------------------------------------------------------------
| Concept       | How It Works                               | Drawbacks                       |
|---------------|--------------------------------------------|---------------------------------|

| synchronized  | Locks an object, ensuring only one thread  | Can create a performance        |
|               | can execute a critical section of code at  | bottleneck as threads are        |
|               | a time.                                    | forced to wait for the lock.    |

| volatile      | Guarantees reads and writes go directly to | Does not make operations atomic.|
|               | main memory, avoiding CPU caches.          | A multi-step operation can still|
|               |                                            | be interrupted.                 |

| AtomicInteger | Uses low-level, lock-free CPU instructions | It is a specialized tool and    |
|               | (like CAS) to perform atomic get-increment-| only works for simple,          |
|               | update operations as a single, indivisible | single-variable operations.     |
|               | unit of work.                              |                                |

| ThreadLocal   | Gives each thread its own private copy of  | Only useful when each thread    |
|               | a variable, avoiding race conditions on    | needs unique data not shared    |
|               | variables that shouldn't be shared.         | across threads.                 |

| ReentrantLock | Provides explicit lock/unlock control by   | Requires manual unlocking,      |
|               | allowing a thread to acquire a lock with   | which can cause deadlocks if    |
|               | reentrant (reacquire) capability and       | not properly released; more     |
|               | options like tryLock and fairness policies.| complex than synchronized.      |
-------------------------------------------------------------------------------------------------

##  7 ReentrantLock
---------------------------------------------------------------------------------------------------------
| Feature                 | Description                                | Benefit                        |
|-----------------------  |--------------------------------------------|--------------------------------|
| **tryLock()**           | Tries to get lock immediately without wait.| Avoids blocking if busy.       |
|                         | Returns true if successful, else false.    | Enables alternate actions.     |
| **lockInterruptibly()** | Lets thread give up waiting if interrupted.| Supports cancellation.         |
| **Fairness policy**     | Fair lock grants access in request order.  | Prevents starvation.           |
| **Multiple Conditions** | Allows multiple wait/notify sets per lock. | Enables complex sync logic.    |
---------------------------------------------------------------------------------------------------------

##  8 synchronized VS ReentrantLock
| Feature              | `synchronized`         | `ReentrantLock` |
| -------------------- | ---------------------- | --------------- |
| Explicit lock/unlock | No (implicit by block) | Yes             |
| tryLock()            | No                     | Yes             |
| lockInterruptibly()  | No                     | Yes             |
| Fairness policy      | No                     | Yes             |
| Multiple Conditions  | No                     | Yes             |
