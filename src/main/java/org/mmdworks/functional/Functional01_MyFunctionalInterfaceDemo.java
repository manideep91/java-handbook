package org.mmdworks.functional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Functional programming is a style where we pass behavior (functions) as arguments,
 * rather than just data. Java supports this from Java 8 using:
 *
 * | Reason                      | Explanation                                                            |
 * | --------------------------- | ---------------------------------------------------------------------- |
 * | 🔁 Pass logic as argument   | You can treat behavior as a variable (like passing add, sub, mul).     |
 * | ✂️ Less boilerplate         | No need to write full class or anonymous classes.                      |
 * | 🔍 Easy to read and test    | Each function (like addition or subtraction) is isolated and testable. |
 * | 💡 Supports modern patterns | Enables use of streams, map/filter/reduce, and immutability.           |
 *
 */

public class Functional01_MyFunctionalInterfaceDemo {


    @FunctionalInterface
    interface Operation {
        int calculate(int a, int b);
    }

    public static int executeOperation(int a, int b, Operation operation) {
        return operation.calculate(a, b);
    }

    
    public static void main(String[] args) {
        Operation add = (a, b) -> a + b;
        Operation sub = (a, b) -> a - b;
        Operation mul = (a, b) -> a * b;
        Operation div = (a, b) -> b != 0 ? a / b : 0;


        Map<String, Integer> resultMap = new LinkedHashMap<>();
        resultMap.put("Addition", executeOperation(2,3,add));
        resultMap.put("Subtraction", executeOperation(2,3,sub));
        resultMap.put("Multiplication", executeOperation(2,3,mul));
        resultMap.put("Division", executeOperation(2,3,div));


        resultMap.forEach((op, result) -> System.out.println(op + " : " + result));


    }




}
