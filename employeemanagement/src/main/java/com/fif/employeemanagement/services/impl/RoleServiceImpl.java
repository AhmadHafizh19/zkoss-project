package com.fif.employeemanagement.services.impl;

import com.fif.employeemanagement.model.Role;
import com.fif.employeemanagement.services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("roleServiceImpl")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAll() {
        return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role getByName(String name) {
        List<Role> result = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
            .setParameter("name", name)
            .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
