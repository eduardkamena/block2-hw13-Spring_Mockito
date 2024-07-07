package pro.sky.spring_mockito.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.spring_mockito.services.DepartmentService;
import pro.sky.spring_mockito.data.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Возврат списка сотрудников по департаменту
    @GetMapping(path = "/{id}/employees")
    public List<Employee> getDepartmentEmployees(@PathVariable int id) {
        return departmentService.getDepartmentEmployees(id);
    }

    //Возврат суммы зарплат по департаменту
    @GetMapping(path = "/{id}/salary/sum")
    public double getTotalSalaryEmployee(@PathVariable int id) {
        return departmentService.getTotalSalaryEmployee(id);
    }

    // Возврат максимальной зарплаты по департаменту
    @GetMapping(path = "/{id}/salary/max")
    public double getMaxSalaryEmployee(@PathVariable int id) {
        return departmentService.getMaxSalaryEmployee(id);
    }

    // Возврат минимальной зарплаты по департаменту
    @GetMapping(path = "/{id}/salary/min")
    public double getMinSalaryEmployee(@PathVariable int id) {
        return departmentService.getMinSalaryEmployee(id);
    }

    // Возврат сотрудников, сгруппированных по отделам
    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> getAllSortedDepartmentsEmployee() {
        return departmentService.getAllSortedDepartmentsEmployee();
    }

}
