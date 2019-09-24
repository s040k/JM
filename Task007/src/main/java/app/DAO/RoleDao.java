package app.DAO;

import app.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String roleName);
    List<Role> getAll();

}
