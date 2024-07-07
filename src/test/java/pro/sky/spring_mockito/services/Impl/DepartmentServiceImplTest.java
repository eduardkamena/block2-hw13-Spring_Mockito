package pro.sky.spring_mockito.services.Impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.spring_mockito.data.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    private final List<Employee> employeeList = new ArrayList<>() {{
        add(new Employee("Ivan", "Ivanov", 1, 100_000));
        add(new Employee("Boris", "Borisov", 1, 200_000));
        add(new Employee("Egor", "Egorov", 1, 300_000));
        add(new Employee("Masha", "Mashina", 2, 400_000));
        add(new Employee("Katya", "Katina", 2, 500_000));
    }};

    private final Map<String, Employee> employeeMap = new HashMap<>();

    @BeforeEach
    public void initMap() {
        for (Employee employee : employeeList) {
            employeeMap.put(employee.firstName() + employee.lastName(), employee);
        }
    }

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void shouldCorrectlyGetDepartmentEmployees() {
        // given
        int department = 1;
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeMap);

        List<Employee> actual = List.of(employeeList.get(0), employeeList.get(1), employeeList.get(2));

        // when
        List<Employee> expected = departmentService.getDepartmentEmployees(department);

        // then
        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void shouldCorrectlyGetTotalSalaryEmployee() {
        // given
        int department = 1;
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeMap);

        double actual = employeeList.get(0).salary() + employeeList.get(1).salary() + employeeList.get(2).salary();

        // when
        double expected = departmentService.getTotalSalaryEmployee(department);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCorrectlyGetMaxSalaryEmployee() {
        // given
        int department = 1;
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeMap);

        double actual = employeeList.get(2).salary();

        // when
        double expected = departmentService.getMaxSalaryEmployee(department);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCorrectlyGetMinSalaryEmployee() {
        // given
        int department = 1;
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeMap);

        double actual = employeeList.get(0).salary();

        // when
        double expected = departmentService.getMinSalaryEmployee(department);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCorrectlyGetAllSortedDepartmentsEmployee() {
        // given
        Map<Integer, List<Employee>> actual = new HashMap<>() {{
            put(1, List.of(employeeList.get(0), employeeList.get(1), employeeList.get(2)));
            put(2, List.of(employeeList.get(3), employeeList.get(4)));

        }};

        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeMap);

        // when
        Map<Integer, List<Employee>> expected = departmentService.getAllSortedDepartmentsEmployee();

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}
