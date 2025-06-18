package com.fif.simpletable.service;

import com.fif.simpletable.model.Employee;
import org.zkoss.zk.ui.Sessions;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private static final String SESSION_EMP_LIST = "employeeList";

    private List<Employee> getSessionList() {
        List<Employee> list = (List<Employee>) Sessions.getCurrent().getAttribute(SESSION_EMP_LIST);
        if (list == null) {
            list = new ArrayList<>();
            list.add(new Employee("71478", "NABILA KARIN", "nabilakarin02@gmail.com", "085694517410", "Data", "Active"));
            list.add(new Employee("71484", "RAVEN DANIEL MARTIN", "ravendaniel0@gmail.com", "085212411844", "IT", "Menyerah"));
            list.add(new Employee("71485", "ALICE SHIZUKA HUTAGAOL", "aliceshizuka@gmail.com", "082111160512", "Data", "Active"));
            list.add(new Employee("71489", "VINCENT BHARATA", "vincentbharataa@gmail.com", "082113372884", "IT", "Menyerah"));
            list.add(new Employee("71477", "MARCELLA AURELIA YATIJAN", "cellaureliay@gmail.com", "081219685684", "Data", "Active"));
            list.add(new Employee("71482", "ADENAWATI HASIM", "denahsm1112@gmail.com", "081296647388", "IT", "Menyerah"));
            list.add(new Employee("71483", "THEOPATRA KENNY SUSANTO", "theopatra.ks@gmail.com", "08970339996", "IT", "Menyerah"));
            list.add(new Employee("71488", "AHMAD HAFIZH ASSAAD", "assaad.hafizh1904@gmail.com", "081249556050", "IT", "Menyerah"));
            list.add(new Employee("71486", "AHMAD ARDRA DAMARJATI", "ahmad.ardra30@gmail.com", "081389955496", "Data", "Active"));
            list.add(new Employee("71479", "LEONARD SEAN LEE", "slleonard86@gmail.com", "082111007107", "IT", "Menyerah"));
            list.add(new Employee("71487", "MICHAELL ABELARD HENDRA", "michaellabelard24@gmail.com", "087786230620", "Data", "Active"));
            list.add(new Employee("71476", "ANGELLA ANANTA BATUBARA", "angellaa59@gmail.com", "08181402003", "Data", "active"));
            list.add(new Employee("71481", "WILLMAN SATRIA SITUMORANG", "willman.satria5@gmail.com", "085270819529", "IT", "Menyerah"));
            list.add(new Employee("71480", "PUTRA ADHLI FALAH", "adhli.falah@gmail.com", "0895636701004", "IT", "Menyerah"));
            Sessions.getCurrent().setAttribute(SESSION_EMP_LIST, list);
        }
        return list;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(getSessionList());
    }

    public void add(Employee emp) {
        getSessionList().add(emp);
    }

    public void delete(Employee emp) {
        getSessionList().remove(emp);
    }

    public boolean existsNpk(String npk) {
        for (Employee e : getSessionList()) {
            if (e.getNpk().equals(npk)) return true;
        }
        return false;
    }

    public List<Employee> search(String keyword) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : getSessionList()) {
            if (e.getName().toLowerCase().contains(keyword) || e.getEmail().toLowerCase().contains(keyword)) {
                result.add(e);
            }
        }
        return result;
    }
}
