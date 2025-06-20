package com.fif.employeemanagement.services.impl;

import com.fif.employeemanagement.services.impl.EmployeeDao;
import com.fif.employeemanagement.model.Employee;
import com.fif.employeemanagement.model.Role;
import com.fif.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

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

    // ✅ Untuk proses login oleh Spring Security
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeDao.getByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found with email: " + email);
        }

        Set<GrantedAuthority> authorities = employee.getRoles()
                .stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        System.out.println("Authorities: " + authorities);

        return new org.springframework.security.core.userdetails.User(
                employee.getEmail(),
                employee.getPassword(),
                authorities
        );
    }
}
