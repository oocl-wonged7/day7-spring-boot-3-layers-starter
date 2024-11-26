package com.oocl.springbootemployee.exception;

public class EmployeeAgeNotValidExecption extends IllegalArgumentException {
    public static final String EMPLOYEE_AGE_NOT_VALID_MSG = "The employee age is not valid";
    public EmployeeAgeNotValidExecption() {
        super(EMPLOYEE_AGE_NOT_VALID_MSG);
    }
}
