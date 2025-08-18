package org.mmdworks.multithreading.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class CF_01_RunVsSupply {

    /**
     * runAsync(Runnable) → runs a task asynchronously but does not return a result. (fire-and-forget)
     * supplyAsync(Supplier<T>) → runs a task asynchronously and returns a result wrapped in CompletableFuture<T>.
     * ---------- *
     * challenge -
     * Simulate a transaction of $100 USD being processed.
     * Use supplyAsync() to fetch a mock exchange rate (say USD → EUR).
     * Use runAsync() to log the transaction asynchronously (just print: “Transaction logged: …”).
     * Combine them so the final output shows
     * ---------- *
     */
    public static void main(String[] args) throws InterruptedException {
        double amountUSD = 100.0;

        // 1. Fetch exchange rate asynchronously (USD -> EUR) using supplyAsync
        CompletableFuture<Double> convertedAmountFuture = fetchConvertedAmount(amountUSD);

        // 2. Once conversion is done, log the transaction asynchronously (fire-and-forget with runAsync)
        convertedAmountFuture.thenAccept(amountEUR -> {
            logTransaction(amountUSD, amountEUR);
        });

        //3. Print the rest of code
        System.out.println("You will see logs after this");

        Thread.sleep(1000);



    }


    private static CompletableFuture<Double> fetchConvertedAmount(double amountUSD) {
        return CompletableFuture.supplyAsync(() -> amountUSD * 0.92);
    }

    private static void logTransaction(double amountUSD, Double amountEUR) {
        CompletableFuture.runAsync(() -> System.out.println("The US Amount is :" + amountUSD + "," +
                "The equivalent amount to EURO is :" + amountEUR));

    }

}
