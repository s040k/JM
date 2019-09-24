package app.service;

import app.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String roleName);
    List<Role> getAll();
}
