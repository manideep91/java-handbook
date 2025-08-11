/**
 * | Interface             | Description                      |
 * | --------------------- | -------------------------------- |
 * | `Function<T, R>`      | Takes `T`, returns `R`           |
 * | `Predicate<T>`        | Takes `T`, returns `boolean`     |
 * | `Consumer<T>`         | Takes `T`, returns nothing       |
 * | `Supplier<T>`         | Takes nothing, returns `T`       |
 * | `BiFunction<T, U, R>` | Takes two inputs, returns result |
 */

 Multithreading -
 # Java Multithreading Pro — Learning Roadmap & Exercises
 Welcome to the **Java Multithreading Pro** repository!
 This repo is designed to take you from **basic thread management** to **advanced concurrency patterns** and **real-world scenarios**.
 It’s organized into **7 learning modules**, each with **key concepts** and **hands-on coding exercises**.

 ---

 ## 📂 Module 1 — Foundations of Multithreading

 **Goals:**
 - Understand how threads work in Java.
 - Learn about race conditions, thread safety, and basic synchronization.

 **Concepts:**
 - Thread lifecycle (`new`, `runnable`, `running`, `waiting`, `terminated`)
 - `Runnable` vs `Callable`
 - `join()`, `yield()`, `sleep()`
 - Race conditions
 - `volatile` vs `synchronized`
 - `ThreadLocal` basics

 **Exercises:**
 1. Create two threads updating the same counter without synchronization → observe race conditions.
 2. Fix the above using `synchronized` and `ReentrantLock`.
 3. Build a date formatter using `ThreadLocal`.

 ---

 ## 📂 Module 2 — Java Concurrency Utilities

 **Goals:**
 - Learn `java.util.concurrent` classes for safer and more efficient multithreading.

 **Concepts:**
 - `ExecutorService`, `ScheduledExecutorService`
 - `BlockingQueue` (`ArrayBlockingQueue`, `LinkedBlockingQueue`)
 - `ConcurrentHashMap`
 - `CountDownLatch`, `CyclicBarrier`
 - `Semaphore`
 - `Phaser`
 - `Exchanger`

 **Exercises:**
 1. Implement a producer-consumer pattern with `BlockingQueue`.
 2. Create a thread-safe cache using `ConcurrentHashMap`.
 3. Start multiple threads together with `CountDownLatch`.
 4. Limit concurrent access to a resource using `Semaphore`.

 ---

 ## 📂 Module 3 — CompletableFuture Mastery

 **Goals:**
 - Become fluent with asynchronous programming in Java using `CompletableFuture`.

 **Concepts:**
 - `runAsync()` vs `supplyAsync()`
 - `thenApply`, `thenAccept`, `thenCompose`, `thenCombine`
 - Exception handling with `exceptionally`, `handle`
 - Custom executors

 **Exercises:**
 1. Fetch data from 3 APIs in parallel and combine results.
 2. Implement retry logic with `CompletableFuture`.
 3. Handle timeouts gracefully in async tasks.

 ---

 ## 📂 Module 4 — Advanced Synchronization & Locks

 **Goals:**
 - Learn advanced locking mechanisms and deadlock prevention.

 **Concepts:**
 - `ReentrantLock` vs `synchronized`
 - `ReadWriteLock` (`ReentrantReadWriteLock`)
 - `StampedLock` for optimistic reads
 - Deadlocks: detection & avoidance

 **Exercises:**
 1. Implement a read-heavy cache with `ReadWriteLock`.
 2. Create and fix a deadlock scenario.
 3. Build a resource-sharing system with `StampedLock`.

 ---

 ## 📂 Module 5 — Parallel Streams & Reactive Programming

 **Goals:**
 - Leverage modern Java features for parallel and reactive processing.

 **Concepts:**
 - `parallelStream()` pros, cons, and pitfalls
 - `ForkJoinPool` basics
 - Reactive Streams (`Flow API`, `SubmissionPublisher`)
 - Intro to Spring WebFlux

 **Exercises:**
 1. Convert sequential list processing to parallel streams and benchmark.
 2. Implement a reactive publisher/subscriber for real-time updates.

 ---

 ## 📂 Module 6 — Real-World Concurrency Patterns

 **Goals:**
 - Apply concurrency concepts to real projects.

 **Concepts:**
 - Work stealing (ForkJoinPool)
 - Batch processing
 - Event-driven async processing
 - Rate limiting & backpressure
 - Actor model basics (Akka)

 **Exercises:**
 1. Build a multi-threaded file search tool.
 2. Implement a mini batch job processor.
 3. Create a thread-safe in-memory message broker.

 ---

 ## 📂 Module 7 — Monitoring, Debugging & Tuning

 **Goals:**
 - Diagnose and optimize multithreaded applications.

 **Concepts:**
 - Thread dumps
 - Detecting blocked threads
 - Memory leaks in concurrency
 - Thread pool tuning

 **Exercises:**
 1. Write a program that logs active threads periodically.
 2. Simulate a bottleneck and fix it with optimized thread pools.

 ---

 ## 📌 Suggested Learning Order

 1. **Module 1 → Module 2 → Module 3** for strong fundamentals.
 2. **Module 4 & 5** for advanced programming.
 3. **Module 6 & 7** for real-world mastery.

 ---

 ## 💡 Pro Tips for Mastery
 - Always write **small, runnable examples**.
 - Experiment with **different thread pool sizes** and measure performance.
 - Use **thread dumps** to debug deadlocks.
 - Profile your app with tools like **VisualVM** or **JFR**.

 ---

 ### 🏁 End Goal:
 By the end of these 7 modules, you will:
 - Confidently use multithreading in production-grade apps.
 - Pass advanced interview questions on Java concurrency.
 - Build your own **multithreaded utilities and frameworks**.

 ---


 java-multithreading-pro/
   01-basics/
   02-thread-safety/
   03-concurrent-utils/
   04-completablefuture/
   05-advanced-locks/
   06-parallel-reactive/
   07-real-world-patterns/