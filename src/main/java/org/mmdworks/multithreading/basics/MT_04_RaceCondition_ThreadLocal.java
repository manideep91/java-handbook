package org.mmdworks.multithreading.basics;

public class MT_04_RaceCondition_ThreadLocal {

    // 1. Change the variable to a ThreadLocal. Each thread gets its own copy.
    private static final ThreadLocal<Integer> counter =
        ThreadLocal.withInitial(() -> 0);

    // 2. The increment method now works on the current thread's private copy.
    public void increment() {
        int currentCount = counter.get();
        counter.set(currentCount + 1);
    }

    // This method now gets the current thread's value, not a shared value.
    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        MT_04_RaceCondition_ThreadLocal demo = new MT_04_RaceCondition_ThreadLocal();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                demo.increment();
            }
            // Each thread prints its own final count, which will be 10,000.
            System.out.println("Thread [" + Thread.currentThread().getName() + "] final count = " + demo.getCounter());
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task, "T-" + i);
            threads[i].start();
        }
        for (Thread t : threads) t.join();

        // This line is now problematic. The main thread's counter is 0 because it never incremented it.
        // It's not a shared counter, so the final value of the main thread's counter is not meaningful.
        System.out.println("Final counter (from main thread) = " + demo.getCounter());
        System.out.println("Expected final count (from shared counter) = " + (10 * 10_000));
    }
}