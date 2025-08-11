package org.mmdworks.multithreading.basics;

import java.util.concurrent.atomic.AtomicInteger;

public class MT_03_RaceCondition_AtomicInteger {

    // 1. Change the variable type to AtomicInteger
    private AtomicInteger counter = new AtomicInteger(0);

    // 2. Use an atomic method to increment the counter
    public void increment() {
        counter.incrementAndGet(); // Atomic operation
    }

    public int getCounter() {
        return counter.get(); // Get the current value
    }

    public static void main(String[] args) throws InterruptedException {
        MT_03_RaceCondition_AtomicInteger demo = new MT_03_RaceCondition_AtomicInteger();

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