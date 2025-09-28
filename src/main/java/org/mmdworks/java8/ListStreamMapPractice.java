package org.mmdworks.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListStreamMapPractice {

    public static void main(String[] args) {


        //Q1. Frequency of Integers
        //👉 Expected: 5=3, 8=3, 2=2, 7=1, 9=1
        List<Integer> nums = Arrays.asList(5, 2, 5, 7, 8, 2, 5, 8, 8, 9);
        Map<Integer, Long> numFrequencyMap = nums.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Numbers Frequency Map ::" + numFrequencyMap);

        //Q2. Top 3 Frequent Numbers
        //👉 Expected: [5, 8, 2]
        System.out.println("Top 3 Frequent elements ");
        nums.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingLong(Map.Entry<Integer, Long>::getValue).reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        //Q3. First Character Grouping
        List<String> words = Arrays.asList("apple", "apricot", "banana", "blueberry", "cherry");
        //👉 Expected: {a=[apple, apricot], b=[banana, blueberry], c=[cherry]}
        System.out.println("Grouping by 1st Char ");
        words.stream().collect(Collectors.groupingBy(e -> e.charAt(0)))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<Character, List<String>>::getKey))
                .forEach(e -> System.out.println(e.getKey() + "->" + e.getValue()));


        //Q4. Longest Word per First Letter
        //👉 Expected: {a=apricot, b=blueberry, c=cherry}
        System.out.println("Longest Word By First Letter ");
        words.stream().collect(Collectors.groupingBy(e -> e.charAt(0),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(String::length)), Optional::get
                        )))
                .entrySet()
                .stream()
                .forEach(e -> System.out.println(e.getKey() + "->" + e.getValue()));
        ;

        System.out.println("Longest Word By First Letter Way 2 ");
        Map<Character, List<String>> charFrequencyMap = words.stream().collect(Collectors.groupingBy(e -> e.charAt(0)));
        charFrequencyMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream()
                        .max(Comparator.comparing(String::length)).get()
                )).entrySet().stream().forEach(e -> System.out.println(e.getKey() + "-> " + e.getValue()));

        //Q5. Word Frequency
        System.out.println("Word Frequency ");
        List<String> languages = Arrays.asList("java", "python", "java", "c", "python", "java", "c++");
        //👉 Expected: {c=1, c++=1, java=3, python=2}
        languages.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .forEach(e -> System.out.println(e.getKey() + "->" + e.getValue()));

        //Q6. Shortest and Longest Word
        //👉 Expected: Shortest=c, Longest=python
        String longestWord = languages.stream()
                .max(Comparator.comparingInt(String::length))
                .get();

        System.out.println("Longest Word :: " + longestWord);

        String shortes = languages.stream().max(Comparator.comparingInt(String::length).reversed()).get();
        System.out.println("Shortest Word :: " + shortes);


        //Q7. Even Numbers Squared
        List<Integer> evenAndOddNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 2, 4, 8, 10);
        //👉 Expected: [100, 64, 36, 16, 4]

        System.out.println(evenAndOddNumbers
                .stream()
                .distinct()
                .filter(e -> e % 2 == 0)
                .map(s -> s * s)
                .sorted(Comparator.comparing(e -> (Integer) e).reversed())
                .toList());

        //Q8. Index Map
        //Expected: {0=java, 1=python, 2=c, 3=go}
        List<String> indexedList = Arrays.asList("java","python","c","go");

        Map<Integer, String> indexMap =
                java.util.stream.IntStream.range(0, indexedList.size())
                        .boxed()
                        .collect(Collectors.toMap(i -> i, indexedList::get));

        System.out.println(indexMap);

        //Q9. Character Frequency
        //👉 Expected: {h=1, e=1, l=3, o=2, w=1, r=1, d=1}
        String input = "hello world";
        input.chars().mapToObj(e -> (char) e)
                .filter(ch -> ch != ' ')
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);

        //Q10. Top N Words by Length
        //👉 Expected: [kotlin, python, scala]
        List<String> topNWords = Arrays.asList("java","python","c","go","kotlin","scala");
        System.out.println(topNWords.stream().sorted(Comparator.comparing(String::length).reversed())
                .limit(3)
                .toList());





    }
}
