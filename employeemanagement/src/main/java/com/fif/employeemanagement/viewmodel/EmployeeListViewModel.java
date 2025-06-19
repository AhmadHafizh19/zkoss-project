package com.fif.employeemanagement.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.Init;
import com.fif.employeemanagement.model.Employee;
import com.fif.employeemanagement.services.EmployeeService;
import org.zkoss.zkplus.spring.SpringUtil;
import java.util.List;

public class EmployeeListViewModel {
    private String searchText;
    private List<Employee> employeeList;
    private EmployeeService employeeService = (EmployeeService) SpringUtil.getBean("employeeServiceImpl");

    @Init
    @NotifyChange("employeeList")
    public void init() {
        employeeList = employeeService.getAll();
    }

    @Command
    @NotifyChange("employeeList")
    public void search() {
        employeeList = employeeService.search(searchText);
    }

    @Command
    public void add() {
        org.zkoss.zk.ui.Executions.sendRedirect("employeeAdd.zul");
    }

    @Command
    public void detail(@org.zkoss.bind.annotation.BindingParam("emp") Employee emp) {
        java.util.Map<String, Object> params = new java.util.HashMap<>();
        params.put("emp", emp);
        org.zkoss.zk.ui.Executions.sendRedirect("employeeDetail.zul?npk=" + emp.getNpk());
    }

    @Command
    @NotifyChange("employeeList")
    public void delete(@org.zkoss.bind.annotation.BindingParam("emp") Employee emp) {
        employeeService.delete(emp.getNpk());
        employeeList = employeeService.getAll();
    }

    // Getter & Setter
    public String getSearchText() { return searchText; }
    public void setSearchText(String searchText) { this.searchText = searchText; }
    public List<Employee> getEmployeeList() { return employeeList; }
    public void setEmployeeList(List<Employee> employeeList) { this.employeeList = employeeList; }
}
