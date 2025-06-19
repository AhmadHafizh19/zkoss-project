package com.fif.employeemanagement.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zkplus.spring.SpringUtil;

import com.fif.employeemanagement.model.Employee;
import com.fif.employeemanagement.services.EmployeeService;
import com.fif.employeemanagement.services.impl.EmployeeServiceImpl;

public class EmployeeAddViewModel {
    private String npk;
    private String name;
    private String email;
    private String phone;
    private String division;
    private String status;
    private String imageUrl;
    private EmployeeService employeeService = (EmployeeService) SpringUtil.getBean("employeeServiceImpl");

    @Init
    public void init() {
        // Inisialisasi jika diperlukan
    }

    @Command
    @NotifyChange({"npk", "name", "email", "phone", "division", "status", "imageUrl"})
    public void save() {
        Employee emp = new Employee(npk, name, email, phone, division, status, imageUrl);
        employeeService.add(emp);
        npk = name = email = phone = division = status = imageUrl = null;
        org.zkoss.zk.ui.Executions.sendRedirect("employeeList.zul");
    }

    @Command
    @NotifyChange({"npk", "name", "email", "phone", "division", "status", "imageUrl"})
    public void cancel() {
        npk = name = email = phone = division = status = imageUrl = null;
        org.zkoss.zk.ui.Executions.sendRedirect("employeeList.zul");
    }

    // Getter & Setter
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
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
