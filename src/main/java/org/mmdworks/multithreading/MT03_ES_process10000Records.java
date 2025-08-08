package org.mmdworks.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class MT03_ES_process10000Records {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //simulate records
        List<String> records = IntStream.rangeClosed(1, 10000).mapToObj(e -> "Record -" + e).toList();

        //chunksize
        int chunkSize = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futureList = new ArrayList<>();

        for (int i = 0; i < records.size(); i += chunkSize) {
            int start = i;
            int end = Math.min(i + chunkSize, records.size());
            List<String> chunk = records.subList(start, end);

            Future<?> future = executorService.submit(() -> {

                for (String s : chunk) {
                    try {
                        Thread.sleep(1);
                        System.out.println("Thread :" + Thread.currentThread().getName() + "processing -" + s);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            });

            futureList.add(future);

            // Step 3: Wait for all tasks to complete
            for (Future<?> f : futureList) {
                f.get();  // blocks until task finishes
            }

            // Step 4: Shutdown executor gracefully
            executorService.shutdown();

            System.out.println("✅ All tasks completed.");


        }

    }
}
