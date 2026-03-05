package com.cctv.monitoring.entity;

import jakarta.persistence.*;

/**
 * Entity representing user roles in the RBAC system.
 * Maps to the 'roles' table in the database.
 * Contains predefined roles: ADMIN, OPERATOR, VIEWER
 **/
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 50)
    private String name;

    public  Role(){
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //for debugging
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ",name='" + name + '\'' +
                '}';
    }
}
