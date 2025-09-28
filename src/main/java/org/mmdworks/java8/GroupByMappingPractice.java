package org.mmdworks.java8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class GroupByMappingPractice {

    // ✅ Student model
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Student {
        private String name;
        private String grade; // A, B, C, D
        private int marks;
    }

    // ✅ Sample Data
    private static final List<Student> students = Arrays.asList(
            Student.builder().name("Alice").grade("A").marks(92).build(),
            Student.builder().name("Bob").grade("B").marks(76).build(),
            Student.builder().name("Charlie").grade("A").marks(88).build(),
            Student.builder().name("David").grade("C").marks(60).build(),
            Student.builder().name("Eve").grade("B").marks(81).build()
    );

    // 👉 Q1. Group students by grade → List of names (not full Student)
    public static Map<String, List<String>> namesByGrade() {

        return students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.mapping(Student::getName, Collectors.toList())));
    }

    // 👉 Q2. Group students by grade → Set of names (unique)
    public static Map<String, Set<String>> uniqueNamesByGrade() {

        return students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.mapping(Student::getName, Collectors.toSet())));
    }

    // 👉 Q3. Group students by grade → Comma-separated names
    public static Map<String, String> joinedNamesByGrade() {

        return students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.mapping(Student::getName, Collectors.joining(","))));
    }

    // 👉 Q4. Group students by grade → Marks only
    public static Map<String, List<Integer>> marksByGrade() {

        return students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.mapping(Student::getMarks, Collectors.toList())));
    }

    // 👉 Q5. Group students by grade → Highest mark only (not Optional)
    public static Map<String, Integer> highestMarksByGrade() {

        return students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Student::getMarks)),
                        e -> e.get().getMarks()
                )
        ));
    }

    public static void main(String[] args) {
        System.out.println("Students: " + students);

        // Call your methods here after solving
        System.out.println(namesByGrade());
        System.out.println(uniqueNamesByGrade());
        System.out.println(joinedNamesByGrade());
        System.out.println(marksByGrade());
        System.out.println(highestMarksByGrade());
    }
}
