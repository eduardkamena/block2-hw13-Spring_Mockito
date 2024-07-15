package pro.sky.spring_mockito.services.Impl;

import org.springframework.stereotype.Service;
import pro.sky.spring_mockito.services.DepartmentService;
import pro.sky.spring_mockito.data.Employee;
import pro.sky.spring_mockito.services.EmployeeService;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    // Внедрение сервиса EmployeeService для работы со списком сотрудников
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Метод возврата списка сотрудников по департаменту
    @Override
    public List<Employee> getDepartmentEmployees(int department) {
        return employeeService.getAllEmployee()
                .values()
                .stream()
                .filter(employee -> employee.department() == department)
                .toList();
    }

    // Метод возврата суммы зарплат по департаменту
    @Override
    public double getTotalSalaryEmployee(int department) {
        return employeeService.getAllEmployee()
                .values()
                .stream()
                .filter(employee -> employee.department() == department)
                .mapToDouble(Employee::salary)
                .sum();
    }

    // Метод возврата максимальной зарплаты по департаменту
    @Override
    public double getMaxSalaryEmployee(int department) {
        return employeeService.getAllEmployee()
                .values()
                .stream()
                .filter(employee -> employee.department() == department)
                .mapToDouble(Employee::salary)
                .max()
                .orElseThrow();
    }

    // Метод возврата минимальной зарплаты по департаменту
    @Override
    public double getMinSalaryEmployee(int department) {
        return employeeService.getAllEmployee()
                .values()
                .stream()
                .filter(employee -> employee.department() == department)
                .mapToDouble(Employee::salary)
                .min()
                .orElseThrow();
    }

    // Метод возврата сотрудников, сгруппированных по отделам
    @Override
    public Map<Integer, List<Employee>> getAllSortedDepartmentsEmployee() {
        return employeeService.getAllEmployee()
                .values()
                .stream()
                .collect(groupingBy(Employee::department));
    }

}
