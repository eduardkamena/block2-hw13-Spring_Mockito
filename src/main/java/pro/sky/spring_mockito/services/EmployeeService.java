package pro.sky.spring_mockito.services;

import pro.sky.spring_mockito.data.Employee;

import java.util.Map;

public interface EmployeeService {

    // Метод проверки регистра сотрудника
    void checkEmployee(String firstName, String lastName);

    // Метод записи нового сотрудника
    Employee addEmployee(String firstName, String lastName, int department, double salary);

    // Метод удаления сотрудника
    Employee removeEmployee(String firstName, String lastName);

    // Метод для поиска сотрудника
    Employee findEmployee(String firstName, String lastName);

    // Метод вывода всех сотрудников в формате JSON
    Map<String, Employee> getAllEmployee();

    // Метод вывода размера коллекции
    int getSize();

    // Метод для поиска по ключу сотрудника в мапе
    String getEmployeeKey(String firstName, String lastName);
}
