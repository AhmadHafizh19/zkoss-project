package com.fif.employeemanagement.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zkplus.spring.SpringUtil;

import com.fif.employeemanagement.model.Employee;
import com.fif.employeemanagement.services.EmployeeService;
import com.fif.employeemanagement.services.impl.EmployeeServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.util.media.Media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EmployeeDetailViewModel {
    private Employee employee;
    private boolean editing = false;
    private EmployeeService employeeService = (EmployeeService) SpringUtil.getBean("employeeServiceImpl");

    @Init
    public void init() {
        String npk = org.zkoss.zk.ui.Executions.getCurrent().getParameter("npk");
        if (npk != null) {
            employee = employeeService.getByNpk(npk).orElse(null);

        }
        System.out.println("Upload button: " + editing);
    }


    @Command
    @NotifyChange("editing")
    public void edit() {
        editing = true;
    }

    @Command
    @NotifyChange({"editing", "employee"})
    public void save() {
        employeeService.update(employee);
        editing = false;
    }

    @Command
    @NotifyChange("editing")
    public void cancel() {
        // Reload data dari DB jika perlu
        if (employee != null && employee.getNpk() != null) {
            employee = employeeService.getByNpk(employee.getNpk()).orElse(employee);
        }
        editing = false;
    }

    @Command
    public void back() {
        org.zkoss.zk.ui.Executions.sendRedirect("employeeList.zul");
    }

    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public boolean getIsAdmin() {
        return isAdmin();
    }

    @Command
    @NotifyChange("employee")
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
                this.employee.setImageUrl("/images/" + fileName);
                Clients.showNotification("Image uploaded successfully!", "info", null, "top_center", 2000);
            } catch (IOException e) {
                Clients.showNotification("Failed to upload image!", "error", null, "top_center", 2000);
            }
        } else {
            Clients.showNotification("Please upload a valid image file!", "warning", null, "top_center", 2000);
        }
    }

    // Getter & Setter
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public boolean isEditing() { return editing; }
    public void setEditing(boolean editing) { this.editing = editing; }
}
