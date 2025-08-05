package org.mmdworks.functional;

import java.util.Random;
import java.util.function.Supplier;

/**
 * The Supplier<T> interface represents a function that takes no arguments and returns a result of type T.
 *
 * Its single abstract method is T get().
 */
public class Functional04_SupplierInterfaceDemo {

    public static void main(String[] args) {

        // Rule 1: A Supplier for a product with standard, fixed pricing.
        Supplier<Product> standardPriceSupplier = () -> {
            System.out.println("\n[RULE]: Generating a product with standard pricing...");
            return new Product("Standard Widget", 19.99, 100);
        };

        // Rule 2: A Supplier for a product with dynamic, flash-sale pricing.
        Supplier<Product> flashSaleSupplier = () -> {
            System.out.println("\n[RULE]: Generating a product with flash-sale pricing...");
            // The price is not hardcoded; it's calculated dynamically.
            double basePrice = 19.99;
            double discount = new Random().nextDouble() * 0.5; // Up to 50% discount
            double finalPrice = basePrice * (1 - discount);

            return new Product("Flash Sale Widget", finalPrice, 100);
        };


        Product flashSaleProduct = flashSaleSupplier.get();
        System.out.printf("Created Flash Sale Product: %s (with %.2f%% discount)\n",
                flashSaleProduct, (1 - flashSaleProduct.getPrice() / 19.99) * 100);

        Product standardProduct = standardPriceSupplier.get();
        System.out.println("Created Standard Product: " + standardProduct);


    }
}
