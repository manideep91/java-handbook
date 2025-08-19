package org.mmdworks.multithreading.interCommunication;

import java.util.concurrent.CompletableFuture;

public class IC_02_PingPong_WaitNotify {


    static Object lock = new Object();
    public static volatile boolean isPingTurn = true;


    public static void main(String[] args) {


        CompletableFuture<Void> pingTask = CompletableFuture.runAsync(() -> {

            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    try {
                        if (!isPingTurn) {
                            lock.wait();
                        }

                        System.out.println("PING");
                        isPingTurn = false;
                        lock.notify();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

        });

        CompletableFuture<Void> pongTask = CompletableFuture.runAsync(() -> {

            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    try {
                        if(isPingTurn) {
                            lock.wait();
                        }

                        System.out.println("PONG");
                        isPingTurn = true;
                        lock.notify();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                }
            }

        });

        pingTask.join();
        pongTask.join();


    }


}
