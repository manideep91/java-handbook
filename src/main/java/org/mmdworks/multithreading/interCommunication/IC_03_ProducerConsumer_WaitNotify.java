package org.mmdworks.multithreading.interCommunication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class IC_03_ProducerConsumer_WaitNotify {


    private static Queue<Integer> buffer = new LinkedList<>();
    private static final int capacity = 1;


    public static void main(String[] args) {

        CompletableFuture<Void> producer = CompletableFuture.runAsync(() -> {
            int item = 0;
            try {
                while (true) {
                    synchronized (buffer) {
                        while (buffer.size() == capacity) {
                            buffer.wait();
                        }
                        buffer.add(item);
                        item++;
                        System.out.println("Produced Item:" + item + "Into Queue by :" + Thread.currentThread().getName());
                        buffer.notify();
                        Thread.sleep(5000);
                    }

                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        });

        CompletableFuture<Void> consumer = CompletableFuture.runAsync(() -> {

            try {
                while (true) {
                    synchronized (buffer) {
                        while (buffer.isEmpty()) {
                            buffer.wait();
                        }

                        int item = buffer.poll();
                        System.out.println("Consumed Item :" + item + "From Queue by :" + Thread.currentThread().getName());
                        buffer.notify();
                        Thread.sleep(5000);
                    }


                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.join();
        consumer.join();
    }


}
