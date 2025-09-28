package org.mmdworks.java8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class GroupByCollectingAndThenPractice {

    // ✅ Employee model
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Employee {
        private String name;
        private String dept;
        private int salary;
    }

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
    private static final List<Employee> employees = Arrays.asList(
            Employee.builder().name("Alice").dept("IT").salary(6000).build(),
            Employee.builder().name("Bob").dept("HR").salary(5000).build(),
            Employee.builder().name("Charlie").dept("IT").salary(7000).build(),
            Employee.builder().name("David").dept("Finance").salary(8000).build(),
            Employee.builder().name("Eve").dept("Finance").salary(7500).build()
    );

    private static final List<Student> students = Arrays.asList(
            Student.builder().name("Alice").grade("A").marks(92).build(),
            Student.builder().name("Bob").grade("B").marks(76).build(),
            Student.builder().name("Charlie").grade("A").marks(88).build(),
            Student.builder().name("David").grade("C").marks(60).build(),
            Student.builder().name("Eve").grade("B").marks(81).build()
    );


    // 👉 Q1. Find max salary employee per department
    public static void maxSalaryByDept() {

        System.out.println("Max Sal Emp By Dept");
        Map<String, Employee> maxSalEmpInDept = employees.stream().collect(Collectors.groupingBy(e -> e.dept,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
                        Optional::get
                )));
        maxSalEmpInDept.forEach((k, v) -> System.out.println(k + "->" + v));

    }

    // 👉 Q2. Find min salary employee per department
    public static void minSalaryByDept() {
        System.out.println("Min Sal Emp By Dept");
        Map<String, Employee> minSalEmpInDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept,
                Collectors.collectingAndThen(
                        Collectors.minBy(Comparator.comparingInt(Employee::getSalary)),
                        Optional::get))
        );
        minSalEmpInDept.forEach((k, v) -> System.out.println(k + "->" + v));

    }

    // 👉 Q3. Count employees per department (return Integer, not Long)
    public static void countEmployeesByDept() {
        System.out.println("Count Employee by department");
        Map<String, Integer> depCountMap = employees.stream().collect(Collectors.groupingBy(Employee::getDept,
                Collectors.collectingAndThen(
                        Collectors.counting(),
                        Long::intValue
                )));
        System.out.println("Dept and Count");
        depCountMap.forEach((k, v) -> System.out.println(k + "->" + v));


    }

    // 👉 Q4. Average marks per grade (rounded to int)
    public static void avgMarksByGrade() {

        Map<String, Integer> avginSub = students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.collectingAndThen(
                        Collectors.averagingInt(Student::getMarks), d -> (int) Math.round(d)
                ))

        );
        System.out.println("Avg in Grades");
        avginSub.forEach((k, v) -> System.out.println(k + "->" + v));
    }

    // 👉 Q5. Highest scoring student per grade
    public static void topStudentByGrade() {
        // TODO: Use groupingBy + collectingAndThen + maxBy
        //Map<String, Student>
        Map<String, Student> highestMarksInGrade = students.stream().collect(Collectors.groupingBy(Student::getGrade,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Student::getMarks)), Optional::get
                )));
        System.out.println("Highest Marks In each grade");
        highestMarksInGrade.forEach((k, v) -> System.out.println(k + "->" + v));


    }

    public static void main(String[] args) {
        maxSalaryByDept();
        minSalaryByDept();
        countEmployeesByDept();
        avgMarksByGrade();
        topStudentByGrade();
    }


}
