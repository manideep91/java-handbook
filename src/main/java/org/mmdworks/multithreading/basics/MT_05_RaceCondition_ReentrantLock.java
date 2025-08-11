package org.mmdworks.multithreading.basics;

import java.util.concurrent.locks.ReentrantLock;

public class MT_05_RaceCondition_ReentrantLock {

    // A shared counter.
    private int counter = 0;

    // The ReentrantLock object used to protect the counter.
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        // 1. Acquire the lock before accessing the shared resource.
        lock.lock();
        try {
            // 2. The critical section: This code is now thread-safe.
            counter++;
        } finally {
            // 3. Always release the lock in a finally block to ensure it happens.
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        MT_05_RaceCondition_ReentrantLock demo = new MT_05_RaceCondition_ReentrantLock();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                demo.increment();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task, "T-" + i);
            threads[i].start();
        }
        for (Thread t : threads) t.join();

        System.out.println("Final counter = " + demo.getCounter());
        System.out.println("Expected (10 threads * 10_000) = " + (10 * 10_000));
    }
}