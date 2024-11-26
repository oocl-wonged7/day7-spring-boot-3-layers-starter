package com.oocl.springbootemployee.exception;

public class EmployeeInactiveException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Employee is inactive";
    public EmployeeInactiveException() {
        super(ERROR_MESSAGE);
    }
}
