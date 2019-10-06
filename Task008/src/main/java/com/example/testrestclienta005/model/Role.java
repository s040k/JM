package com.example.testrestclienta005.model;


import org.springframework.security.core.GrantedAuthority;


public class Role implements GrantedAuthority {

    private Long id;
    private String nameRole;


    @Override
    public String getAuthority() {
        return nameRole;
    }

    public Role() {
    }

    public Role(String nameRole) {
        this.nameRole = nameRole;
    }

    public Role(Long id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }


    @Override
    public String toString() {
        return nameRole;
    }
}

