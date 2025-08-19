package org.mmdworks.multithreading.processBulk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class MT02_CF_Process10000Records {

    public static void main(String[] args) {
        //simulate the list
        List<String> records = IntStream.rangeClosed(1, 10000).mapToObj(e -> "Record -" + e).toList();


        //check the list in size of 1000
        int chunkSize = 1000;
        List<List<String>> chunkedList = new ArrayList<>();
        for (int i = 0; i < records.size(); i += chunkSize) {
            int start = i;
            int end = Math.min(i + chunkSize, records.size());
            chunkedList.add(records.subList(start, end));
        }

        //Collect all completableFutures
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (List<String> chunk : chunkedList) {
            futureList.add(runInAsync(chunk));
        }

        // Wait for all threads to complete
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        allDone.join();  // This will block until all are completed

        System.out.println("All threads completed.");


    }

    private static CompletableFuture<Void> runInAsync(List<String> list) {

        return CompletableFuture.runAsync(() -> {
            for (String s : list) {
                try {
                    Thread.sleep(1);
                    System.out.println("Thread  " + Thread.currentThread().getName() + " Processing " + s);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}
