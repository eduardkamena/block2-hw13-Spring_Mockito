package pro.sky.spring_mockito.data;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.*;

public record Employee(String firstName, String lastName, int department, double salary) {

    public Employee(String firstName, String lastName, int department, double salary) {
        this.firstName = capitalize(firstName.toLowerCase()); // Приведение записи сотрудника к регистру
        this.lastName = capitalize(lastName.toLowerCase());
        this.department = department;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(salary, employee.salary) == 0 && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public String toString() {
        return "Сотрудник: " +
                "{Имя - " + firstName +
                ", Фамилия - " + lastName +
                ", Отдел - " + department +
                ", Зарплата - " + salary +
                "}" + "\n";
    }

}
