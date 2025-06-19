package com.fif.employeemanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String major;
    private Integer year;

    public Student() {}

    public Student(String name, String email, String phone, String address, String major, Integer year) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.major = major;
        this.year = year;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
}
