package com.fif.employeemanagement.model;

import com.fif.employeemanagement.model.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID unik auto-increment (primary key teknikal)

    @Column(unique = true, nullable = false)
    private String npk; // Nomor Pokok Karyawan, tetap unik sebagai identitas bisnis

    private String name;
    private String email;
    private String phone;
    private String division;
    private String status;

    @Column(name = "image_url")
    private String imageUrl;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Employee() {}

    public Employee(Long id, String npk, String name, String email, String phone,
                    String division, String status, String imageUrl, String password, Set<Role> roles) {
        this.id = id;
        this.npk = npk;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.division = division;
        this.status = status;
        this.imageUrl = imageUrl;
        this.password = password;
        this.roles = roles;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNpk() { return npk; }
    public void setNpk(String npk) { this.npk = npk; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}
