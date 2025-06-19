package com.fif.simpletable.controller;

import com.fif.simpletable.model.Employee;
import com.fif.simpletable.service.EmployeeService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController extends SelectorComposer<Component> {

    @Wire
    private Textbox npkBox, nameBox, emailBox, phoneBox, searchBox;
    @Wire
    private Combobox statusBox, divisionBox;

    @Wire
    private Listbox employeeListbox;

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        if (employeeListbox != null) refreshList(employeeService.getAll());
    }

    private void refreshList(List<Employee> list) {
        employeeListbox.getItems().clear();
        for (Employee emp : list) {
            Listitem item = new Listitem();
            item.appendChild(new Listcell(emp.getNpk()));
            item.appendChild(new Listcell(emp.getName()));
            item.appendChild(new Listcell(emp.getEmail()));
            item.appendChild(new Listcell(emp.getPhone()));
            item.appendChild(new Listcell(emp.getDivision()));
            item.appendChild(new Listcell(emp.getStatus()));
            Listcell actionCell = new Listcell();
            Button detailBtn = new Button();
            detailBtn.setIconSclass("z-icon-search");
            detailBtn.setTooltiptext("Detail");
            detailBtn.setSclass("btn btn-info btn-icon");
            detailBtn.addEventListener("onClick", event -> {
                org.zkoss.zk.ui.Session sess = org.zkoss.zk.ui.Sessions.getCurrent();
                sess.setAttribute("selectedEmployee", emp);
                Executions.sendRedirect("employeeDetail.zul");
            });
            actionCell.appendChild(detailBtn);
            Button delBtn = new Button();
            delBtn.setIconSclass("z-icon-trash");
            delBtn.setTooltiptext("Delete");
            delBtn.setSclass("btn btn-danger btn-icon");
            delBtn.addEventListener("onClick", event -> {
                employeeService.delete(emp);
                refreshList(employeeService.getAll());
            });
            actionCell.appendChild(delBtn);
            item.appendChild(actionCell);
            employeeListbox.appendChild(item);
        }
    }

    @Listen("onClick = #addBtn")
    public void goToAddPage() {
        Executions.sendRedirect("employeeAdd.zul");
    }

    @Listen("onClick = #cancelAddBtn")
    public void cancelAdd() {
        Executions.sendRedirect("employeeList.zul");
    }

    @Listen("onClick = #saveAddBtn")
    public void saveAddEmployee() {
        String npk = npkBox.getValue();
        String name = nameBox.getValue();
        String email = emailBox.getValue();
        String phone = phoneBox.getValue();
        String division = divisionBox.getValue();
        String status = statusBox.getValue();
        if (npk.isEmpty() || name.isEmpty()) {
            Messagebox.show("NPK dan Nama wajib diisi!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        if (employeeService.existsNpk(npk)) {
            Messagebox.show("NPK sudah terdaftar!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        employeeService.add(new Employee(npk, name, email, phone, division, status));
        Messagebox.show("Karyawan berhasil ditambahkan!", "Info", Messagebox.OK, Messagebox.INFORMATION, event -> Executions.sendRedirect("employeeList.zul"));
    }

    @Listen("onClick = #searchBtn")
    public void searchEmployees() {
        String keyword = searchBox.getValue().toLowerCase();
        if (keyword.isEmpty()) {
            refreshList(employeeService.getAll());
            return;
        }
        refreshList(employeeService.search(keyword));
    }

    @Listen("onClick = #backBtn")
    public void backToList() {
        Executions.sendRedirect("employeeList.zul");
    }
}
