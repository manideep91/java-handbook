package org.mmdworks.functional;


import java.util.function.Function;

/**
 * The Function<T, R> interface represents a function that takes one argument of type T and returns a result of type R.
 * Its single abstract method is apply(T t).
 */
public class Functional02_FunctionInterfaceDemo {


    public static void main(String[] args) {
        demoSimpleFunctionInterfaceUsage();
        complexFunctionInterfaceUsage();
    }


    private static void complexFunctionInterfaceUsage() {
        String request = "Laptop,1000.0,5";

        // Step 1: Function - Convert input string to Product
        Function<String, Product> stringToProduct = str -> {
            String[] parts = str.split(",");
            return new Product(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
        };

        System.out.println("The product Details :" + stringToProduct.apply(request));

    }

    private static void demoSimpleFunctionInterfaceUsage() {
        // A Function that takes a String and returns its length (as an Integer).
        Function<String, Integer> findLengthOfString = str -> str.length();
        Integer stringLength = findLengthOfString.apply("hello how are you");
        System.out.println("String Length is :" + stringLength);

        // A Function that takes a String and returns the String converted to uppercase.
        Function<String, String> toUpperCaseConversion = String::toUpperCase;
        String upperCaseConverted = toUpperCaseConversion.apply("hello");
        System.out.println("String after converting to upper case  is :" + upperCaseConverted);
    }

}
