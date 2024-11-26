package com.oocl.springbootemployee.exception;


public class EmployeeSalaryNotValidExecption extends IllegalArgumentException{
    public static final String MESSAGE = "Employee Salary is not valid";
    public EmployeeSalaryNotValidExecption() {
        super(MESSAGE);
    }
}
