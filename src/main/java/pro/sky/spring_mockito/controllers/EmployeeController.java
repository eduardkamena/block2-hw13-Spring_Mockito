package pro.sky.spring_mockito.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.spring_mockito.exceptions.EmployeeAlreadyAddedException;
import pro.sky.spring_mockito.exceptions.EmployeeNotFoundException;
import pro.sky.spring_mockito.exceptions.EmployeeStorageIsFullException;
import pro.sky.spring_mockito.services.EmployeeService;
import pro.sky.spring_mockito.records.Employee;

import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Запись нового сотрудника
    @GetMapping(path = "/add")
    public void addEmployee(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("department") int department,
                            @RequestParam("salary") double salary) {
        employeeService.addEmployee(firstName, lastName, department, salary);
    }

    // Удаление сотрудника
    @GetMapping(path = "/remove")
    public void removeEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("department") int department,
                               @RequestParam("salary") double salary) {
        employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    // Поиск сотрудника
    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("department") int department,
                                 @RequestParam("salary") double salary) {
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    // Вывод всех сотрудников в формате JSON
    @GetMapping(path = "/print")
    public Map<String, Employee> printEmployee() {
        return employeeService.printEmployee();
    }

    // Вывод размера коллекции
    @GetMapping(path = "/size")
    public int printSize() {
        return employeeService.printSize();
    }

    // Отлов ошибки
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String handlerEx() {
        return "Нет места для записи сотрудника";
    }

    // Отлов ошибки
    @ExceptionHandler
    public String handlerEx2(EmployeeNotFoundException e) {
        return e.getMessage();
    }

    // Отлов ошибки
    @ExceptionHandler
    public String handlerEx3(EmployeeAlreadyAddedException e) {
        return e.getMessage();
    }

}
