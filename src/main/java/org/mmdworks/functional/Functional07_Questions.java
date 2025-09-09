package org.mmdworks.functional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Functional07_Questions {

    /**
     * Word Count
     * Given a sentence: "Java is great and Java is powerful",
     * write a functional program to produce a frequency map of each word.
     */

    public static void main(String[] args) {
        String word = "Java is great and Java is powerful";

        Map<String, Integer> frequencyMap = new HashMap<>();
        String[] split = word.split(" ");
        Arrays.stream(split).forEach(e -> frequencyMap.put(e, frequencyMap.getOrDefault(e, 0) + 1));
        System.out.println(frequencyMap);
    }
}
