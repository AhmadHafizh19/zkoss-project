package com.fif.simpletable.model;

public class Employee {
    private String npk;
    private String name;
    private String email;
    private String phone;
    private String division;
    private String status;
    private String photoPath;

    public Employee(String npk, String name, String email, String phone, String division, String status, String photoPath) {
        this.npk = npk;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.division = division;
        this.status = status;
        this.photoPath = photoPath;
    }

    public Employee(String npk, String name, String email, String phone, String division, String status) {
        this(npk, name, email, phone, division, status, null);
    }

    public String getNpk() { return npk; }
    public void setNpk(String npk) { this.npk = npk; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
}
