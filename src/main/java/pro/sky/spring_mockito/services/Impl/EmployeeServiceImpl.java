package pro.sky.spring_mockito.services.Impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.spring_mockito.exceptions.EmployeeAlreadyAddedException;
import pro.sky.spring_mockito.exceptions.EmployeeNotFoundException;
import pro.sky.spring_mockito.exceptions.EmployeeStorageIsFullException;
import pro.sky.spring_mockito.exceptions.InvalidCheckEmployeeException;
import pro.sky.spring_mockito.data.Employee;
import pro.sky.spring_mockito.services.EmployeeService;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_DATA_SIZE = 11;

    private final Map<String, Employee> employees = new HashMap<>();

    // Изначально список наполнялся Хэшмапом такой реализации, но при написании теста
    // shouldThrowsExceptionWhenDataSizeIsFull он постоянно падал из-за ошибки, что
    // сотрудник уже добавлен (так и не смог понять почему при добавлении мне сразу выдавало ошибку,
    // что такой сотрудник уже якобы есть).

//    private final Map<String, Employee> employees = new HashMap<>(Map.of(
//            "ArinaEgorova",
//            new Employee("Arina", "Egorova", 1, 110000),
//            "AndreyVasiliev",
//            new Employee("Andrey", "Vasiliev", 2, 134000),
//            "AlexandrEgorov",
//            new Employee("Alexandr", "Egorov", 3, 123000),
//            "SergeyErohin",
//            new Employee("Sergey", "Erohin", 5, 160000),
//            "IrinaRud",
//            new Employee("Irina", "Rud", 4, 113000),
//            "MarinaMorozova",
//            new Employee("Marina", "Morozova", 5, 99000),
//            "ValeriyBogolubov",
//            new Employee("Valeriy", "Bogolubov", 2, 87000),
//            "MihailLavrentiev",
//            new Employee("Mihail", "Lavrentiev", 3, 143000),
//            "DmitryPakulichev",
//            new Employee("Dmitry", "Pakulichev", 1, 136000),
//            "KarinaSimonyan",
//            new Employee("Karina", "Simonyan", 4, 101000)
//    ));

    @PostConstruct
    public void initData() {
        addEmployee("Arina", "Egorova", 1, 110000);
        addEmployee("Andrey", "Vasiliev", 2, 134000);
        addEmployee("Alexandr", "Egorov", 3, 123000);
        addEmployee("Sergey", "Erohin", 5, 160000);
        addEmployee("Irina", "Rud", 4, 113000);
        addEmployee("Marina", "Morozova", 5, 99000);
        addEmployee("Valeriy", "Bogolubov", 2, 87000);
        addEmployee("Mihail", "Lavrentiev", 3, 143000);
        addEmployee("Dmitry", "Pakulichev", 1, 136000);
        addEmployee("Karina", "Simonyan", 4, 101000);
    }

    // Метод проверки регистра сотрудника
    @Override
    public void checkEmployee(String firstName, String lastName) {
        if (!isAlpha(firstName) && !isAlpha(lastName)) {
            throw new InvalidCheckEmployeeException("Имя должно содержать только буквы");
        }
    }

    // Метод записи нового сотрудника
    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        checkEmployee(firstName, lastName);
        String employeeKey = getEmployeeKey(firstName, lastName);

        if (employees.containsKey(employeeKey)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        } else if (employees.size() >= MAX_DATA_SIZE) {
            throw new EmployeeStorageIsFullException("Нет места для записи сотрудника");
        } else {
            employees.put(employeeKey, new Employee(firstName, lastName, department, salary));
        }

        return employees.get(employeeKey);
    }

    // Метод удаления сотрудника
    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        checkEmployee(firstName, lastName);
        String employeeKey = getEmployeeKey(firstName, lastName);

        if (!employees.containsKey(employeeKey)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        return employees.remove(employeeKey);
    }

    // Метод для поиска сотрудника
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        checkEmployee(firstName, lastName);
        String employeeKey = getEmployeeKey(firstName, lastName);

        if (!employees.containsKey(employeeKey)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        return employees.get(employeeKey);
    }

    // Метод вывода всех сотрудников в формате JSON
    @Override
    public Map<String, Employee> getAllEmployee() {
        return employees;
    }

    // Метод вывода размера коллекции
    @Override
    public int getSize() {
        return employees.size();
    }

    // Метод для поиска и работе по ключу сотрудника в мапе
    // Сначала делал ключ только по ФИО, но при написании тестов споткнулся на том,
    // что Фамилия может совпадать с другим сотрудником и это конечно неправильно.
    // Вообще, конечно нужно ключом делать какой-нибудь id (мое мнение)
    @Override
    public String getEmployeeKey(String firstName, String lastName) {
        return capitalize(firstName.toLowerCase()) + capitalize(lastName.toLowerCase());
    }

}
