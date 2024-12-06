import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    int age;
    String department;
    double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

class EmployeeFilter {

    public static List<String> filterEmployees(List<Employee> employees) {
        return employees.stream()
                .filter(employee -> employee.getSalary() > 50000)
                .filter(employee -> "IT".equals(employee.getDepartment()))
                .sorted(Comparator.comparingInt(Employee::getAge))
                .limit(3) // Берем только топ-3 самых молодых
                .map(employee -> employee.getName() + " (Возраст: " + employee.getAge() + ")")
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Пример данных
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 30, "IT", 60000),
                new Employee("Bob", 25, "IT", 70000),
                new Employee("Charlie", 35, "HR", 55000),
                new Employee("David", 28, "IT", 80000),
                new Employee("Eve", 22, "IT", 45000),
                new Employee("Frank", 30, "IT", 52000)
        );

        List<String> filteredEmployeeInfo = filterEmployees(employees);

        System.out.println(filteredEmployeeInfo);
    }
}