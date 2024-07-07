package pro.sky.spring_mockito.services;

import pro.sky.spring_mockito.data.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    // Метод возврата списка сотрудников по департаменту
    List<Employee> getDepartmentEmployees(int department);

    // Метод возврата суммы зарплат по департаменту
    double getTotalSalaryEmployee(int department);

    // Метод возврата максимальной зарплаты по департаменту
    double getMaxSalaryEmployee(int department);

    // Метод возврата минимальной зарплаты по департаменту
    double getMinSalaryEmployee(int department);

    // Метод возврата сотрудников, сгруппированных по отделам
    Map<Integer, List<Employee>> getAllSortedDepartmentsEmployee();

}
