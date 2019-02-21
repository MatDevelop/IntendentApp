package com.intendentapp.model;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roleID")
    private int roleID;
    
	@Column(name="role")
    private String role;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

