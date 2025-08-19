package org.mmdworks.multithreading.interCommunication;

import java.util.concurrent.CompletableFuture;

public class IC_01_PingPong_Unordered {

    public static void main(String[] args) {

        CompletableFuture<Void> pingThread = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Ping");
                mySleep(500);
            }
        });

        CompletableFuture<Void> pongThread = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Pong");
                mySleep(500);
            }
        });

        // Wait for both to finish
        pingThread.join();
        pongThread.join();

        System.out.println("Ping-Pong finished!");

    }

    private static void mySleep(int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
