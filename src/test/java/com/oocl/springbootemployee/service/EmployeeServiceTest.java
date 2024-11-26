package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.exception.EmployeeAgeNotValidExecption;
import com.oocl.springbootemployee.exception.EmployeeInactiveException;
import com.oocl.springbootemployee.exception.EmployeeSalaryNotValidExecption;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.IEmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        when(mockedEmployeeRepository.getAll()).thenReturn(List.of(new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0)));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        List<Employee> allEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_created_employee_when_create_given_a_employee() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee createdEmployee = employeeService.creat(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
    }

    @Test
    void should_throw_exception_when_create_given_employee_age_less_than_18() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee employeeToBeCreated = new Employee(1, "Lucy", 15, Gender.FEMALE, 8000.0);

        //then
        assertThrows(EmployeeAgeNotValidExecption.class, () -> employeeService.creat(employeeToBeCreated));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }

    @Test
    void should_throw_exception_when_create_given_employee_age_greater_than_65() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee employeeToBeCreated = new Employee(1, "Lucy", 70, Gender.FEMALE, 8000.0);

        //then
        assertThrows(EmployeeAgeNotValidExecption.class, () -> employeeService.creat(employeeToBeCreated));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }

    @Test
    void should_throw_exception_when_create_given_employee_age_greater_than_30_but_salary_less_than_20000() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee employeeToBeCreated = new Employee(1, "Lucy", 35, Gender.FEMALE, 8000.0);

        //then
        assertThrows(EmployeeSalaryNotValidExecption.class, () -> employeeService.creat(employeeToBeCreated));
        verify(mockedEmployeeRepository, never()).addEmployee(any());
    }

    @Test
    void should_set_active_when_create_given_employee() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee createdEmployee = employeeService.creat(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
        verify(mockedEmployeeRepository).addEmployee(argThat(Employee::getActive));
    }

    @Test
    void should_throw_exception_when_update_given_employee_is_inactive() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);
        Employee inactiveEmployee = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        inactiveEmployee.setActive(false);
        when(mockedEmployeeRepository.getEmployeeById(1)).thenReturn(inactiveEmployee);

        //then
        assertThrows(EmployeeInactiveException.class, () -> employeeService.update(1, inactiveEmployee));
        verify(mockedEmployeeRepository, never()).updateEmployee(any(), any());
    }

}
