package pro.sky.spring_mockito.services;

import pro.sky.spring_mockito.records.Employee;

import java.util.Map;

public interface EmployeeService {

    // Метод проверки регистра сотрудника
    void checkEmployee(String firstName, String lastName);

    // Метод записи нового сотрудника
    void addEmployee(String firstName, String lastName, int department, double salary);

    // Метод удаления сотрудника
    void removeEmployee(String firstName, String lastName, int department, double salary);

    // Метод для поиска сотрудника
    Employee findEmployee(String firstName, String lastName, int department, double salary);

    // Метод вывода всех сотрудников в формате JSON
    Map<String, Employee> printEmployee();

    // Метод вывода размера коллекции
    int printSize();

}
