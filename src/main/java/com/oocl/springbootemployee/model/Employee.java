package com.oocl.springbootemployee.model;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    private Double salary;
    private Boolean active;

    public Employee(Integer id, String name, Integer age, Gender gender, Double salary) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Double getSalary() {
        return salary;
    }

    public Boolean getActive() {return active;}

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setActive(Boolean active) {this.active = active;}
}
