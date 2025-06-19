package com.fif.employeemanagement.services;

import com.fif.employeemanagement.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();
    Optional<Employee> getByNpk(String npk);
    void add(Employee emp);
    void update(Employee emp);
    void delete(String npk);
    List<Employee> search(String keyword);
}

