package com.fif.simpletable.controller;

import com.fif.simpletable.model.Employee;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Combobox;

public class EmployeeDetailController extends SelectorComposer<Component> {
    @Wire
    private Textbox npkDetail, nameDetail, emailDetail, phoneDetail;
    @Wire
    private Combobox divisionDetail, statusDetail;
    @Wire
    private Image photoImg;

    private Employee originalEmployee;
    private boolean editMode = false;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        Employee emp = (Employee) Sessions.getCurrent().getAttribute("selectedEmployee");
        if (emp != null) {
            // Simpan salinan data asli untuk cancel
            originalEmployee = new Employee(emp.getNpk(), emp.getName(), emp.getEmail(), emp.getPhone(), emp.getDivision(), emp.getStatus(), emp.getPhotoPath());
            setFormValue(emp);
            setFormEditable(false);
        }
    }

    private void setFormValue(Employee emp) {
        npkDetail.setValue(emp.getNpk());
        nameDetail.setValue(emp.getName());
        emailDetail.setValue(emp.getEmail());
        phoneDetail.setValue(emp.getPhone());
        divisionDetail.setValue(emp.getDivision());
        statusDetail.setValue(emp.getStatus());
        if (emp.getPhotoPath() != null && !emp.getPhotoPath().isEmpty()) {
            photoImg.setSrc(emp.getPhotoPath());
        } else {
            photoImg.setSrc("/images/default.png");
        }
    }

    private void setFormEditable(boolean editable) {
        nameDetail.setReadonly(!editable);
        nameDetail.setDisabled(!editable ? true : false);
        emailDetail.setReadonly(!editable);
        emailDetail.setDisabled(!editable ? true : false);
        phoneDetail.setReadonly(!editable);
        phoneDetail.setDisabled(!editable ? true : false);
        divisionDetail.setReadonly(!editable);
        divisionDetail.setDisabled(!editable ? true : false);
        statusDetail.setReadonly(!editable);
        statusDetail.setDisabled(!editable ? true : false);
        getSelf().getFellow("editDetailBtn").setVisible(!editable);
        getSelf().getFellow("saveDetailBtn").setVisible(editable);
        getSelf().getFellow("cancelEditBtn").setVisible(editable);
        editMode = editable;
    }

    @Listen("onClick = #editDetailBtn")
    public void editDetail() {
        setFormEditable(true);
    }

    @Listen("onClick = #saveDetailBtn")
    public void saveDetail() {
        Employee emp = (Employee) Sessions.getCurrent().getAttribute("selectedEmployee");
        if (emp != null) {
            emp.setName(nameDetail.getValue());
            emp.setEmail(emailDetail.getValue());
            emp.setPhone(phoneDetail.getValue());
            emp.setDivision(divisionDetail.getValue());
            emp.setStatus(statusDetail.getValue());
            setFormEditable(false);
            org.zkoss.zul.Messagebox.show("Employee data updated!", "Info", org.zkoss.zul.Messagebox.OK, org.zkoss.zul.Messagebox.INFORMATION);
            // update originalEmployee agar cancel setelah save tidak rollback
            originalEmployee = new Employee(emp.getNpk(), emp.getName(), emp.getEmail(), emp.getPhone(), emp.getDivision(), emp.getStatus(), emp.getPhotoPath());
        }
    }

    @Listen("onClick = #cancelEditBtn")
    public void cancelEdit() {
        setFormValue(originalEmployee);
        setFormEditable(false);
    }

    @Listen("onClick = #backBtn")
    public void backToList() {
        org.zkoss.zk.ui.Executions.sendRedirect("employeeList.zul");
    }
}
