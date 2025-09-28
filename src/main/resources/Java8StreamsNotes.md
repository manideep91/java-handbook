| Use Case                        | Code Snippet                                                                                                                                         | Result Example                       |
| ------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------ |
| **Group into lists (default)**  | `Collectors.groupingBy(Employee::getDept)`                                                                                                           | `IT -> [Alice, Charlie]`             |
| **Count per group**             | `Collectors.groupingBy(Employee::getDept, Collectors.counting())`                                                                                    | `IT -> 2`                            |
| **Max per group**               | `Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::getSalary)), Optional::get))` | `IT -> Charlie (7000)`               |
| **Min per group**               | `Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Employee::getSalary)), Optional::get))` | `HR -> Bob (5000)`                   |
| **Average per group**           | `Collectors.groupingBy(Employee::getDept, Collectors.averagingInt(Employee::getSalary))`                                                             | `Finance -> 7750.0`                  |
| **Sum per group**               | `Collectors.groupingBy(Employee::getDept, Collectors.summingInt(Employee::getSalary))`                                                               | `Finance -> 15500`                   |
| **Names list per group**        | `Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.toList()))`                                               | `IT -> [Alice, Charlie]`             |
| **Unique names per group**      | `Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.toSet()))`                                                | `Finance -> [David, Eve]`            |
| **Join names per group**        | `Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.joining(", ")))`                                          | `IT -> Alice, Charlie`               |
| **Unmodifiable list per group** | `Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList))`                         | `IT -> [Alice, Charlie] (immutable)` |


| Feature                | **Collectors.mapping**                                                                                                                                                                                        | **Collectors.collectingAndThen**                                                                                                                                                                                                                                   |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **What it does**       | Transforms **each element** before collecting.                                                                                                                                                                | Transforms the **entire collected result** after collection.                                                                                                                                                                                                       |
| **When to use**        | When you don’t want the whole object, just a property or derived value.                                                                                                                                       | When the downstream gives you a type you want to post-process (e.g., `Optional`, `Long`, mutable list).                                                                                                                                                            |
| **Applied**            | **Per element** (during accumulation).                                                                                                                                                                        | **Per group** (after accumulation).                                                                                                                                                                                                                                |
| **Signature**          | `mapping(Function mapper, Collector downstream)`                                                                                                                                                              | `collectingAndThen(Collector downstream, Function finisher)`                                                                                                                                                                                                       |
| **Typical downstream** | `toList()`, `toSet()`, `joining()`                                                                                                                                                                            | `maxBy()`, `minBy()`, `counting()`, `toList()`                                                                                                                                                                                                                     |
| **Example 1**          | Group students by grade → names list: <br>`java<br>Map<String, List<String>> names = students.stream()<br> .collect(groupingBy(Student::getGrade,<br> mapping(Student::getName, toList())));<br>`             | Group employees by dept → max salary employee (unwrap Optional): <br>`java<br>Map<String, Employee> top = employees.stream()<br> .collect(groupingBy(Employee::getDept,<br> collectingAndThen(maxBy(comparingInt(Employee::getSalary)),<br> Optional::get)));<br>` |
| **Example 2**          | Group employees by dept → comma-separated names: <br>`java<br>Map<String, String> names = employees.stream()<br> .collect(groupingBy(Employee::getDept,<br> mapping(Employee::getName, joining(", "))));<br>` | Count employees per dept → Integer not Long: <br>`java<br>Map<String, Integer> counts = employees.stream()<br> .collect(groupingBy(Employee::getDept,<br> collectingAndThen(counting(), Long::intValue)));<br>`                                                    |
| **Example 3**          | Group students by grade → unique names: <br>`java<br>Map<String, Set<String>> names = students.stream()<br> .collect(groupingBy(Student::getGrade,<br> mapping(Student::getName, toSet())));<br>`             | Group students by grade → average marks rounded: <br>`java<br>Map<String, Integer> avg = students.stream()<br> .collect(groupingBy(Student::getGrade,<br> collectingAndThen(averagingInt(Student::getMarks),<br> d -> (int)Math.round(d))));<br>`                  |

👉 Group students by grade → list of names (not objects):
Map<String, List<String>> namesByGrade = students.stream()
.collect(Collectors.groupingBy(
Student::getGrade,
Collectors.mapping(Student::getName, Collectors.toList())
));

👉 Max salary employee per department (unwrap Optional):
Map<String, Employee> topByDept = employees.stream()
.collect(Collectors.groupingBy(
Employee::getDept,
Collectors.collectingAndThen(
Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
Optional::get
)
));
