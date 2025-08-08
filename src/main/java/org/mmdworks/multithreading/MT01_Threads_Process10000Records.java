package org.mmdworks.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MT01_Threads_Process10000Records {

    public static void main(String[] args) throws InterruptedException {

        //simulate huge list
        List<String> records = IntStream.rangeClosed(1, 10000).mapToObj(i -> "Record -" + i).toList();

        //chunk size
        int chunkSize = 1000;

        //threads
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < records.size(); i += chunkSize) {
            int start = i;
            int end = Math.min(i + chunkSize, records.size());
            List<String> chunk = records.subList(start, end);

            //process each chunk in separate thread
            Runnable runnable = () -> {
                for (String s : chunk) {
                    try {
                        Thread.sleep(1);
                        System.out.println(Thread.currentThread().getName() + "- Processing -" + s);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            };

            Thread thread = new Thread(runnable, "Thread -" + (i / chunkSize + 1));
            threads.add(thread);
            thread.start();


        }// end of for

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("All threads execution completed");


    }//end of main()
}
