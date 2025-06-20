package com.fif.employeemanagement.services;

import com.fif.employeemanagement.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getByName(String name);
}
