package pro.sky.spring_mockito.services.Impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.spring_mockito.data.Employee;
import pro.sky.spring_mockito.exceptions.EmployeeAlreadyAddedException;
import pro.sky.spring_mockito.exceptions.EmployeeNotFoundException;
import pro.sky.spring_mockito.exceptions.EmployeeStorageIsFullException;
import pro.sky.spring_mockito.exceptions.InvalidCheckEmployeeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceImplTest {

    private final int MAX_DATA_SIZE = 11;
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void clearEmployeeService() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    public void shouldThrowsExceptionWhenInvalidCheckNameEmployee() {
        // given
        Employee actual = new Employee("Egor123", "Egorov233", 1, 110000);

        // when
        // then
        assertThrows(InvalidCheckEmployeeException.class,
                () -> employeeService.addEmployee(actual.firstName(), actual.lastName(), actual.department(), actual.salary()));
    }

    @Test
    public void shouldCorrectlyAddEmployee() {
        // given
        Employee actual = new Employee("Ivan", "Ivanov", 1, 110000);

        // when
        Employee expected = employeeService.addEmployee(
                actual.firstName(),
                actual.lastName(),
                actual.department(),
                actual.salary());

        // then
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldThrowsExceptionWhenEmployeeIsAlreadyAdded() {
        // given
        employeeService.addEmployee("Ivan", "Ivanov", 1, 110000);
        // when
        // then
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee("Ivan", "Ivanov", 1, 110000));
    }

    @Test
    public void shouldThrowsExceptionWhenDataSizeIsFull() {
        // given
        for (int i = 0; i < MAX_DATA_SIZE; i++) {
            employeeService.addEmployee("Egor" + i, "Egorov", 1, 110000);
        }
        // when
        // then
        assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.addEmployee("Boris", "Borisov", 1, 110000));
    }

    @Test
    public void shouldCorrectlyRemoveEmployee() {
        // given
        Employee actual = employeeService.addEmployee("Ivan", "Ivanov", 1, 110000);

        // when
        Employee expected = employeeService.removeEmployee(actual.firstName(), actual.lastName());

        // then
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldThrowsExceptionWhenEmployeeIsRemoved() {
        // given
        Employee actual = employeeService.addEmployee("Ivan", "Ivanov", 1, 110000);

        // when
        employeeService.removeEmployee(actual.firstName(), actual.lastName());

        // then
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.removeEmployee(actual.firstName(), actual.lastName()));
    }

    @Test
    public void shouldCorrectlyFindEmployee() {
        // given
        Employee actual = employeeService.addEmployee("Ivan", "Ivanov", 1, 110000);

        // when
        Employee expected = employeeService.findEmployee(actual.firstName(), actual.lastName());

        // then
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldThrowsExceptionWhenEmployeeIsNotFound() {
        // given
        Employee actual = employeeService.addEmployee("Ivan", "Ivanov", 1, 110000);

        // when
        employeeService.removeEmployee(actual.firstName(), actual.lastName());

        // then
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.findEmployee(actual.firstName(), actual.lastName()));
    }

}
