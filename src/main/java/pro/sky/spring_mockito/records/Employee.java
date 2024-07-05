package pro.sky.spring_mockito.records;

import static org.apache.commons.lang3.StringUtils.*;

public record Employee(String firstName, String lastName, int department, double salary) {

    public Employee(String firstName, String lastName, int department, double salary) {
        this.firstName = capitalize(firstName.toLowerCase()); // Приведение записи сотрудника к регистру
        this.lastName = capitalize(lastName.toLowerCase());
        this.department = department;
        this.salary = salary;
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
