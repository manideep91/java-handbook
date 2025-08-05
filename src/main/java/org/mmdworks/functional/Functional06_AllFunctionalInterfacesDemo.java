package org.mmdworks.functional;

import java.util.Optional;
import java.util.function.*;

/**
 * Use Case
 * You are receiving order requests as strings from a message queue or HTTP body (e.g., "Laptop,900.0,15").
 * You want to:
 * <p>
 * Convert the string to a Product object. ✅ Function
 * Check if the product is in stock. ✅ Predicate
 * Log the product details. ✅ Consumer
 * Provide a default product if input is missing. ✅ Supplier
 * Calculate final price with tax and discount. ✅ BiFunction
 */
public class Functional06_AllFunctionalInterfacesDemo {

    public static void main(String[] args) {


        // Step 1: Function - Convert input string to Product
        Function<String, Product> stringToProduct = str -> {
            String[] parts = str.split(",");
            return new Product(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
        };

        // Step 2: Function - Apply a 10% discount to the product price
        Function<Product, Product> applyDiscount = product -> {
            double discountedPrice = product.getPrice() * 0.9; // 10% discount
            product.setPrice(discountedPrice);
            return product;
        };

        // Step 3: Chain both functions using andThen()
        Function<String, Product> productPipeline = stringToProduct.andThen(applyDiscount);

        // Predicate: Check if in stock
        Predicate<Product> isInStock = product -> product.getStock() > 0;

        // Consumer: Log product
        Consumer<Product> logProduct = product -> System.out.println("Processed Product: " + product);

        // Supplier: Default fallback
        Supplier<Product> defaultProductSupplier = () -> new Product("Default Product", 0.0, 0);

        // BiFunction: Calculate final price
        BiFunction<Double, Double, Double> finalPriceCalculator = (price, taxRate) -> {
            double taxedPrice = price + (price * taxRate);
            return taxedPrice * (1 - 0.05); // Apply 5% more discount
        };


        // Simulated request
        String request = "Laptop,1000.0,1";

        // Execute pipeline
        Product product = Optional.ofNullable(request)
                .map(productPipeline)
                .orElseGet(defaultProductSupplier);

        // Use Predicate, Consumer, and BiFunction
        if (isInStock.test(product)) {
            logProduct.accept(product);
            double finalPrice = finalPriceCalculator.apply(product.getPrice(), 0.03);
            System.out.println("Final Price with Tax & Additional Discount: " + finalPrice);
        } else {
            System.out.println("Product is out of stock.");
        }


    }
}
