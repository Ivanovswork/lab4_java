import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    int age;
    Map<String, Integer> grades;

    public Student(String name, int age, Map<String, Integer> grades) {
        this.name = name;
        this.age = age;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}

class StudentFilter {

    public static List<String> filterStudents(List<Student> students) {
        return students.stream()
                .filter(student -> student.getAge() > 20)
                .filter(student -> student.getGrades().values().stream().anyMatch(grade -> grade > 80))
                .sorted(Comparator.comparing(Student::getName))
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", 22, Map.of("Math", 85, "Science", 78)),
                new Student("Bob", 19, Map.of("Math", 60, "Science", 78)),
                new Student("Charlie", 21, Map.of("Math", 70, "Science", 75)),
                new Student("David", 23, Map.of("Math", 75, "Science", 82)),
                new Student("Eve", 24, Map.of("Math", 88, "Science", 90))
        );

        List<String> filteredStudentNames = filterStudents(students);

        System.out.println(filteredStudentNames);
    }
}