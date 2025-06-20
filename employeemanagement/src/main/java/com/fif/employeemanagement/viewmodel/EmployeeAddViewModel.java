package com.fif.employeemanagement.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zkplus.spring.SpringUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fif.employeemanagement.model.Employee;
import com.fif.employeemanagement.model.Role;
import com.fif.employeemanagement.services.EmployeeService;
import com.fif.employeemanagement.services.RoleService;
import com.fif.employeemanagement.services.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.util.media.Media;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EmployeeAddViewModel {
    private String npk;
    private String name;
    private String email;
    private String phone;
    private String division;
    private String status;
    private String imageUrl;
    private String password;
    private EmployeeService employeeService = (EmployeeService) SpringUtil.getBean("employeeServiceImpl");
    private Set<Role> selectedRoles = new HashSet<>();
    private List<Role> allRoles;
    private RoleService roleService = (RoleService) SpringUtil.getBean("roleServiceImpl");
    private Role selectedRole;

    @Init
    @NotifyChange({"allRoles"})
    public void init() {
        allRoles = roleService.getAll();
    }
    public Role getSelectedRole() { return selectedRole; }
    public void setSelectedRole(Role selectedRole) { this.selectedRole = selectedRole; }

    @Command
    @NotifyChange({"npk", "name", "email", "phone", "division", "status", "imageUrl", "selectedRoles", "password"})
    public void save() {
        if (selectedRole == null) {
            Role userRole = roleService.getByName("USER");
            selectedRole = userRole;
        }
        selectedRoles = new HashSet<>();
        if (selectedRole != null) {
            selectedRoles.add(selectedRole);
        }
        String pwd = (password == null || password.isEmpty()) ? "password" : password;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPwd = encoder.encode(pwd);
        Employee emp = new Employee(null, npk, name, email, phone, division, status, imageUrl, hashedPwd, selectedRoles);
        employeeService.add(emp);
        npk = name = email = phone = division = status = imageUrl = password = null;
        selectedRoles = new HashSet<>();
        org.zkoss.zk.ui.Executions.sendRedirect("employeeList.zul");
    }

    @Command
    @NotifyChange({"npk", "name", "email", "phone", "division", "status", "imageUrl"})
    public void cancel() {
        npk = name = email = phone = division = status = imageUrl = null;
        org.zkoss.zk.ui.Executions.sendRedirect("employeeList.zul");
    }

    @Command
    @NotifyChange("imageUrl")
    public void uploadImage(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
        Media media = event.getMedia();
        if (media != null && media.isBinary()) {
            try {
                String ext = media.getFormat();
                String fileName = "emp_" + System.currentTimeMillis() + "." + ext;
                String realPath = org.zkoss.zk.ui.Executions.getCurrent().getDesktop().getWebApp().getRealPath("/images/") + File.separator + fileName;
                File file = new File(realPath);
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(media.getByteData());
                }
                this.imageUrl = "/images/" + fileName;
                Clients.showNotification("Image uploaded successfully!", "info", null, "top_center", 2000);
            } catch (IOException e) {
                Clients.showNotification("Failed to upload image!", "error", null, "top_center", 2000);
            }
        } else {
            Clients.showNotification("Please upload a valid image file!", "warning", null, "top_center", 2000);
        }
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
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<Role> getSelectedRoles() { return selectedRoles; }
    public void setSelectedRoles(Set<Role> selectedRoles) { this.selectedRoles = selectedRoles; }
    public List<Role> getAllRoles() { return allRoles; }
    public void setAllRoles(List<Role> allRoles) { this.allRoles = allRoles; }
}
