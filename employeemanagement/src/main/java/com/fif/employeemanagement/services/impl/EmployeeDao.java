package com.fif.employeemanagement.services.impl;

import com.fif.employeemanagement.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Employee getByNpk(String npk) {
        List<Employee> result = em.createQuery(
                        "SELECT e FROM Employee e WHERE e.npk = :npk", Employee.class)
                .setParameter("npk", npk)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Transactional
    public void add(Employee emp) {
        em.persist(emp);
    }

    @Transactional
    public void update(Employee emp) {
        em.merge(emp);
    }

    @Transactional
    public void delete(String npk) {
        Employee emp = getByNpk(npk);
        if (emp != null) {
            em.remove(emp);
        }
    }

    @Transactional(readOnly = true)
    public List<Employee> search(String keyword) {
        TypedQuery<Employee> query = em.createQuery(
                "SELECT e FROM Employee e WHERE LOWER(e.name) LIKE :kw OR LOWER(e.email) LIKE :kw", Employee.class);
        query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
        return query.getResultList();
    }

    // ✅ Tambahkan method ini agar bisa login pakai email
    @Transactional(readOnly = true)
    public Employee getByEmail(String email) {
        try {
            TypedQuery<Employee> query = em.createQuery(
                    "SELECT e FROM Employee e WHERE e.email = :email", Employee.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
