package com.example.firebasetutorial.model;

public class Student {
    private String name,email;
    private long regNumber;

    public Student() {
    }

    public Student(String name, String email, long regNumber) {
        this.name = name;
        this.email = email;
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(long regNumber) {
        this.regNumber = regNumber;
    }
}
