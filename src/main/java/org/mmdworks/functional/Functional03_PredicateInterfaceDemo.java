package org.mmdworks.functional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The Predicate<T> interface represents a function that takes one argument of type T and returns a boolean value.
 * Its single abstract method is boolean test(T t).
 */
public class Functional03_PredicateInterfaceDemo {

    public static void main(String[] args) {


        List<Product> products = List.of(
                new Product("Laptop", 1200.00, 10),
                new Product("Mouse", 25.50, 0),
                new Product("Monitor", 300.00, 5)
        );

        // Predicate to check if a product is in stock
        Predicate<Product> isInStock = product -> product.getStock() > 0;

        // Predicate to check if a product is on sale (price less than $50)
        Predicate<Product> isOnSale = product -> product.getPrice() < 50.00;

        // Predicate to find high-value items (price > $1000)
        Predicate<Product> isHighValue = product -> product.getPrice() > 1000.00;

        // Find products that are in stock and on sale - Chaining
        List<Product> onSaleAndInStock = products.stream()
                .filter(isInStock.and(isOnSale))
                .collect(Collectors.toList());

        System.out.println("Products on sale and in stock: " + onSaleAndInStock);

        // Find products that are not high-value
        List<Product> notHighValue = products.stream()
                .filter(isHighValue.negate())
                .collect(Collectors.toList());

        System.out.println("Products that are not high-value: " + notHighValue);
    }
}
