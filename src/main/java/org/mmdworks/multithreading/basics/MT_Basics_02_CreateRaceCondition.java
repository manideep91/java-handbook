package org.mmdworks.multithreading.basics;

public class MT_Basics_02_CreateRaceCondition {
    private  int counter = 0;

    public  void increment() {
            counter++; // not atomic -> race
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        MT_Basics_02_CreateRaceCondition demo = new MT_Basics_02_CreateRaceCondition();

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
