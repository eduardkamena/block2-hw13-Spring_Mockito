package pro.sky.spring_mockito.services.Impl;

import org.springframework.stereotype.Service;
import pro.sky.spring_mockito.exceptions.EmployeeAlreadyAddedException;
import pro.sky.spring_mockito.exceptions.EmployeeNotFoundException;
import pro.sky.spring_mockito.exceptions.EmployeeStorageIsFullException;
import pro.sky.spring_mockito.exceptions.InvalidCheckEmployeeException;
import pro.sky.spring_mockito.records.Employee;
import pro.sky.spring_mockito.services.EmployeeService;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_LIST = 11;

    final static Map<String, Employee> employees = new HashMap(Map.of(
            "Egorova",
            new Employee("Arina", "Egorova", 1, 110000),
            "Vasiliev",
            new Employee("Andrey", "Vasiliev", 2, 134000),
            "Egorov",
            new Employee("Alexandr", "Egorov", 3, 123000),
            "Erohin",
            new Employee("Sergey", "Erohin", 5, 160000),
            "Rud",
            new Employee("Irina", "Rud", 4, 113000),
            "Morozova",
            new Employee("Marina", "Morozova", 5, 99000),
            "Bogolubov",
            new Employee("Valeriy", "Bogolubov", 2, 87000),
            "Lavrentiev",
            new Employee("Mihail", "Lavrentiev", 3, 143000),
            "Pakulichev",
            new Employee("Dmitry", "Pakulichev", 1, 136000),
            "Simonyan",
            new Employee("Karina", "Simonyan", 4, 101000)
    ));

    // Метод проверки регистра сотрудника
    @Override
    public void checkEmployee(String firstName, String lastName) {
        if (!isAlpha(firstName) || !isAlpha(lastName)) {
            throw new InvalidCheckEmployeeException("Имя должно содержать только буквы");
        }
    }

    // Метод записи нового сотрудника
    @Override
    public void addEmployee(String firstName, String lastName, int department, double salary) {
        checkEmployee(firstName, lastName);
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        if (employees.size() >= MAX_LIST) {
            throw new EmployeeStorageIsFullException("Нет места для записи сотрудника");
        }
        if (employees.containsValue(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employees.put(employee.lastName(), employee);
    }

    // Метод удаления сотрудника
    @Override
    public void removeEmployee(String firstName, String lastName, int department, double salary) {
        checkEmployee(firstName, lastName);
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        employees.remove(employee.lastName(), employee);
    }

    // Метод для поиска сотрудника
    @Override
    public Employee findEmployee(String firstName, String lastName, int department, double salary) {
        checkEmployee(firstName, lastName);
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary);
        if (!employees.containsValue(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    // Метод вывода всех сотрудников в формате JSON
    @Override
    public Map<String, Employee> printEmployee() {
        return employees;
    }

    // Метод вывода размера коллекции
    @Override
    public int printSize() {
        return employees.size();
    }

}
