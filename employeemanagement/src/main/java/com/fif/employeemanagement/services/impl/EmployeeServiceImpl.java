package com.fif.employeemanagement.services.impl;

import com.fif.employeemanagement.model.Employee;
import com.fif.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public Optional<Employee> getByNpk(String npk) {
        return Optional.ofNullable(employeeDao.getByNpk(npk));
    }

    @Override
    public void add(Employee emp) {
        employeeDao.add(emp);
    }

    @Override
    public void update(Employee emp) {
        employeeDao.update(emp);
    }

    @Override
    public void delete(String npk) {
        employeeDao.delete(npk);
    }

    @Override
    public List<Employee> search(String keyword) {
        return employeeDao.search(keyword);
    }
}
