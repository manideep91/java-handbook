package org.mmdworks.functional;

import java.util.List;
import java.util.function.Consumer;

/**
 * The Consumer<T> interface represents an operation that accepts a single input argument of type T and returns no result.
 * Its single abstract method is void accept(T t).
 */
public class Functional05_ConsumerInterfaceDemo {

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Laptop", 1200.00, 15),
                new Product("Mouse", 25.50, 5),
                new Product("Monitor", 300.00, 2)
        );

        //1. Consumer for a simple action: Displaying product information.
        Consumer<Product> displayProductInfo = product ->
                System.out.printf("Product Name: %s, Price: $%.2f, Stock: %d\n",
                        product.getName(), product.getPrice(), product.getStock());

        System.out.println("\n[ACTION]: Displaying all product details:");
        products.forEach(displayProductInfo); // Using forEach with the Consumer

        // 2. Consumer for a conditional action: Checking and logging low stock.
        Consumer<Product> checkLowStock = product -> {
            if (product.getStock() < 10) {
                System.out.printf(">> ALERT: Low stock for %s! Only %d units left.\n",
                        product.getName(), product.getStock());
            }
        };
        System.out.println("\n[ACTION]: Running low stock check:");
        products.forEach(checkLowStock);

        //3. Chaining Consumers for a composite action.
        Consumer<Product> detailedStockCheck = displayProductInfo.andThen(checkLowStock);
        System.out.println("\n[ACTION]: Running a composite action (display & check):");
        products.forEach(detailedStockCheck);



    }
}
