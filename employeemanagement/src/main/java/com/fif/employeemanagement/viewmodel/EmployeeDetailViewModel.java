package com.fif.employeemanagement.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zkplus.spring.SpringUtil;

import com.fif.employeemanagement.model.Employee;
import com.fif.employeemanagement.services.EmployeeService;
import com.fif.employeemanagement.services.impl.EmployeeServiceImpl;

public class EmployeeDetailViewModel {
    private String npk;
    private String name;
    private String email;
    private String phone;
    private String division;
    private String status;
    private String imageUrl;
    private boolean editing = false;
    private EmployeeService employeeService = (EmployeeService) SpringUtil.getBean("employeeServiceImpl");

    @Init
    public void init() {
        String npk = org.zkoss.zk.ui.Executions.getCurrent().getParameter("npk");
        if (npk != null) {
            Employee emp = employeeService.getByNpk(npk).orElse(null);
            if (emp != null) {
                this.npk = emp.getNpk();
                name = emp.getName();
                email = emp.getEmail();
                phone = emp.getPhone();
                division = emp.getDivision();
                status = emp.getStatus();
                imageUrl = emp.getImageUrl();
            }
        }
    }

    @Command
    @NotifyChange("editing")
    public void edit() {
        editing = true;
    }

    @Command
    @NotifyChange({"editing", "npk", "name", "email", "phone", "division", "status", "imageUrl"})
    public void save() {
        Employee emp = new Employee(npk, name, email, phone, division, status, imageUrl);
        employeeService.update(emp);
        editing = false;
    }

    @Command
    @NotifyChange("editing")
    public void cancel() {
        // Batalkan perubahan, reload data jika perlu
        editing = false;
    }

    @Command
    public void back() {
        // Navigasi kembali ke list
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
    public boolean isEditing() { return editing; }
    public void setEditing(boolean editing) { this.editing = editing; }
}
