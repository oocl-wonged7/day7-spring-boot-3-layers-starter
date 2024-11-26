package com.oocl.springbootemployee.exception;

public class EmployeeIsNullException extends RuntimeException {
    private static final String MESSAGE = "Employee is null";
    public EmployeeIsNullException() {
        super(MESSAGE);
    }
}
