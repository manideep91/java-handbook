package org.mmdworks.multithreading.basics;

import java.util.concurrent.*;

public class MT_Basics_01_ThreadLifeCycleDemo {

    // 1. Runnable task
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " (Runnable) - Started");
            try {
                Thread.sleep(500); // moves to TIMED_WAITING
                System.out.println(Thread.currentThread().getName() + " (Runnable) - Yielding...");
                Thread.yield(); // Hint: might give up CPU
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " (Runnable) - Finished");
        }
    }

    // 2. Callable task
    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " (Callable) - Started");
            Thread.sleep(700); // TIMED_WAITING
            return Thread.currentThread().getName() + " (Callable) - Result after work";
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Thread Lifecycle Demo ===");

        // Thread from Runnable
        Thread t1 = new Thread(new MyRunnable(), "Thread-R1");
        System.out.println("t1 state after NEW: " + t1.getState()); // NEW

        t1.start();
        System.out.println("t1 state after start(): " + t1.getState()); // RUNNABLE

        // Using Callable with ExecutorService
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> future = es.submit(new MyCallable());

        // join() example
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " - Doing quick task");
            try {
                Thread.sleep(300);
                System.out.println(Thread.currentThread().getName() + " - Done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-JoinExample");

        t2.start();
        t2.join(); // main waits until t2 finishes
        System.out.println("Main thread - t2 finished, continuing...");

        // Get Callable result
        String result = future.get();
        System.out.println("Callable returned: " + result);

        // Wait for t1 to finish
        t1.join();
        System.out.println("t1 state after termination: " + t1.getState()); // TERMINATED

        es.shutdown();
        System.out.println("=== Demo Complete ===");
    }
}
